<template>
  <div>
    <el-card>
      <span>
        <i class="el-icon-folder"></i>全部文件
      </span>
    </el-card>

    <el-card class="change">
      <!-- 面包屑导航 -->
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>根目录</el-breadcrumb-item>
        <!-- <el-breadcrumb-item>一级目录</el-breadcrumb-item>
        <el-breadcrumb-item>二级目录</el-breadcrumb-item>
        <el-breadcrumb-item>三级目录</el-breadcrumb-item>-->
      </el-breadcrumb>

      <el-row :gutter="80">
        <el-col :span="7">
          <!--搜索框-->
          <el-input placeholder="请输入文件名" v-model="Filename" clearable @clear="getAllFiles()">
            <el-button slot="append" icon="el-icon-search" @click="searchFile()"></el-button>
          </el-input>
        </el-col>

        <el-col :span="7">
          <span class="button_position">
            <!-- 上传文件按钮 -->
            <a id="form1" class="file" method="POST" enctype="multipart/form-data" ref="uploadfrom">
              <i class="el-icon-upload2"></i>上传文件
              <input type="file" id="fileSelect" @change="fileUploadFile" />
            </a>
            <!-- <el-button type="success" plain size="small">
              <i class="el-icon-upload2"></i>上传文件
            </el-button>-->

            <!-- 创建文件夹按钮 -->
            <el-button type="success" plain size="small" @click="showCreatedialog()">
              <i class="el-icon-folder-add"></i>创建文件夹
            </el-button>
          </span>
        </el-col>
      </el-row>

      <!--文件列表区-->
      <el-table :data="callBack">
        <el-table-column label="#" width="80px">
          <template slot-scope="scope">
            <i :class="iconMath(scope.row.type)" style="font-size:18px"></i>
          </template>
        </el-table-column>

        <el-table-column label="名称" prop="name">
          <template slot-scope="scope">
            <div v-if="scope.row.file_folder_id >= 0">
              <!-- <router-link :to="{name:'one', query:{id:scope.row.file_folder_id}}">{{scope.row.name}}</router-link> -->
              <router-link
                :to="{path:'/folderOne', query:{id:scope.row.file_folder_id,parent_id:scope.row.parent_folder_id,oneName:scope.row.name}}"
              >{{scope.row.name}}</router-link>
              <!-- <router-link :to="{name:'detail',query:{id:index}}">查看详情</router-link> -->
              <!-- <a :href="'/files/'+scope.row.file_folder_id">{{scope.row.name}}</a> -->
            </div>
            <div v-else>{{scope.row.name}}</div>
          </template>
        </el-table-column>
        <el-table-column label="类型" prop="type"></el-table-column>
        <el-table-column label="扩展名" prop="postfix"></el-table-column>
        <el-table-column label="大小" prop="size"></el-table-column>
        <el-table-column label="下载次数" prop="download"></el-table-column>
        <el-table-column label="上传时间">
          <template slot-scope="scope">
            {{ scope.row.upTime|formatDate }}
            <!-- {{scope.row.upTime}} -->
          </template>
        </el-table-column>
        <!--文件操作区域-->
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <!-- -----------------------------------文件操作域 --------------------------------------->
            <div v-if="scope.row.my_file_id >= 0">
              <!--分享文件按钮-->
              <el-tooltip class="item" effect="dark" content="分享此文件" placement="top" hide-after:600>
                <el-button
                  type="plain"
                  icon="el-icon-share"
                  circle
                  @click="showShareDialog(scope.row.my_file_id)"
                ></el-button>
              </el-tooltip>
              <!--重命名按钮-->
              <el-tooltip
                class="item"
                effect="dark"
                content="重命名此文件"
                placement="top"
                hide-after:600
              >
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  circle
                  @click="showEditDialog(scope.row.my_file_id)"
                ></el-button>
              </el-tooltip>
              <!--删除按钮-->
              <el-tooltip class="item" effect="dark" content="删除此文件" placement="top" hide-after:600>
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  @click="showDeleteDialog(scope.row.my_file_id)"
                ></el-button>
              </el-tooltip>
            </div>
            <!-----------------------------------------文件夹操作域------------------------------------------->
            <div v-else>
              <!-- 鼠标点击按钮
              <el-tooltip class="item" effect="dark" content="点击文件夹" placement="top" hide-after:600>
                <el-button type="info" icon="el-icon-mouse" circle></el-button>
              </el-tooltip>-->
              <!--重命名文件夹按钮-->
              <el-tooltip
                class="item"
                effect="dark"
                content="重命名文件夹"
                placement="top"
                hide-after:600
              >
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  circle
                  @click="showEditFolderDialog(scope.row.file_folder_id)"
                ></el-button>
              </el-tooltip>
              <!--删除文件夹按钮-->
              <el-tooltip class="item" effect="dark" content="删除文件夹" placement="top" hide-after:600>
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  @click="showDeleteFolderDialog(scope.row.file_folder_id)"
                ></el-button>
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!----------------------------------------弹出对话框----------------------------------------------->
    <!--创建文件夹弹出对话框-->
    <el-dialog
      title="创建文件夹"
      :visible.sync="CreatedialogVisible"
      width="50%"
      @close="CreatedialogVisibleClosed"
    >
      <!--创建文件夹表单弹出对话框-->
      <el-form
        :model="createFolder"
        :rules="createFolderRules"
        ref="createFolderRef"
        label-width="120px"
      >
        <el-form-item label="文件夹的名称" prop="Foldername">
          <el-input v-model="createFolder.Foldername"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="CreatedialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addFolder()">确 定</el-button>
      </span>
    </el-dialog>

    <!--文件重命名弹出对话框-->
    <el-dialog
      title="文件重命名"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="EditdialogVisibleClosed"
    >
      <el-form
        :model="editFilename"
        :rules="editFilenameRules"
        ref="editFilenameRef"
        label-width="100px"
      >
        <!--重命名表单-->
        <el-form-item label="新文件名称" prop="newFilename">
          <el-input v-model="editFilename.newFilename"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="EditFilename()">确 定</el-button>
      </span>
    </el-dialog>

    <!--文件夹重命名弹出对话框-->
    <el-dialog
      title="文件夹重命名"
      :visible.sync="editFolderDialogVisible"
      width="50%"
      @close="EditFolderdialogVisibleClosed"
    >
      <el-form
        :model="editFoldername"
        :rules="editFoldernameRules"
        ref="editFoldernameRef"
        label-width="120px"
      >
        <!--文件夹重命名表单-->
        <el-form-item label="新文件夹名称" prop="newFoldername">
          <el-input v-model="editFoldername.newFoldername"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editFolderDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="EditFoldername()">确 定</el-button>
      </span>
    </el-dialog>

    <!--设置分享弹出对话框-->
    <el-dialog title="设置文件为共享文件" :visible.sync="shareDialogVisible" width="30%">
      <span>是否确定分享此文件，分享后，此文件将显示在共享文件中</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="shareDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="Sharefile()">确 定</el-button>
      </span>
    </el-dialog>

    <!--设置删除文件按钮对话框-->
    <el-dialog title="删除此文件" :visible.sync="deleteDialogVisible" width="30%">
      <span>确定删除此文件</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="Deletefile()">确 定</el-button>
      </span>
    </el-dialog>

    <!--设置删除文件夹按钮对话框-->
    <el-dialog title="删除此文件夹" :visible.sync="deleteFolderDialogVisible" width="30%">
      <span>确定删除此文件夹</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteFolderDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="Deletefolder()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// import 'https://cdn.bootcss.com/url-search-params/0.10.0/url-search-params.js'
