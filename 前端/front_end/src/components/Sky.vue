<template>
  <div class="main-wrapper">
    <el-container class="container_1">
      <!-- 侧边栏 -->
      <!-- id="sidebar" tabindex="1" style="overflow: hidden; outline: none;" -->
      <el-aside :width="isCollapse ? '0px' : '250px'" class="main-sidebar">
        <div class="sidebar-brand">
          <a href="#">工坊网盘</a>
        </div>
        <div class="sidebar-user">
          <div class="sidebar-user-picture">
            <a v-if="imageShow">
              <img style="width: 50px; height:50px" alt="image" src="../assets/100.png" />
            </a>
            <a v-else>
              <img style="width: 50px; height:50px" alt="image" :src="{imagePath}" />
            </a>
          </div>
          <div class="sidebar-user-details">
            <div class="user-name">{{userName}}</div>
            <div class="user-role">普通用户</div>
          </div>
        </div>
        <el-menu
          class="sidebar-menu"
          :active-text-color="themeColor"
          :collapse="isCollapse"
          :collapse-transition="false"
          router
        >
          <li class="menu-header">预览</li>
          <el-menu-item v-for="item in forUpper" :key="item.id" :index="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>

          <el-submenu index="4">
            <template slot="title">
              <i :class="iconsObj[4]"></i>
              <span>分类文件</span>
            </template>

            <el-menu-item v-for="item in forLower" :key="item.id" :index="item.index">
              <template slot="title">
                <i :class="item.icon"></i>
                <span>{{item.title}}</span>
                <div class="badge badge-primary">{{item.text_num}}</div>
              </template>
            </el-menu-item>
          </el-submenu>
          <el-menu-item index="/help">
            <i :class="iconsObj[10]"></i>
            <span slot="title">帮助</span>
          </el-menu-item>
          <div class="p-3 mt-4 mb-4 sidebar-button">
            <el-button class="btn-color" round size="medium" @click="logout">
              <i class="el-icon-d-arrow-right button-icon"></i>&nbsp;退&nbsp;出&nbsp;登&nbsp;录
            </el-button>
          </div>
        </el-menu>
      </el-aside>
      <!-- 侧边栏结束 -->

      <el-container class="main-container">
        <div class="navbar-bg"></div>

        <!--右侧头部区域-->
        <!-- id="top" style="transform:translate3d(0px, 0px, 0px; position: fixed; top: 0px;)" -->
        <el-header>
          <div class="header-left">
            <el-button circle @click="toggleCollapse">
              <i class="el-icon-data-board"></i>
            </el-button>
          </div>
          <div class="header-right">
            <el-menu background-color="#FDE5E5" text-color="#000000">
              <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-2">
                  <el-button
                    class="btn-color"
                    round
                    size="medium"
                    @click="logout"
                  >退&nbsp;出&nbsp;登&nbsp;录</el-button>
                </el-menu-item>
              </el-submenu>
            </el-menu>
          </div>
        </el-header>
        <!--右侧头部区域结束-->

        <!-- 右侧内容主体 -->
        <el-main class="main-content">
          <!-- 路由占位符 -->
          <router-view></router-view>
        </el-main>
        <!-- 右侧内容主体结束 -->

        <!-- 右侧底部区域 -->
        <el-footer>
          <div class="footer-left">
            COPYRIGHT&nbsp;©&nbsp;2020
            <div class="bullet">。</div>版权所有&nbsp;工坊网盘
            <div class="bullet">。</div>备案号：
            <a>123456789号</a>
          </div>
          <div class="footer-right"></div>
        </el-footer>
        <!-- 右侧底部区域结束 -->
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // openid
      openId: '',
      //用户名
      userName: '',
      // 头像
      imagePath: '',
      // 主题颜色
      themeColor: '#D62164',
      // 头像展示
      imageShow: true,
      // 左侧菜单数据
      menulist: [],
      iconsObj: {
        '1': 'el-icon-s-opportunity',
        '2': 'el-icon-s-grid',
        '3': 'el-icon-share',
        '4': 'el-icon-s-claim',
        '5': 'el-icon-tickets',
        '6': 'el-icon-picture-outline',
        '7': 'el-icon-video-camera',
        '8': 'el-icon-video-play',
        '9': 'el-icon-folder-opened',
        '10': 'el-icon-s-data'
      },
      // 上半部循环
      forUpper: [
        {
          id: 1,
          index: '/situation',
          icon: 'el-icon-s-opportunity',
          title: '使用情况'
        },
        {
          id: 2,
          index: '/files',
          icon: 'el-icon-s-grid',
          title: '全部文件'
        },
        {
          id: 3,
          index: '/share',
          icon: 'el-icon-share',
          title: '分享文件'
        }
      ],
      // 下半部分循环
      forLower: [
        {
          id: 5,
          index: '/myFiles',
          icon: 'el-icon-tickets',
          title: '我的文档',
          text_num: ''
        },
        {
          id: 6,
          index: '/myImages',
          icon: 'el-icon-picture-outline',
          title: '我的图像',
          text_num: ''
        },
        {
          id: 7,
          index: '/myVideo',
          icon: 'el-icon-video-camera',
          title: '我的视频',
          text_num: ''
        },
        {
          id: 8,
          index: '/myMusic',
          icon: 'el-icon-video-play',
          title: '我的音乐',
          text_num: ''
        },
        {
          id: 9,
          index: '/otherFiles',
          icon: 'el-icon-folder-opened',
          title: '其他文件',
          text_num: ''
        }
      ],
      // 使用情况
      usage: {},
      // 是否折叠
      isCollapse: false
    }
  },

  methods: {
    handleSelect(key, keyPath) {
      // console.log(key, keyPath)
    },
    // 点击按钮，切换菜单的折叠和展开
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // 退出登录
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login2')
      this.$message({
        message: '退出登录',
        duration: 1000
      })
    },

    // 获取openId
    getOpenId() {
      this.openId = window.sessionStorage.getItem('token')
      this.userName = window.sessionStorage.getItem('userName')
      this.imagePath = window.sessionStorage.getItem('imagePath')
      if (this.imagePath) return (this.imageShow = true)
      return (this.imageShow = false)
    },
    // 请求数据
    async getSituationList() {
      this.situationUrl =
        'http://fangyulong.online:8703/warehouse?openid=' + this.openId
      const { data: res } = await this.$http.get(this.situationUrl)
      if (res.data.length == 0)
        return this.$message.error({ message: '获取数据失败', duration: 1000 })
      // console.log(res.data[0]);
      this.situationList = res.data

      this.forLower[0].text_num = this.situationList[0].document_num

      this.forLower[1].text_num = this.situationList[0].photo_num

      this.forLower[2].text_num = this.situationList[0].movie_num

      this.forLower[3].text_num = this.situationList[0].void_num

      this.forLower[4].text_num = this.situationList[0].other_num
    }
  },

  created() {
    this.getOpenId()
    this.getSituationList()
    // console.log(this.imageShow)
  }
}
</script>

