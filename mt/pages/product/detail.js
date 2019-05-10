//index.js  
//获取应用实例  
var app = getApp();
//引入这个插件，使html内容自动转换成wxml内容
var WxParse = require('../../wxParse/wxParse.js');
Page({
  firstIndex: -1,
  data:{
    bannerApp:true,
    winWidth: 0,
    winHeight: 0,
    currentTab: 0, //tab切换  
    productId:0,//产品id
    itemData:{},
    price:{},
    bannerItem:[],
    buynum:1,
    // 产品图片轮播
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    // 属性选择
    firstIndex: -1,
    //准备数据
    //数据结构：以一组一组来进行设定
     commodityAttr:[],
     attrValueList: [],
     userInfo:{},
     norms:[],
     skuid:'',
    gg_id:'',
     gg_txt:'',
    guigeList: [{ guige: '100', price: '133' }, { guige: '200', price: '345' }, { guige: '300', price: 'fsf' }]
  },

  filter: function (e) {
    //console.log(e);
    var self = this, 
    id = e.currentTarget.dataset.id, 
    txt = e.currentTarget.dataset.txt, 
    prices = e.currentTarget.dataset.price,
    skuids = e.currentTarget.dataset.skuid
    self.setData({
      gg_id: id,
      gg_txt: txt,
      gg_price: prices,
      price: prices,
      skuid:skuids
    });
  },

  // 弹窗
  setModalStatus: function (e) {
    if (app.globalData.userInfo == null) {
      wx.showToast({
        title: '请先授权登录！',
        duration: 2000
      });
      return;
    };
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: "linear",
      delay: 0
    })

    this.animation = animation
    animation.translateY(300).step();
    
    this.setData({
      animationData: animation.export()
    })

    if (e.currentTarget.dataset.status == 1) {

      this.setData(
        {
          showModalStatus: true
        }
      );
    }
    setTimeout(function () {
      animation.translateY(0).step()
      this.setData({
        animationData: animation
      })
      if (e.currentTarget.dataset.status == 0) {
        this.setData(
          {
            showModalStatus: false
          }
        );
      }
    }.bind(this), 200)
  },
  // 加减
  changeNum:function  (e) {
    var that = this;
    if (e.target.dataset.alphaBeta == 0) {
        if (this.data.buynum <= 1) {
            buynum:1
        }else{
            this.setData({
                buynum:this.data.buynum - 1
            })
        };
    }else{
        this.setData({
            buynum:this.data.buynum + 1
        })
    };
  },
  // 传值
  onLoad: function (option) {     
    //this.initNavHeight();
    var that = this;
    that.setData({
      productId: option.productId,
    });
    that.loadProductDetail();

  },
// 商品详情数据获取
  loadProductDetail:function(){
    var that = this;
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Product/index',
      url: app.d.myurl + '/goods/findAllGoods',
      method:'post',
      data: {
        // pro_id: that.data.productId,
        "addgoodsDto": {
          "goodsname": '',
          "selectedaddOptions": '',
          "goods_id": that.data.productId,
          "type": 0
        },
        "myPagaRequest": {
          "page": 1,
          "limit": 1,
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
        if(1) {   
          var pro = {};
          pro.price_yh = res.data.data.content[0].skus[0].price;
          pro.name = res.data.data.content[0].goodsName;
          pro.num = res.data.data.content[0].skus[0].stock;
          pro.brand = res.data.data.content[0].brand;
          pro.id = res.data.data.content[0].goods_id;
          pro.photo_x = app.d.img + '/ImageResource/' +  res.data.data.content[0].detailsImage[0].img + '.png';
          for (var i = 0; i < res.data.data.content[0].detailsImage.length;i++){
            res.data.data.content[0].detailsImage[i] = app.d.img +'/ImageResource/'+ res.data.data.content[0].detailsImage[i].img + '.png';
          }
          // var content=pro.content;
          //that.initProductData(data);
          // WxParse.wxParse('content', 'html', content, that, 3);
          that.setData({
            itemData: pro,
            norms: res.data.data.content[0].norms,
            bannerItem: res.data.data.content[0].detailsImage,
            price: res.data.data.content[0].skus[0].price,
            guigeList: res.data.data.content[0].skus,
            gg_id: res.data.data.content[0].skus[0].sku,
            gg_txt: res.data.data.content[0].skus[0].sku,
            skuid: res.data.data.content[0].skus[0].sku_id

            // commodityAttr:res.data.commodityAttr,
            // attrValueList:res.data.attrValueList,
          });
        } else {
          wx.showToast({
            title:res.data.err,
            duration:2000,
          });
        }
      },
      error:function(e){
        wx.showToast({
          title:'网络异常！',
          duration:2000,
        });
      },
    });
  },
