// pages/user/dingdan.js
//index.js  
//获取应用实例  
var app = getApp();
var common = require("../../utils/common.js");
Page({  
  data: {  
    winWidth: 0,  
    winHeight: 0,  
    // tab切换  
    currentTab: 0,  
    isStatus:'pay',//10待付款，20待发货，30待收货 40、50已完成
    page:0,
    refundpage:0,
    orderList0:[],
    orderList1:[],
    orderList2:[],
    orderList3:[],
    orderList4:[],
  },  
  onLoad: function(options) {  
    this.initSystemInfo();
    this.setData({
      currentTab: parseInt(options.currentTab),
      isStatus:options.otype
    });

    if(this.data.currentTab == 4){
      this.loadReturnOrderList();
    }else{
      this.loadOrderList();
    }
  },  
  getOrderStatus:function(){
    return this.data.currentTab == 0 ? 1 : this.data.currentTab == 2 ?2 :this.data.currentTab == 3 ? 3:0;
  },

//取消订单
removeOrder:function(e){
    var that = this;
    var orderId = e.currentTarget.dataset.ordid;
    wx.showModal({
      title: '提示',
      content: '你确定要取消订单吗？',
      success: function(res) {
        res.confirm && wx.request({
          // url: app.d.ceshiUrl + '/Api/Order/orders_edit',
          url: app.d.myurl + '/order/delete',
          method:'post',
          data: {
            orders_id: orderId
          },
          header: {
            // 'Content-Type':  'application/x-www-form-urlencoded'
            'Content-Type': 'application/json;charset=UTF-8'
          },
          success: function (res) {
            //--init data
            if(1){
              wx.showToast({
                title: '操作成功！',
                duration: 2000
              });
              that.loadOrderList();
            }else{
              wx.showToast({
                title: res.data.err,
                duration: 2000
              });
            }
          },
          fail: function () {
            // fail
            wx.showToast({
              title: '网络异常！',
              duration: 2000
            });
          }
        });

      }
    });
  },

  //确认收货
recOrder:function(e){
    var that = this;
    var orderId = e.currentTarget.dataset.orderId;
    wx.showModal({
      title: '提示',
      content: '你确定已收到宝贝吗？',
      success: function(res) {
        res.confirm && wx.request({
          // url: app.d.ceshiUrl + '/Api/Order/orders_edit',
          url: app.d.myurl + '/order/changeorder',
          method:'post',
          data: {
            orders_id: orderId,
            state: 4
          },
          header: {
            // 'Content-Type':  'application/x-www-form-urlencoded'
            'Content-Type': 'application/json;charset=UTF-8'

          },
          success: function (res) {
            //--init data
            var status = res.data.status;
            if(1){
              wx.showToast({
                title: '操作成功！',
                duration: 2000
              });
              that.loadOrderList();
            }
          },
          fail: function () {
            // fail
            wx.showToast({
              title: '网络异常！',
              duration: 2000
            });
          }
        });

      }
    });
  },

  loadOrderList: function(){
    var that = this;
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Order/index',
      url: app.d.myurl + '/order/findOders',
      method:'post',
      data: {
        user_id:app.d.userId,
        order_type:that.data.isStatus
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        //--init data  

        var list = res.data.data;
        console.log(list);
        switch(that.data.currentTab){
          case 0:
            that.setData({
              orderList0: list,
            });
            break;
          case 1:
            that.setData({
              orderList1: list,
            });
            break;  
          case 2:
            that.setData({
              orderList2: list,
            });
            break;
          case 3:
            that.setData({
              orderList3: list,
            });
            break;
          case 4:
            that.setData({
              orderList4: list,
            });
            break;  
        }
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

loadReturnOrderList:function(){
    var that = this;
    wx.request({
      url: app.d.ceshiUrl + '/Api/Order/order_refund',
      method:'post',
      data: {
        uid:app.d.userId,
        page:that.data.refundpage,
      },
      header: {
        'Content-Type':  'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //--init data        
        var data = res.data.ord;
        var status = res.data.status;
        if(status==1){
          that.setData({
            orderList4: that.data.orderList4.concat(data),
          });
        }else{
          wx.showToast({
            title: res.data.err,
            duration: 2000
          });
        }
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
  
  // returnProduct:function(){
  // },
  initSystemInfo:function(){
    var that = this;  
  
    wx.getSystemInfo( {
      success: function( res ) {  
        that.setData( {  
          winWidth: res.windowWidth,  
          winHeight: res.windowHeight  
        });  
      }    
    });  
  },
  bindChange: function(e) {  
    var that = this;  
    that.setData( { currentTab: e.detail.current });  
  },  
  swichNav: function(e) {  
    var that = this;  
    if( that.data.currentTab === e.target.dataset.current ) {  
      return false;  
    } else { 
      var current = e.target.dataset.current;
      that.setData({
        currentTab: parseInt(current),
        isStatus: e.target.dataset.otype,
      });

      //没有数据就进行加载
      switch(that.data.currentTab){
          case 0:
            !that.data.orderList0.length && that.loadOrderList();
            break;
          case 1:
            !that.data.orderList1.length && that.loadOrderList();
            break;  
          case 2:
            !that.data.orderList2.length && that.loadOrderList();
            break;
          case 3:
            !that.data.orderList3.length && that.loadOrderList();
            break;
          case 4:
            that.data.orderList4.length = 0;
            that.loadReturnOrderList();
            break;
        }
    };
  },
  /**
   * 微信支付订单
   */
  // payOrderByWechat: function(event){
  //   var orderId = event.currentTarget.dataset.orderId;
  //   this.prePayWechatOrder(orderId);
  //   var successCallback = function(response){
  //     console.log(response);
  //   }
  //   common.doWechatPay("prepayId", successCallback);
  // },

  payOrderByWechat: function (e) {
    var orders_id = e.currentTarget.dataset.oid;
    var price = e.currentTarget.dataset.price;
    console.log(orders_id);
    wx.navigateTo({
      url: "../view/pwdInput?orders_id=" + orders_id + '&price=' + price
    })
  },

  /**
   * 调用服务器微信统一下单接口创建一笔微信预订单
   */
//   prePayWechatOrder: function(orderId){
//     var uri = "/ztb/userZBT/GetWxOrder";
//     var method = "post";
//     var dataMap = {
//       SessionId: app.globalData.userInfo.sessionId,
//       OrderNo: orderId
//     }
//     console.log(dataMap);
//     var successCallback = function (response) {
//       console.log(response);
//     };
//     common.sentHttpRequestToServer(uri, dataMap, method, successCallback);
//   }
})