<style lang="less" scoped>
.container_1 {
  height: 100%;
  .main-sidebar {
    .sidebar-menu {
      padding: 0;
      margin: 0;
      .badge {
        float: right;
        padding: 5px 10px;
        margin-top: 15px;
      }
      .menu-header {
        padding: 14px 15px;
        color: #cbcbcb;
        font-family: 'Montserrat';
        font-size: 15px;
        text-transform: uppercase;
        letter-spacing: 1.5px;
        font-weight: 600;
      }
    }
    .sidebar-user {
      display: inline-block;
      width: 100%;
      padding: 10px;
      margin-bottom: 10px;

      .sidebar-user-details {
        .user-name {
          white-space: nowrap;
          -o-text-overflow: ellipsis;
          text-overflow: ellipsis;
          overflow: hidden;
          margin-top: 7px;
          margin-bottom: 3px;
          font-weight: 600;
          color: #505c66;
        }
        .user-role {
          font-size: 12px;
          font-weight: 400;
          letter-spacing: 0.3;
          color: #868e96;
          font-family: 'Montserrat';
          font-size: 10px;
          letter-spacing: 0.5px;
        }
      }

      .sidebar-user-picture {
        float: left;
        margin-right: 10px;
        img {
          width: 50px;
          border-radius: 50%;
        }
      }
    }
    .sidebar-brand a {
      text-decoration: none;
      font-size: 16px;
      text-transform: uppercase;
      letter-spacing: 1.5px;
      font-family: 'Montserrat';
      font-weight: 700;
      color: #000;
    }
    .sidebar-brand {
      display: inline-block;
      width: 100%;
      text-align: center;
      height: 60px;
      line-height: 60px;
    }
  }
}

