import router from '@ohos.router';
import EnvironmentCallback from '@ohos.app.ability.EnvironmentCallback';
import call from '@ohos.telephony.call';

import {login} from "../api/api"

@Entry
@Component
struct Index {
  controller: TextClockController = new TextClockController()

  @State username:string = 'log'
  @State psw:string = ''

  build() {
    //行
    Row() {
      Stack({ alignContent: Alignment.Start }) {
        Image($r('app.media.bg'))
          .objectFit(ImageFit.Auto)
        Flex(){
          Text()
            .flexGrow(1)
          Image($r('app.media.log'))
            .objectFit(ImageFit.Auto)
            .width(300)
            .height(360)
          Text()
            .flexGrow(1)
        }
        .width('100%')
        .margin({bottom:340})

        Column() {
          TextClock({ timeZoneOffset: 0, controller: this.controller })
            .format('Hm') // 设置为24小时制
            .fontSize(15)
            .fontColor(Color.Black)
        }
        .margin({ left: 17, bottom: 750 })

        Column(){
          TextInput({ text: this.username, placeholder: '账号' })
            .onChange((user)=>{
              this.username = user
            })
            .width('95%')
            .backgroundColor('#FFFFFF')
          Blank(30)
          TextInput({ placeholder: '密码' })
            .onChange((psw)=>{
              this.psw = psw
            })
            .width('95%')
            .backgroundColor('#FFFFFF')

          Blank(20)

          Flex({ direction: FlexDirection.Row }) {
            Text('短信验证码登录').width('35%').height(20).fontColor('#FFFFFF')
              .onClick(() => {
                router.pushUrl({
                  url:'pages/reg'
                })
                // console.log('跳转');
              })
            Text()
              .flexGrow(1)
            Text('忘 记 密 码').height(20).fontColor('#FFFFFF')
            // .onClick(() => {
            //   router.pushUrl({
            //     url: 'pages/reg' // 目标url
            //   })
            // })
          }
          .width('90%')

          Blank()

          Row(){
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
            Blank()
            Text("我已阅读并同意《用户服务协议》与《隐私协议》")
              .fontColor('#3D3D3D')
              .fontSize(12)
          }
          .width('85%')

          Blank(10)

          Button('登 录')
            .backgroundColor('#80D8FF')
            .fontColor('#000000')
            .width('90%')
            .height(40)
            .onClick(() => {

              let login_data = {
                "phone":this.username,
                "password":this.psw

              }


              console.log("nth send " + JSON.stringify(login_data))

              login(login_data).then((res)=>{
                console.log("nth then" + JSON.stringify(res))
              }).catch((rej)=>{
                console.log("nth catch" + JSON.stringify(rej))
              })
              // apilocal.uuu(this.username,this.psw).then((v)=>{
              // //  apinet.uuu().then((v)=>{
              //    console.log("true" + JSON.stringify(v))
              //    router.pushUrl({
              //      url:'pages/main'
              //    })
              //  }).catch((a)=>{
              //    console.log("false1" + JSON.stringify(a))
              //  })
            })
          Blank(10)
          Text('注册账号')
            .fontColor('#FFFFFF')
            .fontSize(16)
            .onClick(()=>{
              router.pushUrl({
                url:'pages/reg'
              })
            })
        }
        .margin({top:300})
        .width('100%')
      }
    }
    .height('100%')
  }
}
