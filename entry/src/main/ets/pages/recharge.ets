import router from '@ohos.router';
@Entry
@Component


struct Index {
  controller: TextClockController = new TextClockController()

  @State num: string = '#03'

  build() {
    //行
    Row() {
      Stack({alignContent: Alignment.Start}) {
        Image($r('app.media.rechange'))
          .objectFit(ImageFit.Auto)
        Column() {
          TextClock({ timeZoneOffset: 0, controller: this.controller })
            .format('Hm') // 设置为24小时制
            .fontSize(15)
            .fontColor(Color.Black)
        }
        .margin({left:17,bottom:750})
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
            Text('首页')
          }
        }
        .margin({left:50,top:730})
        .width('70%')

        Row(){
          // 单选
          Toggle({
            type: ToggleType.Checkbox,
            isOn: false
          })
            .selectedColor(Color.Blue)
            .borderRadius(10)
            .padding(0)
            .border({
              width: 1
            })
          Text('1小时至2小时')
          Blank()
          Toggle({
            type: ToggleType.Checkbox,
            isOn: false
          })
            .selectedColor(Color.Blue)
            .borderRadius(10)
            .padding(0)
            .border({
              width: 1
            })
          Text('2小时至3小时')
        }
        .width('85%')
        .margin({top:60,left:'3%'})

        Column(){
          Image($r('app.media.back'))
            .objectFit(ImageFit.Auto)
            .onClick(()=>{
              router.pushUrl({
                url:'pages/renew'
              })
            })
        }
        .margin({bottom:680,left:5})
        .width(24)
        .height(23)
        .alignItems(HorizontalAlign.Start)

        Column(){
          Text(this.num)
            .fontSize(20)
        }
        .width('95%')
        .alignItems(HorizontalAlign.End)
        .margin({bottom:567})
      }
    }
    .height('100%')
  }
}