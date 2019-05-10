// import ApiList from  '../../config/api';
// import request from '../../utils/request.js';


//获取应用实例  
var app = getApp();
Page({
    data: {
        // types: null,
        typeTree: {}, // 数据缓存
        currType: 0 ,
        // 当前类型
        "types": [
        ],
        typeTree: [],
      typeThree:[],
      showModalStatus:true,
      showModalStatusThree:false
    },




     //加载   
    onLoad: function (option){
        var that = this;
        wx.request({
          // url: app.d.ceshiUrl + '/Api/Category/index',
          url: app.d.myurl + '/categoryFirst/findAllCategoryFirst',
            method:'post',
            data: {
              "categoryFirstDto": {},
              "myPagaRequest": {
                "page": 1,
                "limit": 100,
                "sort": "creatTime",
                "dir": "DESC"
              }
            },
            header: {
              // 'Content-Type':  'application/x-www-form-urlencoded'
              'Content-Type': 'application/json;charset=UTF-8'
            },
            success: function (res) {
                //--init data 
                var status = res.data.status;
                console.log(res);
                //获取list
                var lists=[];
                var a = {};
                for(var i=0;i<res.data.data.content.length;i++){
                  a.id = res.data.data.content[i].categoryFirst_id;
                  a.tid = "1";
                  a.name = res.data.data.content[i].name;
                  lists.push(a);
                  a={};
                }

                wx.request({
                url: app.d.myurl + '/categorySecond/findAllCategorySecond',
                method: 'post',
                data: {
                  "categorySecondDto": {
                    'categoryFirst_id': lists[0].id
                  },
                  "myPagaRequest": {
                    "page": 1,
                    "limit": 200,
                    "sort": "creatTime",
                    "dir": "DESC"
                  }
                },
                header: {
                  'Content-Type': 'application/json;charset=UTF-8'
                },
                success: function (res) {
                  var catLists = [];
                  var b = {};
                  for (var i = 0; i < res.data.data.content.length; i++) {
                    b.id = res.data.data.content[i].categorySecond_id;
                    b.bz_1 = app.d.img +'/categorySecondImg/'+ res.data.data.content[i].img+'.png';
                    b.name = res.data.data.content[i].name;
                    catLists.push(b);
                    b = {};
                  }
                  var catList = catLists;
                  that.setData({
                    typeTree: catList,
                  });

                },
                error: function (e) {
                  wx.showToast({
                    title: '网络异常！',
                    duration: 2000,
                  });
                },
              });
              if(1) { 
                  var list = lists;
                    that.setData({
                        types:list
                    });
                } else {
                    wx.showToast({
                        title:res.data.err,
                        duration:2000,
                    });
                }
     that.setData({
            currType: 2
        });    
      },
      error:function(e){
          wx.showToast({
              title:'网络异常！',
              duration:2000,
          });
      },
    });
    },   

//点击左侧
  tapType: function (e){
      var that = this;
      const currType = e.currentTarget.dataset.typeId;

      that.setData({
          currType: currType
      });
      wx.request({
        url: app.d.myurl + '/categorySecond/findAllCategorySecond',
        method: 'post',
        data: {
          "categorySecondDto": {
            'categoryFirst_id': currType
          },
          "myPagaRequest": {
            "page": 1,
            "limit": 200,
            "sort": "creatTime",
            "dir": "DESC"
          }
        },
        header: {
          'Content-Type': 'application/json;charset=UTF-8'
        },
        success: function (res) {
          var catLists = [];
          var b = {};
          for (var i = 0; i < res.data.data.content.length; i++) {
            b.id = res.data.data.content[i].categorySecond_id;
            b.bz_1 = app.d.img + '/categorySecondImg/' + res.data.data.content[i].img + '.png';
            b.name = res.data.data.content[i].name;
            catLists.push(b);
            b = {};
          }
          var catList = catLists;
          that.setData({
            typeTree: catList,
            showModalStatus:true,
            showModalStatusThree: false
          });

        },
        error: function (e) {
          wx.showToast({
            title: '网络异常！',
            duration: 2000,
          });
        },
    });
      // wx.request({
      //     url: app.d.ceshiUrl + '/Api/Category/getcat',
      //     method:'post',
      //     data: {cat_id:currType},
      //     header: {
      //         'Content-Type':  'application/x-www-form-urlencoded'
      //     },
      //     success: function (res) {
      //         var status = res.data.status;
      //         if(status==1) { 
      //             var catList = res.data.catList;
      //             that.setData({
      //                 typeTree:catList,
      //             });
      //         } else {
      //             wx.showToast({
      //                 title:res.data.err,
      //                 duration:2000,
      //             });
      //         }
      //     },
      //     error:function(e){
      //         wx.showToast({
      //             title:'网络异常！',
      //             duration:2000,
      //         });
      //     }
      //   });
    },

  three: function (e) {
    var that = this;
    const currType = e.currentTarget.dataset.typeId;
    console.log(currType);
    wx.request({
      url: app.d.myurl +'/categoryThird/findAllCategoryThird',
      method: 'post',
      data: {
        "categoryThirdDto": {
          'categorySecond_id': currType
        },
        "myPagaRequest": {
          "page": 1,
          "limit": 200,
          "sort": "creatTime",
          "dir": "DESC"
        }
      },
      header: {
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var catLists = [];
        var b = {};
        for (var i = 0; i < res.data.data.content.length; i++) {
          b.id = res.data.data.content[i].categoryThird_id;
          b.bz_1 = app.d.img + '/categoryThirdImg/' + res.data.data.content[i].img + '.png';
          b.name = res.data.data.content[i].name;
          catLists.push(b);
          b = {};
        }
        var catList = catLists;
        that.setData({
          typeThree: catList,
          showModalStatus: false,
          showModalStatusThree:true
        });

      },
      error: function (e) {
        wx.showToast({
          title: '网络异常！',
          duration: 2000,
        });
      },
    });
  },



    // 加载品牌、二级类目数据
    getTypeTree (currType) {
        const me = this, _data = me.data;
        if(!_data.typeTree[currType]){
            request({
                url: ApiList.goodsTypeTree,
                data: {typeId: +currType},
                success: function (res) {
                    _data.typeTree[currType] = res.data.data;
                    me.setData({
                        typeTree: _data.typeTree
                    });
                }
            });
        }
    }




})