<template>
  <el-container>
    <div class="loginto_background">
      <img src="../../assets/背景6.jpg" class="img" />
    </div>

    <div class="loginto_container">
      <!-- 框内区域 -->
      <div class="loginto_box">
        <div class="avatar_box">
          <img src="../../assets/瑞翼2.jpg" class="icon_img" width="fullWidth" />
        </div>

        <!-- 注册区域 -->
        <el-form
          ref="logintoFormRef"
          :model="logintoForm"
          :rules="addFormRules"
          label-width="0px"
          class="loginto_form"
        >
          <el-form-item prop="email">
            <el-input placeholder="请输入邮箱" v-model="logintoForm.email"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="请输入密码" v-model="logintoForm.password" type="password"></el-input>
          </el-form-item>

          <!-- <el-row>
                            <span style="font-weight:bolder">审核：<el-button size="mini" type="success" icon="el-icon-check" circle></el-button></span>

                            <span>审核：不通过</span> 
          </el-row>-->

          <!-- 按钮 -->
          <el-form-item class="btns">
            <el-button type="primary" :plain="true" @click="login">登录</el-button>
            <el-button type="primary" :plain="true" @click="loginto">注册</el-button>
            <el-button type="info" @click="resetLogintoForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </el-container>
</template>

<script>
export default {
  data() {
    //验证邮箱的规则
    var checkEmail = (rule, value, cb) => {
      //验证邮箱的正则表达式
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        //合法的邮箱
        return cb()
      }

      cb(new Error('请输入合法的邮箱'))
    }

    return {
      //这是注册表单的数据绑定对象
      logintoForm: {
        email: '',
        password: ''
      },
      // logintoForm:{
      //     email:'300@qq.com',
      //     password:'1111',
      // },
      // 登录信息
      loginUrl: '',
      //登录的规则对象
      addFormRules: {
        email: [
          { reqired: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        password: [
          { reqired: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 4,
            max: 15,
            message: '密码的长度在4~15个字符之间',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    //点击重置按钮，重置登录表单
    resetLogintoForm() {
      // console.log(this)
      this.$refs.logintoFormRef.resetFields()
    },
    //跳转到注册页面
    loginto() {
      //2. 通过编程式导航跳转到登录，路由地址是 /register
      this.$router.push('/register')
    },
    // 表单预验证
    login() {
      this.$refs.logintoFormRef.validate(async valid => {
        if (!valid) return
        this.loginUrl =
          'http://fangyulong.online:8701/login?email=' +
          this.logintoForm.email +
          '&password=' +
          this.logintoForm.password
        const { data: res } = await this.$http.get(this.loginUrl)
        // const { data:res } = await this.$http.get('http://fangyulong.online:8701/login?email=300@qq.com&password=1111')
        // const {data:res} = await this.$http.get('http://fangyulong.online:8703/Shared')
        // console.log(res)
        if (res.code !== 0)
          return this.$message.error({ message: '登录失败', duration: 1000 })
        this.$message.success({ message: '登录成功', duration: 1000 })
        window.sessionStorage.setItem('token', res.data.openId)
        window.sessionStorage.setItem('userName', res.data.userName)
        window.sessionStorage.setItem('imagePath', res.data.imagePath)
        window.sessionStorage.setItem('root', res.data.root)
        this.$router.push('/sky')
      })
    }
  }
}
</script>

<style lang="less" scoped>
.el-header {
  //   background-color: #B3C0D1;
  opacity: 0.8;
  color: #eee;
  text-align: center;
  //   line-height: 5px;
  //   padding-left: 8px;
  //   padding-right:8px ;
  // padding: 10px;
}

.loginto_container {
  height: 100%;
}
.loginto_background {
  height: 100%;
  width: 100%;
  background-color: black;

  background-size: cover; /* 使图片平铺满整个浏览器（从宽和高的最大需求方面来满足，会使某些部分无法显示在区域中） */
  position: absolute;
  z-index: -1;
  background-repeat: no-repeat;

  .img {
    position: relative;
    height: 100%;
    width: 100%;
    opacity: 0.5;
  }
}

.loginto_box {
  width: 340px;
  height: 440px;
  padding: 20px;
  // background-color: #fff;
  border-radius: 6px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  // box-shadow: 2px 5px 40px 1px #000000;

  .avatar_box {
    border: 1px solid #eee;
    border-radius: 50%;
    height: 138px;
    width: 138px;
    padding: 8px;
    box-shadow: 0 0 10px #edd;
    position: absolute;

    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    .icon_img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }

    // .icon_img{
    //     height: 130px;
    //     widows: 130px;
    //     position: absolute;
    //     left: 50%;
    //     top: 0%;
    //     transform: translate(-50%,-50%);

    // }
  }

  .loginto_form {
    top: 165px;
    justify-content: center;
    position: absolute;
    bottom: 0;
    left: 0%;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
    opacity: 0.7;
  }

  .btns {
    padding-top: 8px;
    float: right;
  }
  .el-steps {
    box-sizing: border-box;
    justify-content: center;
    margin-bottom: 10px;
  }
}
</style>