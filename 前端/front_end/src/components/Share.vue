<template>
  <div>
    <el-card>
      <span>
        <i class="el-icon-share"></i>分享文件
      </span>
    </el-card>
    <!--卡片区域-->
    <el-card class="margin_change">
      <el-row :gutter="80">
        <el-col :span="7">
          <!--搜索框-->
          <el-input placeholder="请输入分享文件名" v-model="file_name" clearable @clear="getShareFiles()">
            <el-button slot="append" icon="el-icon-search" @click="searchSharedFiles()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="3"></el-col>
      </el-row>
      <!--显示分享文件表单-->
      <el-table :data="ShareFilesList">
        <!-- <el-table-column type="selection"></el-table-column> -->

        <el-table-column label="#" width="80px">
          <template slot-scope="scope">
            <i :class="iconMath(scope.row.type)" style="font-size:18px"></i>
          </template>
        </el-table-column>
        <el-table-column label="名称" prop="my_file_name"></el-table-column>
        <el-table-column label="分享者" prop="user_name"></el-table-column>
        <el-table-column label="文件类型" prop="type"></el-table-column>
        <el-table-column label="文件大小" prop="size"></el-table-column>
        <el-table-column label="下载次数" prop="download_time"></el-table-column>
        <el-table-column label="分享时间" prop="upload_time"></el-table-column>
        <el-table-column label="操作" width="170px">
          <template slot-scope="scope">
            <!--操作的内部插槽-->
            <!--下载按钮-->
            <el-tooltip class="item" effect="dark" content="下载此文件" placement="top" hide-after:600>
              <a
                :href="'http://fangyulong.online:8702/downloadFolder?FileId=' + scope.row.my_file_id"
              >
                <el-button
                  type="primary"
                  icon="el-icon-download"
                  size="mini"
                  class="button_position1"
                ></el-button>
              </a>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="是否取消分享" placement="top" hide-after:600>
              <el-button
                type="primary"
                icon="el-icon-share"
                size="mini"
                @click="showShareDialog(scope.row.my_file_id)"
                class="button_position2"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!----------------------------------------弹出对话框---------------------------------------------------->
    <!--设置分享弹出对话框-->
    <el-dialog title="取消文件共享" :visible.sync="shareDialogVisible" width="30%">
      <span>是否确定取消分享此文件，取消后，将不再分享文件中显示</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="shareDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="Sharefile()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  // 获取用户列表的参数对象
  data() {
    return {
      ShareFilesList: [
        {
          user_name: '',
          my_file_name: '',
          upload_time: '',
          download_time: '',
          type: '',
          size: '',
          my_file_id: ''
        }
      ],

      //搜索文件的请求参数
      file_name: '',
      //分享文件对话框的显示
      shareDialogVisible: false,
      //分享文件的请求参数文件id
      file_id: ''
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
    this.getShareFiles()
  },

  mounted() {
    this.searchSharedFiles()
  },

  methods: {
    /************************************获取分享文件************************************* */
    //获取分享文件列表
    async getShareFiles() {
      const { data: res } = await this.$http.get(
        'http://fangyulong.online:8703/Shared'
      )
      if (res.code !== 0) {
        return this.$message.error('请求数据失败')
      }

      this.ShareFilesList = res.data
    },

    /**********************************共享文件搜索************************************************** */
    //文件搜索函数,模糊搜索
    async searchSharedFiles() {
      const { data: res } = await this.$http.get(
        'http://fangyulong.online:8703/SharedFind?file_name=' + this.file_name
      )
      if (res.code !== 0) {
        return this.$message.error('搜索共享文件失败')
      }
      this.ShareFilesList = res.data
    },

    //下载文件，请求参数文件id

    /***********************************取消文件分享**********************************************/
    //分享对话框
    showShareDialog(my_file_id) {
      this.file_id = my_file_id
      this.shareDialogVisible = true
    },
    //取反，取消文件共享
    async Sharefile() {
      const { data: res } = await this.$http.get(
        ' http://fangyulong.online:8702/setFileShared?file_id=' + this.file_id
      )
      this.shareDialogVisible = false
      if (res.code !== 0) {
        return this.$message.error('取消分享失败')
      }
      this.getShareFiles()
      return this.$message.success('取消分享成功')
    }
  }
}
</script>

<style lang="less" scoped>
.margin_change {
  margin-top: 30px;
}

.button_position1 {
  margin-left: 10px;
}
.button_position2 {
  margin-left: 20px;
}
</style>