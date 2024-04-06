import router from '@ohos.router';

@Entry
@Component



struct Index {
  controller: TextClockController = new TextClockController()
  @State fontColor: string = '#182431'
  @State selectedFontColor: string = '#007DFF'
  @State currentIndex: number = 0
  cut: TabsController = new TabsController()

  @Builder TabBuilder(index: number, name: string) {
    Column() {
      Text(name)
        .fontColor(this.currentIndex === index ? this.selectedFontColor : this.fontColor)
        .fontSize(16)
        .fontWeight(this.currentIndex === index ? 500 : 400)
        .lineHeight(22)
        .margin({ top: 17, bottom: 7 })
      Divider()
        .strokeWidth(2)
        .color('#007DFF')
        .opacity(this.currentIndex === index ? 1 : 0)
    }.width('100%')
  }


  build() {
    //行
    Row() {
      Stack({alignContent: Alignment.Start}) {
        Image($r('app.media.balance'))
          .objectFit(ImageFit.Auto)

        Column() {
          TextClock({ timeZoneOffset: 0, controller: this.controller })
            .format('Hm') // 设置为24小时制
            .fontSize(15)
            .fontColor(Color.Black)
        }
        .margin({left:17,bottom:750})

        Column(){
          // 退款
          Image($r('app.media.refund'))
            .objectFit(ImageFit.Fill)
            .height(35)
            .width(99)
            .onClick(()=>{

            })

        }
        .margin({bottom:550,left:280})

        // 滑动页面
        Column(){
          Tabs({ barPosition: BarPosition.Start, controller: this.cut }) {
              TabContent() {
                Column(){

                }.width('100%').height('20%')
                .backgroundImage($r('app.media.cz'))
                .backgroundImageSize(1)
              }.tabBar(this.TabBuilder(0, '充值明细'))
              .align(Alignment.TopStart)

              TabContent() {
                Column(){
                  Stack(){

                  }
                }.width('100%').height('20%')
              }.tabBar(this.TabBuilder(1, '退款明细'))
            .align(Alignment.TopStart)
          } 
          .vertical(false)
          .onChange((index: number) => {
            this.currentIndex = index
          })
        }
        .width('90%')
        .height(400)
        .margin({top:200,left:'5%'})

        Column({space:5}){
            // ForEach()
        }

        Row(){
          Column(){
            Image($r('app.media.home'))
              .objectFit(ImageFit.Auto)
              .height(34)
              .width(34)
            Text('首页')
              .onClick(()=>{
                router.pushUrl({
                  url:'pages/main'
                })
              })
          }
          Blank()
          Column(){
            Image($r('app.media.user'))
              .objectFit(ImageFit.Auto)
              .height(34)
              .width(34)
            Text('我的')
              // .onClick(()=>{
              //   router.pushUrl({
              //     url:''
              //   })
              // })
          }
        }
        .margin({left:50,top:726})
        .width('70%')

      }
    }
    .height('100%')
  }
}