var app = getApp();

Page({
  data: {
    imgUrls: [],
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    circular: true,
    productData: [],
    proCat:[],
    page: 2,
    index: 2,
    brand:[],
    // 滑动
    imgUrl: [],
    kbs:[],
    lastcat:[],
    course:[],
    search:'',
    limit:8
  },
 
//跳转商品列表页   
listdetail:function(e){
    console.log(e.currentTarget.dataset.title)
    wx.navigateTo({
      url: '../listdetail/listdetail?title='+e.currentTarget.dataset.title,
      success: function(res){
        // success
      },
      fail: function() {
        // fail
      },
      complete: function() {
        // complete
      }
    })
  },
//点击加载更多
getMore:function(e){
  // var that = this;
  // var page = that.data.page;
  // wx.request({
  //     url: app.d.ceshiUrl + '/Api/Index/getlist',
  //     method:'post',
  //     data: {page:page},
  //     header: {
  //       'Content-Type':  'application/x-www-form-urlencoded'
  //     },
  //     success: function (res) {  
  //       var prolist = res.data.prolist;
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
  //         productData:that.data.productData.concat(prolist)
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
  this.on();
},

  changeIndicatorDots: function (e) {
    this.setData({
      indicatorDots: !this.data.indicatorDots
    })
  },
  changeAutoplay: function (e) {
    this.setData({
      autoplay: !this.data.autoplay
    })
  },
  intervalChange: function (e) {
    this.setData({
      interval: e.detail.value
    })
  },
  durationChange: function (e) {
    this.setData({
      duration: e.detail.value
    })
  },

  onLoad: function () {
    this.on();
  },
  
  on:function (options) {
    var that = this;
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Index/index',
      url: app.d.myurl + '/goods/findAllGoods',
      method:'post',
      data: {
        "addgoodsDto": {
          "goodsname": this.data.search,
          "selectedaddOptions": '',
          "type": 0
        },
        "myPagaRequest": {
          "page": 1,
          "limit": this.data.limit,
          "sort": "creatTime",
          "dir": "DESC"
        }
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        // var ggtop = res.data.ggtop;
        var a = {};
        var prolists = [];
        for(var i=0;i<res.data.data.content.length;i++){
          if (res.data.data.content[i].showOut == 'Show'){
            a.id = res.data.data.content[i].goods_id;
            a.name = res.data.data.content[i].goodsName;
            a.price_yh = res.data.data.content[i].skus[0].price;
            a.photo_x = app.d.img + '/ImageResource/' + res.data.data.content[i].masterImage + '.png';
            prolists.push(a);
            a = {};
          }
        }
        var procat ='';
        var prolist = prolists;
        var brand = '';
        var course = '';
        //that.initProductData(data);
        that.setData({
          // imgUrls:ggtop,
          proCat:procat,
          productData:prolist,
          brand: brand,
          course: course
        });
        //endInitData
      },
      fail:function(e){
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      },
    })

  },
  getInput(e){
    this.data.search=e.detail.value
  },
  search() {
    this.on();
  },



});