// 属性选择
  onShow: function () {
    var that = this;
    that.setData({
      includeGroup: that.data.commodityAttr
    });
    that.distachAttrValue(that.data.commodityAttr);
    // 只有一个属性组合的时候默认选中
    // console.log(this.data.attrValueList);
    if (that.data.commodityAttr.length == 1) {
      for (var i = 0; i < that.data.commodityAttr[0].attrValueList.length; i++) {
        that.data.attrValueList[i].selectedValue = that.data.commodityAttr[0].attrValueList[i].attrValue;
      }
      that.setData({
        attrValueList: that.data.attrValueList
      });
    };
    that.getuserinfo();
  },
  /* 获取数据 */
  distachAttrValue: function (commodityAttr) {
    var that = this;
    /**
      将后台返回的数据组合成类似
      {
        attrKey:'型号',
        attrValueList:['1','2','3']
      }
    */
    // 把数据对象的数据（视图使用），写到局部内
    var attrValueList = that.data.attrValueList;
    // 遍历获取的数据
    for (var i = 0; i < commodityAttr.length; i++) {
      for (var j = 0; j < commodityAttr[i].attrValueList.length; j++) {
        var attrIndex = that.getAttrIndex(commodityAttr[i].attrValueList[j].attrKey, attrValueList);
        // console.log('属性索引', attrIndex); 
        // 如果还没有属性索引为-1，此时新增属性并设置属性值数组的第一个值；索引大于等于0，表示已存在的属性名的位置
        if (attrIndex >= 0) {
          // 如果属性值数组中没有该值，push新值；否则不处理
          if (!that.isValueExist(commodityAttr[i].attrValueList[j].attrValue, attrValueList[attrIndex].attrValues)) {
            attrValueList[attrIndex].attrValues.push(commodityAttr[i].attrValueList[j].attrValue);
          }
        } else {
          attrValueList.push({
            attrKey: commodityAttr[i].attrValueList[j].attrKey,
            attrValues: [commodityAttr[i].attrValueList[j].attrValue]
          });
        }
      }
    }
    // console.log('result', attrValueList)
    for (var i = 0; i < attrValueList.length; i++) {
      for (var j = 0; j < attrValueList[i].attrValues.length; j++) {
        if (attrValueList[i].attrValueStatus) {
          attrValueList[i].attrValueStatus[j] = true;
        } else {
          attrValueList[i].attrValueStatus = [];
          attrValueList[i].attrValueStatus[j] = true;
        }
      }
    }
    that.setData({
      attrValueList: attrValueList
    });
  },
  getAttrIndex: function (attrName, attrValueList) {
    // 判断数组中的attrKey是否有该属性值
    for (var i = 0; i < attrValueList.length; i++) {
      if (attrName == attrValueList[i].attrKey) {
        break;
      }
    }
    return i < attrValueList.length ? i : -1;
  },
  isValueExist: function (value, valueArr) {
    // 判断是否已有属性值
    for (var i = 0; i < valueArr.length; i++) {
      if (valueArr[i] == value) {
        break;
      }
    }
    return i < valueArr.length;
  },
  /* 选择属性值事件 */
  selectAttrValue: function (e) {
    /*
    点选属性值，联动判断其他属性值是否可选
    {
      attrKey:'型号',
      attrValueList:['1','2','3'],
      selectedValue:'1',
      attrValueStatus:[true,true,true]
    }
    console.log(e.currentTarget.dataset);
    */
    var attrValueList = this.data.attrValueList;
    var index = e.currentTarget.dataset.index;//属性索引
    var key = e.currentTarget.dataset.key;
    var value = e.currentTarget.dataset.value;
    if (e.currentTarget.dataset.status || index == this.data.firstIndex) {
      if (e.currentTarget.dataset.selectedvalue == e.currentTarget.dataset.value) {
        // 取消选中
        this.disSelectValue(attrValueList, index, key, value);
      } else {
        // 选中
        this.selectValue(attrValueList, index, key, value);
      }

    }
  },
  /* 选中 */
  selectValue: function (attrValueList, index, key, value, unselectStatus) {
    // console.log('firstIndex', this.data.firstIndex);
    var includeGroup = [];
    if (index == this.data.firstIndex && !unselectStatus) { // 如果是第一个选中的属性值，则该属性所有值可选
      var commodityAttr = this.data.commodityAttr;
      // 其他选中的属性值全都置空
      // console.log('其他选中的属性值全都置空', index, this.data.firstIndex, !unselectStatus);
      for (var i = 0; i < attrValueList.length; i++) {
        for (var j = 0; j < attrValueList[i].attrValues.length; j++) {
          attrValueList[i].selectedValue = '';
        }
      }
    } else {
      var commodityAttr = this.data.includeGroup;
    }

    // console.log('选中', commodityAttr, index, key, value);
    for (var i = 0; i < commodityAttr.length; i++) {
      for (var j = 0; j < commodityAttr[i].attrValueList.length; j++) {
        if (commodityAttr[i].attrValueList[j].attrKey == key && commodityAttr[i].attrValueList[j].attrValue == value) {
          includeGroup.push(commodityAttr[i]);
        }
      }
    }
    attrValueList[index].selectedValue = value;

    // 判断属性是否可选
    // for (var i = 0; i < attrValueList.length; i++) {
    //   for (var j = 0; j < attrValueList[i].attrValues.length; j++) {
    //     attrValueList[i].attrValueStatus[j] = false;
    //   }
    // }
    // for (var k = 0; k < attrValueList.length; k++) {
    //   for (var i = 0; i < includeGroup.length; i++) {
    //     for (var j = 0; j < includeGroup[i].attrValueList.length; j++) {
    //       if (attrValueList[k].attrKey == includeGroup[i].attrValueList[j].attrKey) {
    //         for (var m = 0; m < attrValueList[k].attrValues.length; m++) {
    //           if (attrValueList[k].attrValues[m] == includeGroup[i].attrValueList[j].attrValue) {
    //             attrValueList[k].attrValueStatus[m] = true;
    //           }
    //         }
    //       }
    //     }
    //   }
    // }
    // console.log('结果', attrValueList);
    this.setData({
      attrValueList: attrValueList,
      includeGroup: includeGroup
    });

    var count = 0;
    for (var i = 0; i < attrValueList.length; i++) {
      for (var j = 0; j < attrValueList[i].attrValues.length; j++) {
        if (attrValueList[i].selectedValue) {
          count++;
          break;
        }
      }
    }
    if (count < 2) {// 第一次选中，同属性的值都可选
      this.setData({
        firstIndex: index
      });
    } else {
      this.setData({
        firstIndex: -1
      });
    }
  },
  /* 取消选中 */
  disSelectValue: function (attrValueList, index, key, value) {
    var commodityAttr = this.data.commodityAttr;
    attrValueList[index].selectedValue = '';

    // 判断属性是否可选
    for (var i = 0; i < attrValueList.length; i++) {
      for (var j = 0; j < attrValueList[i].attrValues.length; j++) {
        attrValueList[i].attrValueStatus[j] = true;
      }
    }
    this.setData({
      includeGroup: commodityAttr,
      attrValueList: attrValueList
    });

    for (var i = 0; i < attrValueList.length; i++) {
      if (attrValueList[i].selectedValue) {
        this.selectValue(attrValueList, i, attrValueList[i].attrKey, attrValueList[i].selectedValue, true);
      }
    }
  },

  initProductData: function(data){
    data["LunBoProductImageUrl"] = [];

    var imgs = data.LunBoProductImage.split(';');
    for(let url of imgs){
      url && data["LunBoProductImageUrl"].push(app.d.hostImg + url);
    }

    data.Price = data.Price/100;
    data.VedioImagePath = app.d.hostVideo + '/' +data.VedioImagePath;
    data.videoPath = app.d.hostVideo + '/' +data.videoPath;
  },


  getuserinfo: function (e) {
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
    });
  },

