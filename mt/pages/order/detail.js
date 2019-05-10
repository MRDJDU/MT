var app = getApp();
// pages/order/detail.js
Page({
  data:{
    orderId:0,
    orderData:{},
    proData:[],
  },
  onLoad:function(options){
    this.setData({
      orderId: options.orderId,
    })
    this.loadProductDetail();
  },
  loadProductDetail:function(){
    var that = this;
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Order/order_details',
      url: app.d.myurl + '/order/showall',
      method:'post',
      data: {
        orders_id: that.data.orderId,
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        console.log(888888888888);
        console.log(res);
          var pro = res.data.data.pros;
        var ord = res.data.data.ord;
          that.setData({
            orderData: ord,
            proData:pro
          });
      },
      fail: function () {
          // fail
          wx.showToast({
            title: '网络异常！',
            duration: 2000
          });
      }
    });
  },

})