// app.js
App({
  d: {
    hostUrl: 'https://lingshixiaodian.club/index.php',
    userId: 1,
    ceshiUrl:'https://lingshixiaodian.club/index.php',
    myurl:'http://127.0.0.1:8080/mt',
    img:'http://127.0.0.1:8080/image/'
  },
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs);
    //login
    this.getUserInfo(false);
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      //调用登录接口
      wx.login({
        success: function (res) {
          var code = res.code;
          //get wx user simple info
          wx.getUserInfo({
            success: function (res) {
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo);
              //get user sessionKey
              //get sessionKey
              that.getUserSessionKey(code);
            },
            fail: function (e) {
              console.log("----",e);
              if (e.errMsg == "getUserInfo:fail scope unauthorized" && cb != false) {
                wx.navigateTo({
                  url: "../../pages/authorize/authorize"
                });
              }
            }
          });
        }
      });
    }
  },

  getUserSessionKey:function(code){
    //用户的订单状态
    var that = this;
    wx.request({
      url: that.d.ceshiUrl + '/Api/Login/getsessionkey',
      method:'post',
      data: {
        code: code
      },
      header: {
        'Content-Type':  'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //--init data        
        var data = res.data;
        if(data.status==0){
          wx.showToast({
            title: data.err,
            duration: 2000
          });
          return false;
        }

        that.globalData.userInfo['sessionId'] = data.session_key;
        that.globalData.userInfo['openid'] = data.openid;
        that.onLoginUser();
      },
      fail:function(e){
        wx.showToast({
          title: '网络异常！err:getsessionkeys',
          duration: 2000
        });
      },
    });
  },
  onLoginUser:function(){
    var that = this;
    var user = that.globalData.userInfo;
    wx.request({
       url: that.d.ceshiUrl + '/Api/Login/authlogin',
      // url: that.d.myurl + '/user/adduser',
      method:'post',
      data: {
        SessionId: user.sessionId,
        gender:user.gender,
        NickName: user.nickName,//用户昵称
        HeadUrl: user.avatarUrl,//用户头像url
        openid:user.openid
      },
      // data: {
      //   sessionId: user.sessionId,
      //   gender: user.gender,
      //   nickName: user.nickName,//用户昵称
      //   headUrl: user.avatarUrl,//用户头像url
      //   openid: user.openid
      // },
      header: {
         'Content-Type':  'application/x-www-form-urlencoded'
        // 'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        //--init data        
        var data = res.data.arr;
        var status = res.data.status;
        if(status!=1){
          wx.showToast({
            title: res.data.err,
            duration: 3000
          });
          return false;
        }
        that.globalData.userInfo['id'] = data.ID;
        that.globalData.userInfo['NickName'] = data.NickName;
        that.globalData.userInfo['HeadUrl'] = data.HeadUrl;
        var userId = data.ID;
        if (!userId){
          wx.showToast({
            title: '登录失败！',
            duration: 3000
          });
          return false;
        }



        wx.request({
          url: that.d.myurl + '/user/adduser',
          method: 'post',
          data: {
            id: userId,
            gender: user.gender,
            nickName: data.NickName,//用户昵称
            headUrl: data.HeadUrl,//用户头像url
          },
          header: {
           'Content-Type': 'application/json;charset=UTF-8'
          },
          success: function (res) {},
          fail: function (e) {
            wx.showToast({
              title: '网络异常！err:authlogin',
              duration: 2000
            });
          }
        });




        that.d.userId = userId;
      },
      fail:function(e){
        wx.showToast({
          title: '网络异常！err:authlogin',
          duration: 2000
        });
      },
    });
  },
  getOrBindTelPhone:function(returnUrl){
    var user = this.globalData.userInfo;
    if(!user.tel){
      wx.navigateTo({
        url: 'pages/binding/binding'
      });
    }
  },

 globalData:{
    userInfo:null
  },

  onPullDownRefresh: function (){
    wx.stopPullDownRefresh();
  }

});





