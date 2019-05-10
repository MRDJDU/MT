// pages/user/user.js
var app = getApp()
Page( {
  data: {
    userInfo: null,
    orderInfo:{},
    userListInfo: [ {
        icon: '../../images/iconfont-dingdan.png',
        text: '我的订单',
        isunread: true,
        unreadNum: 2
      }, {
        icon: '../../images/iconfont-card.png',
        text: '我的代金券',
        isunread: false,
        unreadNum: 2
      }, {
        icon: '../../images/iconfont-icontuan.png',
        text: '我的拼团',
        isunread: true,
        unreadNum: 1
      }, {
        icon: '../../images/iconfont-shouhuodizhi.png',
        text: '收货地址管理'
      }, {
        icon: '../../images/iconfont-kefu.png',
        text: '联系客服'
      }, {
        icon: '../../images/iconfont-help.png',
        text: '常见问题'
      }],
       loadingText: '加载中...',
       loadingHidden: false,
       hasUserInfo : false
  },
  onLoad: function () {
      var that = this
      that.getuserinfo();
  },
  onShow:function(){
    this.loadOrderStatus();
  },

  getuserinfo:function(e){
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo,
        loadingHidden: true,
        hasUserInfo: true
      })
    });
    this.loadOrderStatus();
  },

  loadOrderStatus:function(){
    //获取用户订单数据
    var that = this;
    that.setData({
      userInfo: app.globalData.userInfo
      });
    wx.request({
      // url: app.d.ceshiUrl + '/Api/User/getorder',
      url: app.d.myurl + '/order/findnum',
      method:'post',
      data: {
        user_id:app.d.userId,
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {   
        console.log(res);
        if(1){
          var orderInfo = res.data.data;
          that.setData({
            orderInfo: orderInfo
          });
        }else{
          wx.showToast({
            title: '非法操作.',
            duration: 2000
          });
        }
      },
      error:function(e){
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    });
  },
  onShareAppMessage: function () {
    return {
      title: '友购在线',
      path: '/pages/index/index',
      success: function (res) {
        // 分享成功
      },
      fail: function (res) {
        // 分享失败
      }
    }
  }
})