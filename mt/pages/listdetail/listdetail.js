var app = getApp()
Page({
  data: {
    current: 0,
    shopList: [],
    options:[],
    ptype:'',
    title:'宠物美容学校',
    page:2,
    catId:0,
    brandId: 0,
    limit:8
  },
showModal: function () {
    // 显示遮罩层
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: "linear",
      delay: 0
    })
    this.animation = animation
    animation.translateY(300).step()
    this.setData({
      animationData: animation.export(),
      showModalStatus: true
    })
    setTimeout(function () {
      animation.translateY(0).step()
      this.setData({
        animationData: animation.export()
      })
    }.bind(this), 200)
 },
hideModal: function () {
  // 隐藏遮罩层
  var animation = wx.createAnimation({
   duration: 200,
   timingFunction: "linear",
   delay: 0
  })
  this.animation = animation
  animation.translateY(300).step()
  this.setData({
   animationData: animation.export(),
  })
  setTimeout(function () {
   animation.translateY(0).step()
   this.setData({
    animationData: animation.export(),
    showModalStatus: false
   })
  }.bind(this), 200)
},

//点击加载更多
getMore:function(e){
  // var that = this;
  // var page = that.data.page;
  // wx.request({
  //     url: app.d.ceshiUrl + '/Api/Product/get_more',
  //     method:'post',
  //     data: {
  //       page:page,
  //       ptype:that.data.ptype,
  //       cat_id:that.data.catId,
  //       brand_id: that.data.brandId
  //     },
  //     header: {
  //       'Content-Type':  'application/x-www-form-urlencoded'
  //     },
  //     success: function (res) {  
  //       var prolist = res.data.pro;
  //       if(prolist==''){
  //         wx.showToast({
  //           title: '没有更多数据！',
  //           duration: 2000
  //         });
  //         return false;
  //       }
  //       //that.initProductData(data);
  //       that.setData({
  //         page: page+1,
  //         shopList:that.data.shopList.concat(prolist)
  //       });
  //       //endInitData
  //     },
  //     fail:function(e){
  //       wx.showToast({
  //         title: '网络异常！',
  //         duration: 2000
  //       });
  //     }
  //   })
  this.data.limit = this.data.limit + this.data.limit;
  this.on(this.data.options);
},

onLoad: function (options) {
  this.data.options = options;
  this.on(this.options);
},

on: function (options) {
  var objectId = options.title;
  //更改头部标题
  wx.setNavigationBarTitle({
      title: objectId,
      success: function() {
      },
    });

    //页面初始化 options为页面跳转所带来的参数
    var cat_id = options.cat_id;//分类id
    var that = this;
    that.setData({
      catId: cat_id
    });
    //ajax请求数据
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Product/lists',
      url: app.d.myurl + '/goods/findAllGoods',
      method:'post',
      data: {
        "addgoodsDto": {
          "selectedaddOptions": cat_id
        },
        "myPagaRequest": {
          "page": 1,
          "limit": that.data.limit,
          "sort": "creatTime",
          "dir": "DESC"
        }
        // ptype:ptype,
        // brand_id: brandId
      },
      header: {
        // 'content-type': 'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        var a = {};
        var shoplist = [];
        for (var i = 0; i < res.data.data.content.length; i++) {
          if (res.data.data.content[i].showOut == 'Show') {
            a.id = res.data.data.content[i].goods_id;
            a.name = res.data.data.content[i].goodsName;
            a.price_yh = res.data.data.content[i].skus[0].price;
            a.photo_x = app.d.img + '/ImageResource/' + res.data.data.content[i].masterImage + '.png';
            shoplist.push(a);
            a = {};
          }
        }
        that.setData({
          shopList: shoplist
        })
      },
      error: function(e){
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    })

  },
  //详情页跳转
  lookdetail: function (e) {
    console.log(e)
    var lookid = e.currentTarget.dataset;
    console.log(e.currentTarget.dataset);
    wx.navigateTo({
      url: "../index/detail?id=" + lookid.id
    })
  },
  switchSlider: function (e) {
    this.setData({
      current: e.target.dataset.index
    })
  },
  changeSlider: function (e) {
    this.setData({
      current: e.detail.current
    })
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }

})
