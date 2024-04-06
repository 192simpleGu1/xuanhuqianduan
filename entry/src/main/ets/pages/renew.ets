import router from '@ohos.router';
@Entry
@Component

struct Index {
  controller: TextClockController = new TextClockController()

  @State num:string ='#03'
  @State balance:string ='0.5'

  build() {
    //行
    Row() {
      Stack({alignContent: Alignment.Start}) {
        Column(){}
        .margin({bottom:740})
        .width('100%')
        .height(44)
        .backgroundColor('#8876FB')

        Column() {
          TextClock({ timeZoneOffset: 0, controller: this.controller })
            .format('Hm') // 设置为24小时制
            .fontSize(15)
            .fontColor(Color.Black)
        }
        .margin({left:17,bottom:750})

        // 块
        Row(){}
         .height(222)
         .width('100%')
         .backgroundColor('#FBFBFBFB')
         .width('100%')
         .margin({bottom:400})

        Flex(){
          Text('床位信息:'+this.num)
            .fontSize(15)
            .height(18)
            .flexGrow(15)
          Button('解绑床位')
            .height(18)
            .fontColor(Color.Black)
            .backgroundColor('#D8D8D8')
            .align(Alignment.Center)
            .fontSize(15)
            .flexGrow(2)
            .onClick(()=>{
              AlertDialog.show(
                {
                  title:'当前解绑床号是:'+this.num,
                  message: '确认解绑吗？',
                  autoCancel: false,
                  alignment: DialogAlignment.Center,
                  primaryButton: {
                    value: '取消',
                    action: () => {
                      console.info('no')
                    }
                  },
                  secondaryButton: {
                    value: '确认',
                    action: () => {
                      console.info('yes')
                    }
                  }
                }
              )
            })
        }
        .width('80%')
        .height('5%')
        .margin({bottom:577,left:20})

        // 灰块
        Column(){
          Row(){
            Column(){
              Image($r('app.media.zhenguan'))
                .height(50)
                .width(50)
              Button('解绑设备')
                .fontSize(13)
                .fontColor(Color.Black)
                .height(20)
                .width(100)
                .backgroundColor('#FFFFFF')
            }
            Blank()
            Column(){
              Row(){
                Text('设备编号:')
                  .width(100)
                  .height(30)
                Text(this.num)
                  .width(100)
                  .height(30)
              }
              Blank(20)
              Row(){
                Text('绑定时间:')
                  .width(100)
                  .height(30)
                TextClock({ timeZoneOffset: 0, controller: this.controller })
                  .format('yyyy/mm/dd/Hm')
                  .width(100)
                  .height(30)
              }
            }
          }
          .width('90%')
          .height(117)
          .backgroundColor('#DBDBDB')
          .border({radius:20})
        }
        .width('100%')
        .margin({bottom:450})

        Flex(){
            Text('当前设备余额:'+this.balance+'元')
              .fontSize(15)
              .height(18)
              .flexGrow(10)
            Button('续费')
              .width(120)
              .height(18)
              .fontColor(Color.Black)
              .backgroundColor('#D8D8D8')
              .align(Alignment.Center)
              .fontSize(15)
              .flexGrow(1)
            .onClick(()=>{
              router.pushUrl({
                url: 'pages/balance'
              })
            })
        }
        .width('80%')
        .margin({bottom:270,left:20})

        Column(){
          Image($r('app.media.wait'))
            .width(247)
            .height(151)
            .objectFit(ImageFit.Auto)
        }
        .margin({top:100})
        .width('100%')
        .alignItems(HorizontalAlign.Center)

        Row(){
          Column(){
            Image($r('app.media.home'))
              .objectFit(ImageFit.Auto)
              .height(34)
              .width(34)
            Text('首页')
          }
          Blank()
          Column(){
            Image($r('app.media.user'))
              .objectFit(ImageFit.Auto)
              .height(34)
              .width(34)
            Text('我的')
          }
        }
        .margin({left:50,top:726})
        .width('70%')

      }
    }
    .height('100%')
  }
}