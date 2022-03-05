import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'

// 导入字体图标
import './assets/fonts/iconfont.css'
// 导入全局样式
import './assets/css/global.css'

// 配置路由
import axios from 'axios'
// axios.defaults.baseURL = 'http://fangyulong.online:8701/sugoNetdisk/'
Vue.prototype.$http = axios

Vue.config.productionTip = false


// 请求各个文件夹下文件
Vue.prototype.globalGetHomeFile = async function (url) {

  const { data: res } = await this.$http.get(url)
  // console.log(res.data)
  let that = this
  res.data.forEach(function (item, index) {

    let dataPush = {}
    dataPush.my_file_id = item.my_file_id
    dataPush.parent_folder_id = item.parent_folder_id
    // dataPush.typeIcon = item.type
    dataPush.name = item.my_file_name
    dataPush.type = item.type
    dataPush.postfix = item.postfix
    dataPush.size = item.size
    dataPush.download = item.download_time
    dataPush.upTime = item.upload_time
    dataPush.sharing = item.sharing
    // console.log(that.callBack)
    that.callBack.push(dataPush)
    // console.log(that.callBack)                  
  })
}

// 请求各个文件夹下文件夹
Vue.prototype.globalGetHomeFolder = async function (url) {
  const { data: res } = await this.$http.get(url)
  // console.log(res.data)
  if (res.data !== '') {
    let that = this
    res.data.forEach(function (item, index) {

      let dataPush = {}
      dataPush.file_folder_id = item.file_folder_id
      dataPush.parent_folder_id = item.parent_folder_id
      // dataPush.typeIcon = item.file_folder_id
      dataPush.name = item.file_folder_name
      dataPush.type = '文件夹'
      dataPush.postfix = '-'
      dataPush.size = '-'
      dataPush.download = '-'
      dataPush.upTime = item.time
      that.callBack.push(dataPush)
    })
  }
}


// 日期过滤器
Vue.filter('formatDate', function (value) {
  value = value.toString()
  var year = value.substring(0, 10)
  var time = value.substring(11, 19)
  return year + '-' + time;
})


// 上传文件
Vue.prototype.fileUpload = function (event, id) {

  let file = event.target.files[0]


  let formData = new FormData()

  formData.append('FolderId', id)
  formData.append('File', file)


  // 文件上传
  let config = {
    headers: { 'Content-Type': 'multipart/form-data' }
  }

  let that = this
  this.$http.post('http://fangyulong.online:8702/uploadFolder', formData, config).then(function (response) {

    if (response.data.msg == '操作成功') {

      that.$message.success({ message: '上传成功', duration: 1000 })
    }
    that.getAllFiles()
  }).catch(function (err) {

    that.$message.error({ message: '上传失败', duration: 1000 })
    that.getAllFiles()
  })
}

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
