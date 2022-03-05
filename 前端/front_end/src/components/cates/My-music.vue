<template>
  <div class="container">
    <el-card class="box_card">
      <p>我的文档</p>
    </el-card>

    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>分类文件</el-breadcrumb-item>
      <el-breadcrumb-item>我的文档</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <!-- 表格区 -->
      <el-table :data="callBack" stripe style="width: 100%">
        <!-- <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="file_folder_name" label="名称"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="postfix" label="扩展名"></el-table-column>
        <el-table-column prop="size" label="大小"></el-table-column>
        <el-table-column prop="download" label="下载次数"></el-table-column>
        <el-table-column prop="time" label="上传日期"></el-table-column>-->

        <!-- <el-table-column type="index" label="#"></el-table-column> -->

        <el-table-column label="#" width="80px">
          <template slot-scope="scope">
            <i :class="iconMath(scope.row.type)" style="font-size:18px"></i>
          </template>
        </el-table-column>

        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="postfix" label="扩展名"></el-table-column>
        <el-table-column prop="size" label="大小"></el-table-column>
        <el-table-column prop="download" label="下载次数"></el-table-column>
        <el-table-column prop="upTime" label="上传日期"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 下载按钮 -->
            <el-tooltip effect="dark" content="下载" placement="top" :enterable="false">
              <a
                :href="'http://fangyulong.online:8702/downloadFolder?FileId=' + scope.row.my_file_id"
              >
                <el-button type="primary" icon="el-icon-download" size="mini"></el-button>
              </a>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区 -->
      <!-- <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="queryInfo.pagenum"
              :page-sizes="[2, 4]"
              :page-size="queryInfo.pagesize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
      </el-pagination>-->
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //获取文件对象
      // textList: []
      callBack: []
    }
  },

  computed: {
    // 计算图标
    iconMath() {
      return function(o) {
        if (o == '文件夹') return 'el-icon-folder-opened'
        if (o == '图像类型') return 'el-icon-picture-outline'
        if (o == '其他类型') return 'el-icon-present'
        if (o == '文本类型') return 'el-icon-document'
        return null
      }
    }
  },

  created() {
    //获取openId
    this.getOpenId()
    //数据请求根目录下所有文件
    this.getTextList()
  },

  methods: {
    //获取openId
    getOpenId() {
      this.openId = window.sessionStorage.getItem('token')
    },
    //获取根目录下所有文件
    async getTextList() {
      this.situationUrl =
        'http://fangyulong.online:8702/getMusic?openid=' + this.openId
      let { data: res } = await this.$http.get(this.situationUrl)
      if (res.code !== 0) {
        return this.$message.error('获取失败')
      }

      // this.textList = res.data
      // console.log(res)

      let that = this
      res.data.forEach(function(item, index) {
        let dataPush = {}
        dataPush.my_file_id = item.my_file_id
        // dataPush.typeIcon = item.type
        dataPush.name = item.my_file_name
        dataPush.type = item.type
        dataPush.postfix = item.postfix
        dataPush.size = item.size
        dataPush.download = item.download_time
        dataPush.upTime = item.upload_time
        that.callBack.push(dataPush)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  .box_card {
    margin-bottom: 20px;
    p {
      margin-left: 10px;
      font-weight: bold;
    }
  }

  el-card__body {
    padding: 0;
    // padding: 5rpx;
    // padding-left: 10px;
  }
  .box-card {
    padding: 20px;
  }
}
</style>