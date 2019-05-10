var app = getApp();
// pages/order/downline.js
Page({
  data:{
    itemData:{},
    userId:0,
    paytype:'weixin',//0线下1微信
    remark:'',
    cartId:0,
    addrId:0,//收货地址//测试--
    btnDisabled:false,
    productData:[],
    address:{},
    total:0,
    vprice:0,
    vid:0,
    addemt:1,
    vou:[]
  },
  onLoad:function(options){
    var uid = app.d.userId;
    console.log(options.cartId);
    this.setData({
      cartId: options.cartId,
      userId: uid
    });
    this.loadProductDetail();
  },
  loadProductDetail:function(){
    var that = this;
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Payment/buy_cart',
      url: app.d.myurl + '/car/buy_cart',
      method:'post',
      data: {
        shoppingCart_id: that.data.cartId,
        user_id: that.data.userId,
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        //that.initProductData(res.data);
        console.log(res);
        var totals = 0;
        var pros=[];
        var a={};
        for(var i=0;i<res.data.data.length;i++){
          a.id = res.data.data[i].shoppingCart_id;
          a.name = res.data.data[i].goodsName + '(' + res.data.data[i].sku+')';
          a.photo_x = res.data.data[i].img;
          a.price = res.data.data[i].price;
          a.num = res.data.data[i].goodsCount;
          pros.push(a);
          totals = totals + res.data.data[i].zprice;
          a={};
        }


        that.setData({
          addemt: 0,
          productData: pros,
          total: totals,//共支付金额
        });





        wx.request({
          url: app.d.ceshiUrl + '/Api/Address/index',
          data: {
            user_id: app.d.userId,
          },
          method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
          header: {// 设置请求的 header
            'Content-Type': 'application/x-www-form-urlencoded'
          },

          success: function (res) {
            console.log(111111111111122222);
            console.log(res);
            var adds ={};
            for(var i = 0;i<res.data.adds.length;i++){
              if (res.data.adds[i].is_default==1){
                adds = res.data.adds[i];
              }
            }
            // var adds = res.data.adds;
            if (adds) {
              var addrId = adds.id;
              that.setData({
                address: adds,
                addrId: addrId
              });
            }




            // // success
            // console.log(res);
            // var address = res.data.adds;
            // console.log(address);
            // if (address == '') {
            //   var address = []
            // }

            // that.setData({
            //   address: address,
            //   cartId: cartId,
            // })
          },
          fail: function () {
            // fail
            wx.showToast({
              title: '网络异常！',
              duration: 2000
            });
          }
        })
       
        
        //endInitData
      },
    });
  },

  remarkInput:function(e){
    this.setData({
      remark: e.detail.value,
    })
  },

 //选择优惠券
  getvou:function(e){
    var vid = e.currentTarget.dataset.id;
    var price = e.currentTarget.dataset.price;
    var zprice = this.data.vprice;
    var cprice = parseFloat(zprice) - parseFloat(price);
    this.setData({
      total: cprice,
      vid: vid
    })
  }, 

//微信支付
  createProductOrderByWX:function(e){
    this.setData({
      paytype: 'weixin',
    });

    this.createProductOrder();
  },

  //确认订单
  createProductOrder:function(){
    this.setData({
      btnDisabled:false,
    })
    
    //创建订单
    var that = this;
    if (that.data.addrId == 0 || that.data.addrId == undefined){
      wx.showToast({
        title: "请选择收货地址！",
        duration: 2000
      });
      return false;
    }
    var adds = {};
    adds.address_xq = that.data.address.address_xq;
    adds.tel = that.data.address.tel;
    adds.name = that.data.address.name;
    //创建订单
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Payment/payment',
      url: app.d.myurl + '/order/addorder',
      method:'post',
      data: {
        orderDetails: that.data.productData,
        add: adds,
        total: that.data.total,
        user_id: app.d.userId
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        //--init data        
        var orders_id = res.data.data.orders_id;
        console.log(res);
        if(1){
          wx.navigateTo({
            url: "../view/pwdInput?orders_id=" + orders_id + '&price=' + that.data.total
          })
        }else{
          wx.showToast({
            title:"下单失败!",
            duration:2500
          });
        }
      },
      fail: function (e) {
        wx.showToast({
          title: '网络异常！err:createProductOrder',
          duration: 2000
        });
      }
    });
  },
  
  //调起微信支付
  wxpay: function(order){
      wx.request({
        url: app.d.ceshiUrl + '/Api/Wxpay/wxpay',
        data: {
          order_id:order.order_id,
          order_sn:order.order_sn,
          uid:this.data.userId,
        },
        method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
        header: {
          'Content-Type':  'application/x-www-form-urlencoded'
        }, // 设置请求的 header
        success: function(res){
          if(res.data.status==1){
            var order=res.data.arr;
            wx.requestPayment({
              timeStamp: order.timeStamp,
              nonceStr: order.nonceStr,
              package: order.package,
              signType: 'MD5',
              paySign: order.paySign,
              success: function(res){
                wx.showToast({
                  title:"支付成功!",
                  duration:2000,
                });
                setTimeout(function(){
                  wx.navigateTo({
                    url: '../user/dingdan?currentTab=1&otype=deliver',
                  });
                },2500);
              },
              fail: function(res) {
                wx.showToast({
                  title:res,
                  duration:3000
                })
              }
            })
          }else{
            wx.showToast({
              title: res.data.err,
              duration: 2000
            });
          }
        },
        fail: function() {
          // fail
          wx.showToast({
            title: '网络异常！err:wxpay',
            duration: 2000
          });
        }
      })
  },


});