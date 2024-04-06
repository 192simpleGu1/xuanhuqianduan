import router from '@ohos.router';
@Entry
@Component
struct Index {
  controller: TextClockController = new TextClockController()
  @State phnum :string = ''
  @State user:string = 'reg'
  @State psw : string = ''
  @State psw2 : string = ''

  build() {
    //行
    Row() {
      Stack({ alignContent: Alignment.Start }) {
        Image($r('app.media.bg'))
          .objectFit(ImageFit.Auto)
        Flex(){
          Text()
            .flexGrow(1)
          Image($r('app.media.reg'))
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

          Column({space:12}){
            Row(){
              Text('账号 +86')
                .fontSize(15)
              TextInput({ placeholder: '请输入手机号' })
                .width('70%')
                .backgroundColor('#FFFFFF')
            }
            Row(){
              Text('验证码：')
              TextInput({ placeholder: '验证码' })
                .width('47%')
                .backgroundColor('#FFFFFF')
              Blank()
              Button('获取验证码')
                .width("30%")
            }
            .width('85%')
            Row(){
              Text('登陆密码')
                .fontSize(15)
              TextInput({ placeholder: '请输入密码' })
                .width('70%')
                .backgroundColor('#FFFFFF')
            }
            Row(){
              Text('确认密码')
                .fontSize(15)
              TextInput({ placeholder: '请确认密码' })
                .width('70%')
                .backgroundColor('#FFFFFF')
            }

          }
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

          Button('验证登录')
            .backgroundColor('#80D8FF')
            .fontColor('#000000')
            .width('85%')
            .height(40)
            // .onClick(() => {
            //   // axioss.uuu(this.phnum,this.user,this.psw,this.psw2);
            //   regapilocal.uuu(this.user,this.phnum,this.psw,this.psw2).then((v)=>{
            //     console.log("true" + JSON.stringify(v))
            //     AlertDialog.show({
            //       message: 'AlertDialog',
            //       autoCancel: false,
            //       alignment: DialogAlignment.Center,
            //       primaryButton: {
            //         value: '取消',
            //         action: () => {
            //           console.info('no')
            //         }
            //       },
            //       secondaryButton: {
            //         value: '确认',
            //         action: () => {
            //           console.info('yes')
            //         }
            //       },
            //     })
            //
            //   }).catch((a)=>{
            //     console.log("false" + JSON.stringify(a))
            //   })
              // u.then((a) => {
              //   // a.data
              //   router.pushUrl({
              //     url: 'pages/main'
              //   })
              //   console.log("nie" + JSON.stringify(a));
              // }).catch((reason)=>{
              //   console.log("nie error " + JSON.stringify(reason));
              // })
              // console.info('跳转');
            //})
        }
        .margin({top:300})
        .width('100%')
      }
    }
    .height('100%')
  }
}