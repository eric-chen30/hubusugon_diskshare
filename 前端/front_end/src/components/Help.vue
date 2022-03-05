<template>
  <div class="section">
    <!-- 上方头部 -->
    <el-row class="section-header">
      <el-card style="color:#D62164">
        <i class="el-icon-s-data"></i>
        <span>使用帮助</span>
      </el-card>
    </el-row>
    <!-- 中间内容区域 -->
    <el-row class="section-content">
      <!-- 左边手风琴区域 -->
      <el-col :span="12" class="content-left">
        <el-card shadow="always">
          <el-row>用户协议</el-row>
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="关于网盘" name="1">
              <div class="collapse-content">
                <p style="text-indent: 2em">
                  工坊网盘是由工坊团队开发者推出的在线存储服务。用户可以轻松将自己的文件上传到网盘上，
                  并可跨终端随时随地查看和分享。 无需注册，使用QQ一键登录，注重用户的隐私。 具有安全、简约、高效、不限速的特点。
                  其主要特点如下：
                </p>
                <p>
                  1. 提供多元化数据存储服务，用户可自由管理网盘存储文件。
                  <br />2. 提供个性化分享功能，用户通过分享二维码或链接的方式实现文件共享。
                  <br />3. 可跨终端随时随地管理、下载、分享文件。
                  <br />
                </p>
              </div>
            </el-collapse-item>
            <el-collapse-item title="用户的权益" name="2">
              <div class="collapse-content">
                <p style="text-indent: 2em">
                  由于服务器的压力，普通用户最多有1024MB（1GB）的存储空间。
                  工坊网盘保证用户下载文件的速度不作限制。
                </p>
              </div>
            </el-collapse-item>
            <el-collapse-item title="登录与注册" name="3">
              <div class="collapse-content">
                <p style="text-indent: 2em">
                  为了能够带来更加便捷简约的体验，工坊网盘放弃了传统的登录方式，使用腾讯QQ的第三方登录。
                  这样可以有效的解决用户因长期未使用而忘记账号密码的情况。同时也可以将用户的账号安全交给
                  更加权威、安全系数更高的腾讯QQ团队。
                </p>
                <p style="text-indent: 2em">
                  于此同时，工坊网盘在注册也做到了便捷简约，甚至用户到感觉不到具有注册的存在。
                  实际上，在用户第一次登工坊提网盘的时候，便完成了注册的逻辑。
                </p>
                <small>工坊网盘承诺不会挖掘用户的任何隐私信息。</small>
              </div>
            </el-collapse-item>
            <el-collapse-item title="上传与下载" name="4">
              <div class="collapse-content">
                <p style="text-indent: 2em">切换路径支持：双击目录、点击目录名、鼠标右键菜单（移动端使用长按的方式）三种方式</p>
                <p style="text-indent: 2em">
                  上传文件需要选定目录（默认为根目录），预览框内展示出当前目录下的所有文件夹。
                  进入想要上传文件的目录后，可以选取文件（支持多文件选取与上传），之后可以全选文件后者勾选指
                  定文件进行上传。等待进度条到达100%时，即上传成功。
                </p>
                <p style="text-indent: 2em">
                  可以通过点击文件名、双击文件、鼠标右键菜单（移动端使用长按的方式）三种方式下载文件。如果文件
                  较大，服务器需要足够的时间准备，这时候请耐心等待。
                </p>
              </div>
            </el-collapse-item>
            <el-collapse-item title="其他" name="5">
              <div class="collapse-content">
                <p style="text-indent: 2em">
                  工坊网盘还支持新建文件夹、重命名文件夹、删除文件夹（同时删除里面的文件）、重命名文件、
                  文件的分享、文件的删除等，这些你都可以使用鼠标右键菜单实现（移动端长按）。
                </p>
              </div>
            </el-collapse-item>
            <el-collapse-item title="关于作者" name="6">
              <div class="collapse-content">
                <p>
                  个人网站：
                  <a href>工坊</a>
                </p>
                <p>
                  bilibili：
                  <a href>工坊团队</a>
                </p>
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <!-- 右边走马灯区域 -->
      <el-col :span="12" class="content-right">
        <el-card shadow="always" ref="cardHeight">
          <div class="block">
            <el-row class="block-header">
              <p>工坊网盘，只为给你带来最纯粹的体验！</p>
            </el-row>
            <!-- 图片671/480 -->
            <el-carousel :height="autoHeight">
              <el-carousel-item class="small">
                <p>提供多元化数据存储服务，用户可自由管理网盘存储文件。</p>
                <img src="../assets/img01.jpg" model="widthfix" height="100%" />
              </el-carousel-item>
              <el-carousel-item class="small">
                <p>提供个性化分享功能，用户通过分享二维码或链接的方式实现文件共享。</p>
                <img src="../assets/img02.jpg" model="widthfix" height="100%" />
              </el-carousel-item>
              <el-carousel-item class="small">
                <p>可跨终端随时随地管理、下载、分享文件。文件的分类查看与管理。</p>
                <img src="../assets/img03.jpg" model="widthfix" height="100%" />
              </el-carousel-item>
            </el-carousel>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 手风琴激活id
      activeName: '',
      // 图片高度
      autoHeight: ''
    }
  },
  mounted() {
    // 监听窗口大小，窗口大小一变就获取图片高度
    window.onresize = () => {
      return (() => {
        this.getAutoHeight()
      })()
    }
  },
  created() {
    this.getAutoHeight()
  },
  methods: {
    // 获取图片的自适应高度
    getAutoHeight() {
      this.$nextTick(function() {
        this.autoHeight =
          (this.$refs.cardHeight.$el.clientWidth * 480) / 671 + 'px'
      })
    }
  }
}
</script>

<style lang="less" scoped>
.section {
  margin: 10px;

  // 头部
  .section-header {
    margin: -7px 0 20px 0;
    font-weight: 700;
    margin-bottom: 20px;
    font-size: 25px;
    width: 100%;
    i {
      margin-right: 8px;
    }
  }
  // 内容
  .section-content {
    display: flex;
    // 左边
    .content-left {
      flex: 1;
      margin-right: 15px;
      .el-card {
        font-weight: 800;
        .el-row {
          margin: 0px 0 12px 2px;
          font-weight: 800;
          .el-collapse {
            .el-collapse-item {
              line-height: 24px;
              font-size: 16px;
            }
          }
        }
      }
    }
    // 右边
    .content-right {
      flex: 1;
      margin-left: 15px;
    }
  }
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.block-header p {
  display: block;
  margin-block-start: 0em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: 800;
  border-bottom: 1px solid #ebeef5;
  line-height: 31px;
}

.content-right {
  position: relative;
}

.block {
  .small {
    width: 100% !important;
    p {
      display: block;
      margin-block-start: 0em;
      margin-block-end: 0em;
      margin-inline-start: 0px;
      margin-inline-end: 0px;
      position: absolute;
      bottom: 40px;
      color: #fff;
      font-weight: 800;
      text-align: center;
      right: 16%;
      width: 64%;
    }
  }
}
</style>