import router from '@ohos.router';
@Entry
@Component

struct Index {
  controller: TextClockController = new TextClockController()
  build() {
    //行
    Row() {
      Stack({alignContent: Alignment.Start}) {
        Image($r('app.media.first'))
          .objectFit(ImageFit.Auto)
        Column() {
          TextClock({ timeZoneOffset: 0, controller: this.controller })
            .format('Hm') // 设置为24小时制
            .fontSize(15)
            .fontColor(Color.Black)
        }
        .margin({left:17,bottom:750})

        Flex(){
          Text()
            .flexGrow(25)
          Text('操 作 说 明')
            .align(Alignment.End)
            .fontSize(15)
            .flexGrow(1)
        }
        .width('100%')
        .height('5%')
        .margin({bottom:577})

        Row() {
          Text('床位信息:#3')
            .fontSize(20)
            .height(50)

          Blank()

          Image($r('app.media.shuye'))
            .width(160)
            .height(50)
            .onClick(()=>{
              AlertDialog.show(
                {
                  title:'当前检测器编号是:#3',
                  message: '确认绑定吗？',
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
        .width('97%')
        .margin({bottom:120})

        Column(){
          Image($r('app.media.ds'))
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