.main-wrapper {
  height: 100%;
}

.el-aside {
  background-color: #fde5e5;
  top: 0;
  left: 0;
  height: 100%;
  width: 250px;
  z-index: 880;
  box-shadow: 0 0 40px ragb(0, 0, 0, 0.05);
  .el-menu {
    font-weight: 800;
    background-color: #fff;
  }
}

.el-header {
  height: 70px;
  left: 250px;
  right: 5px;
  z-index: 890;
  background-color: transparent;
}

.el-main {
  width: 100%;
  position: relative;
}

.footer {
  font-family: 'Montserrat';
  padding: 20px 30px 20px 280px;
  font-size: 10px;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin-top: 40px;
  color: #999;
}
.navbar-bg {
  content: ' ';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 115px;
  background-color: #fde5e5;
  z-index: -1;
  .el-header {
    width: 100%;
  }
}

a {
  color: #574b90;
  font-weight: 500;
}

.p-3 {
  padding: 1rem !important;
}

.mb-4,
.my-4 {
  margin-bottom: 1.5rem !important;
}

.mt-4,
.my-4 {
  margin-top: 1.5rem !important;
}

.sidebar-button {
  display: flex;
  justify-content: center;
}

.el-menu-item .el-submenu {
  margin-left: 15px;
}

.badge-primary {
  color: #fff;
  background-color: #d62164;
}

.badge {
  display: inline-block;
  font-size: 75%;
  line-height: 1;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  font-weight: 500;
  letter-spacing: 0.5px;
  border-radius: 30px;
}

.el-menu-item i,
.el-submenu i {
  margin-right: 15px;
}

.main-wrapper .el-header .el-menu {
  padding: 0;
  margin: 0;
}

.el-header .header-right {
  width: 150px;
  height: 150px;
}

.el-submenu .el-menu-item {
  min-width: 150px;
}

.el-menu {
  border-right: 0;
}

.el-header {
  display: flex;
  justify-content: space-between;
  .header-left {
    width: 30px;
    height: 30px;
    color: #fff;
    padding-top: 5px;
    i {
      font-size: 25px;
    }
  }
  .header-right {
    .el-submenu {
      font-weight: 800;
      color: #fff;
      .el-button {
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15), 0 0 6px rgba(0, 0, 0, 0.15);
      }
    }
  }
}

.header-right .el-submenu .el-menu-item {
  padding-left: 10px !important;
}

.header-right .el-submenu .el-menu-item i {
  margin-right: 0;
}

.main-container {
  .el-footer {
    background-color: #fff;
    padding: 0 auto;
    text-align: center;
    line-height: 60px;
    .footer-left {
      .bullet {
        display: inline-block;
        padding-left: 5px;
      }
    }
  }
}

.sidebar-button {
  .el-button {
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.15), 0 0 6px rgba(0, 0, 0, 0.15);
  }
}

.btn-color:hover {
  background-color: #d62164;
  color: #fff;
}
</style>