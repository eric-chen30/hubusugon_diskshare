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

          <!-- 姓名 -->
          <el-form-item prop="realname">
            <el-input placeholder="请输入姓名" v-model="logintoForm.realname"></el-input>
          </el-form-item>

          <!-- 专业 -->
          <el-form-item prop="major">
            <el-input placeholder="请输入专业" v-model="logintoForm.major"></el-input>
          </el-form-item>

          <!-- 年级 -->
          <el-form-item prop="class">
            <el-input placeholder="请输入年级" v-model="logintoForm.class"></el-input>
          </el-form-item>

          <!-- 学院 -->
          <el-form-item prop="college">
            <el-input placeholder="请输入学院" v-model="logintoForm.college"></el-input>
          </el-form-item>

          <!-- <el-steps :active="1" finish-status="success" >
                            <el-step title="编辑" ></el-step>
                            <el-step title="审核中" ></el-step>
                            <el-step title="审核通过" ></el-step>
                            <el-step title="审核失败" ></el-step>
          </el-steps>-->

          <!-- <el-row>
                                <span style="font-weight:bolder">审核：<el-button size="mini" type="success" icon="el-icon-check" circle></el-button></span>

                                <span>审核：不通过</span> 
          </el-row>-->

          <!-- 按钮 -->
          <el-form-item class="btns">
            <el-button type="primary" :plain="true" @click="loginback">取消</el-button>
            <el-button type="primary" :plain="true" @click="open2">注册</el-button>
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
        password: '',
        realname: '',
        major: '',
        college: '',
        class: ''
      },
      //登录的规则对象
      addFormRules: {
        email: [
          { reqired: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        password: [
          { reqired: true, message: '请输入密码', trigger: 'blur' },
          {
            min: 6,
            max: 15,
            message: '密码的长度在6~15个字符之间',
            trigger: 'blur'
          }
        ]
      },
      // 注册路由
      registerUrl: ''
    }
  },
  methods: {
    //点击重置按钮，重置注册表单
    resetLogintoForm() {
      // console.log(this)
      this.$refs.logintoFormRef.resetFields()
    },
    //点击注册消息弹框
    open2() {
      this.$refs.logintoFormRef.validate(async valid => {
        if (!valid) return
        this.registerUrl =
          'http://fangyulong.online:8701/registered?name=' +
          this.logintoForm.realname +
          '&profession=' +
          this.logintoForm.major +
          '&class=' +
          this.logintoForm.class +
          '&school=' +
          this.logintoForm.college +
          '&password=' +
          this.logintoForm.password +
          '&email=' +
          this.logintoForm.email

        const res = await this.$http.get(this.registerUrl)
        if (res.data.code !== 1)
          return this.$message.error({ message: '注册失败', duration: 1000 })
        this.$message.success({
          message: '已提交注册信息，待管理员审核',
          duration: 2000
        })
        this.$refs.logintoFormRef.resetFields()
      })
    },

    //点击取消
    loginback() {
      this.$router.push('/Login')
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
    top: 115px;
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
    margin-top: 10px;
  }
  .el-steps {
    box-sizing: border-box;
    justify-content: center;
    margin-bottom: 10px;
  }
}
</style>