export default {
  data() {
    return {
      //根目录id
      root: '',
      // 请求文件夹url
      folderUrl: '',
      // 请求文件url
      fileUrl: '',
      // 搜索文件url
      searchUrl: '',
      // 返回数据
      callBack: [],

      //搜索文件接口请求参数
      openId: '',
      Filename: '',

      //控制对话框的显示
      CreatedialogVisible: false,
      editDialogVisible: false,
      shareDialogVisible: false,
      deleteDialogVisible: false,
      editFolderDialogVisible: false,
      deleteFolderDialogVisible: false,

      //添加创建文件夹表单数据
      createFolder: {
        Foldername: '',
        FolderId: ''
      },

      //创建修改文件名表单
      editFilename: {
        newFilename: ''
      },

      //创建修改文件夹名称表单
      editFoldername: {
        newFoldername: ''
      },

      //添加创建文件夹表单的验证规则
      createFolderRules: {
        Foldername: [
          { required: true, message: '请输入添加文件夹名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      },

      //添加重命名文件的验证规则
      editFilenameRules: {
        newFilename: [
          { required: true, message: '请输入新文件名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      },

      //添加重命名文件夹的验证规则
      editFoldernameRules: {
        newFoldername: [
          { required: true, message: '请输入新文件夹名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      },
      // 按钮子id
      child_id: '',

      //重命名获取的请求参数文件id
      FileId: '',
      //设置分享的请求参数文件id
      file_id: '',
      //删除文件的请求参数文件id
      FileID: '',
      //重命名文件夹获取的文件夹id
      FolderId: '',
      //删除文件夹按钮获取的文件夹id
      FolderID: ''
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

  //创建生命周期函数
  created() {
    this.getOpenId()
    this.folderUrl =
      'http://fangyulong.online:8702/homeFolder?openid=' + this.openId
    this.fileUrl =
      'http://fangyulong.online:8702/homeFile?openid=' + this.openId
    // 请求根文件夹下文件夹
    this.globalGetHomeFolder(this.folderUrl)
    // 请求根文件夹下文件
    this.globalGetHomeFile(this.fileUrl)
  },

  // mounted() {
  //   this.searchFile()
  // },

  methods: {
    //获取openID
    getOpenId() {
      this.openId = window.sessionStorage.getItem('token')
      this.root = window.sessionStorage.getItem('root')
    },

    /***************************************上传文件文件**************************************** */
    fileUploadFile() {
      let id = this.root
      // console.log(id)
      this.fileUpload(event, id)
    },
    // // 上传文件
    // fileUpload(event){
    //       // 上传文件
    //               // console.log(event);

    //               let file = event.target.files[0]
    //               // console.log(file);

    //               let formData = new FormData()
    //               // // this.callBack[0].parent_folder_id
    //               formData.append('FolderId', this.root)
    //               formData.append('File', file)

    //             // 文件上传
    //             let config = {
    //                 headers:{'Content-Type':'multipart/form-data'}
    //             }

    //             let that = this
    //             this.$http.post('http://fangyulong.online:8702/uploadFolder',formData,config).then(function(response){
    //                 console.log(response);
    //                 if (response.data.msg == '操作成功'){
    //                   console.log(response.data.msg)
    //                   that.$message.success({ message: '上传成功', duration: 1000 })
    //                 }
    //                 that.getAllFiles()
    //             }).catch(function(err){
    //                 console.log(err)
    //                 that.$message.error({ message: '上传失败', duration: 1000 })
    //                 that.getAllFiles()
    //             })

    //             },
    /***************************************创建文件夹**************************************** */
    //监听创建文件夹对话框关闭的事件
    CreatedialogVisibleClosed() {
      this.$refs.createFolderRef.resetFields()
    },

    //点击创建文件夹按钮，显示创建文件夹对话框，获取参数
    showCreatedialog() {
      this.CreatedialogVisible = true
    },

    //点击确定按钮，发送发送请求，并刷新表格
    addFolder() {
      this.$refs.createFolderRef.validate(async valid => {
        if (!valid) return this.$message.error('不符合规范，请重新编辑')
        //发起添加文件夹的网络请求
        let addFolderUrl =
          'http://fangyulong.online:8702/createFolder?Foldername=' +
          this.createFolder.Foldername +
          '&&FolderId=' +
          this.callBack[0].parent_folder_id
        const { data: res } = await this.$http.get(addFolderUrl)
        this.CreatedialogVisible = false
        if (res.code !== 0) {
          return this.$message.error('创建文件夹失败')
        }
        this.getAllFiles()
        return this.$message.success('创建文件夹成功')
      })
    },

    /**************************************文件搜索************************************** */
    //文件搜索
    async searchFile() {
      if (this.Filename !== '') {
        this.callBack = []
        this.searchUrl =
          'http://fangyulong.online:8702/findFile?Filename=' +
          this.Filename +
          '&&openid=' +
          this.openId
        this.globalGetHomeFile(this.searchUrl)

        // const { data: res } = await this.$http.get(
        //         'http://fangyulong.online:8702/findFile',
        //         {
        //           params: { Filename: this.Filename, openid: this.openId }
        //         }
        //       )
        // this.callBack = [];
      }
    },

    //点击clearble返回所有文件和文件夹
    getAllFiles() {
      // this.callBack = '';
      this.callBack = []
      // 请求根文件夹下文件夹
      this.globalGetHomeFolder(this.folderUrl)
      // 请求根文件夹下文件
      this.globalGetHomeFile(this.fileUrl)
    },

    /*******************************重命名文件************************************* */
    //展示文件重命名对话框,将作用域插槽id全局注册
    showEditDialog(my_file_id) {
      this.child_id = my_file_id
      this.editDialogVisible = true
    },

    //监听重命名对话框关闭的事件
    EditdialogVisibleClosed() {
      this.$refs.editFilenameRef.resetFields()
    },

    //修改文件名称;表单预验证;并关掉对话框;
    EditFilename() {
      //表单预验证
      this.$refs.editFilenameRef.validate(async valid => {
        if (!valid) return this.$message.error('不符合规范，请重新编辑')
        //发起数据请求
        const { data: res } = await this.$http.get(
          'http://fangyulong.online:8702/modifyFile',
          {
            params: {
              newFilename: this.editFilename.newFilename,
              FileId: this.child_id
            }
          }
        )
        if (res.code !== 0) {
          return this.$message.error('修改失败')
        }
        return this.$message.success('修改成功')
      })
      this.getAllFiles()
      this.editDialogVisible = false
    },
    /*******************************重命名文件夹***************************************** */
    //展示文件夹重命名对话框,将作用域插槽id全局注册
    showEditFolderDialog(file_folder_id) {
      this.child_id = file_folder_id
      this.editFolderDialogVisible = true
    },

    //监听重命名对话框关闭的事件
    EditFolderdialogVisibleClosed() {
      this.$refs.editFoldernameRef.resetFields()
    },

    //修改文件夹名称;表单预验证;并关掉对话框;
    EditFoldername() {
      //表单预验证
      this.$refs.editFoldernameRef.validate(async valid => {
        if (!valid) return this.$message.error('不符合规范，请重新编辑')
        //发起数据请求
        const { data: res } = await this.$http.get(
          'http://fangyulong.online:8702/modifyFolder',
          {
            params: {
              newFoldername: this.editFoldername.newFoldername,
              FolderId: this.child_id
            }
          }
        )
        if (res.code !== 0) {
          return this.$message.error('修改失败')
        }
        this.getAllFiles()
        return this.$message.success('修改成功')
      })
      this.editFolderDialogVisible = false
    },

    /***************************************设置文件共享****************************************** */
    //分享对话框
    showShareDialog(my_file_id, sharing_id) {
      this.child_id = my_file_id
      this.shareDialogVisible = true
    },

    //设置文件为共享文件
    async Sharefile() {
      const { data: res } = await this.$http.get(
        ' http://fangyulong.online:8702/setFileShared?file_id=' + this.child_id
      )
      this.shareDialogVisible = false
      if (res.code !== 0) {
        return this.$message.error('分享失败')
      }
      return this.$message.success('分享成功')
    },
    /**************************删除文件**************************************************** */
    //展示删除对话框
    showDeleteDialog(my_file_id) {
      this.child_id = my_file_id
      this.deleteDialogVisible = true
    },

    //对话框确定按钮发送删除请求
    async Deletefile() {
      const { data: res } = await this.$http.get(
        'http://fangyulong.online:8702/deleteFile?FileId=' + this.child_id
      )
      this.deleteDialogVisible = false
      if (res.code !== 0) {
        return this.$message.error('删除文件失败')
      }
      this.getAllFiles()
      return this.$message.success('删除文件成功')
    },

    /**************************删除文件夹***************************************************** */
    //点击删除文件夹按钮展示对话框并获取文件夹id
    showDeleteFolderDialog(file_folder_id) {
      this.child_id = file_folder_id
      this.deleteFolderDialogVisible = true
    },

    //请求删除文件夹接口
    async Deletefolder() {
      const { data: res } = await this.$http.get(
        'http://fangyulong.online:8702/deleteFolder?FolderId=' + this.child_id
      )
      this.deleteFolderDialogVisible = false
      if (res.code !== 0) {
        return this.$message.error('删除文件夹失败')
      }
      this.getAllFiles()
      return this.$message.success('删除文件夹成功')
    }
  }
}
</script>

<style lang="less" scoped>
.change {
  margin-top: 30px;
}
.button_position {
  text-align: center;
  position: relative;
  width: 100%;
}
.el-breadcrumb {
  font-size: 16px;
}
.file {
  position: relative;
  display: inline-block;
  background: #f0f9eb;
  border: 1px solid #c2e7b0;
  border-radius: 4px;
  padding: 4px 12px;
  overflow: hidden;
  color: #67c23a !important;
  text-decoration: none;
  text-indent: 0;
  line-height: 20px;
  top: 11px;
  right: 5px;
  font-size: 12px;
  font-weight: 500;
  width: 104px;
  height: 32px;
}
.file input {
  position: absolute;
  font-size: 100px;
  right: 0;
  top: 0;
  opacity: 0;
}
.file:hover {
  background: #67c23a;
  border-color: #67c23a;
  color: #fff !important;
  text-decoration: none;
}
</style>