//添加到收藏
  addFavorites:function(e){
    var that = this;
    if (app.globalData.userInfo == null){
      wx.showToast({
        title: '请先授权登录！',
        duration: 2000
      });
      return;
    };
    wx.request({
      url: app.d.ceshiUrl + '/Api/Product/col',
      method:'post',
      data: {
        uid: app.d.userId,
        pid: that.data.productId,
      },
      header: {
        'Content-Type':  'application/x-www-form-urlencoded'
      },
      success: function (res) {
        // //--init data        
        var data = res.data;
        if(data.status == 1){
          wx.showToast({
            title: '操作成功！',
            duration: 2000
          });
          //变成已收藏，但是目前小程序可能不能改变图片，只能改样式
          that.data.itemData.isCollect = true;
        }else{
          wx.showToast({
            title: data.err,
            duration: 2000
          });
        }
      },
      fail: function() {
        // fail
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    });
  },

  addShopCart:function(e){ //添加到购物车
    var that = this;
    if (app.globalData.userInfo == null) {
      wx.showToast({
        title: '请先授权登录！',
        duration: 2000
      });
      return;
    };
    wx.request({
      // url: app.d.ceshiUrl + '/Api/Shopping/add',
      url: app.d.myurl + '/car/addcar',
      method:'post',
      data: {
        // uid: app.d.userId,
        // pid: that.data.productId,
        // num: that.data.buynum,
        shoppingCart_id: that.data.productId,
        user_id: app.d.userId,
        goods_id: that.data.productId,
        sku_id: that.data.skuid,
        goodsName: that.data.itemData.name,
        img: that.data.itemData.photo_x,
        goodsCount: that.data.buynum,
        price: that.data.price,
        sku: that.data.gg_txt,
        state:1
      },
      header: {
        // 'Content-Type':  'application/x-www-form-urlencoded'
        'Content-Type': 'application/json;charset=UTF-8'
      },
      success: function (res) {
        // //--init data        
        var data = res.data;
        that.setData(
          {
            showModalStatus: false
          }
        );
        if(1){
          var ptype = e.currentTarget.dataset.type;
          console.log(ptype);
          if (ptype == 'buynow'){
            wx.redirectTo({
              url: '../order/pay?cartId=' + that.data.productId
            });
            return;
          }else{
            wx.showToast({
                title: '加入购物车成功',
                icon: 'success',
                duration: 2000
            });
          }     
        }else{
          wx.showToast({
                title: data.err,
                duration: 2000
            });
        }
      },
      fail: function() {
        // fail
        wx.showToast({
          title: '网络异常！',
          duration: 2000
        });
      }
    });
  },
  bindChange: function (e) {//滑动切换tab 
    var that = this;
    that.setData({ currentTab: e.detail.current });
  },
  initNavHeight:function(){////获取系统信息
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    });
  },
  bannerClosed:function(){
    this.setData({
      bannerApp:false,
    })
  },
  swichNav: function (e) {//点击tab切换
    var that = this;
    if (that.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  }
});
