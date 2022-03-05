<template>
    <div class="situation">
        <el-row class="situation-header">
            <el-card style="color:#D62164">
                <i class="el-icon-s-opportunity"></i>
                <span>使用情况</span>
            </el-card>
        </el-row>
        <el-row class="situation-bottom">
            <el-row class="left">
                <el-card class="card-upper">
                    <div class="upper-content">
                        <div class="upper-content-file"><i class="el-icon-folder"></i></div>
                        <div class="upper-content-cont">
                            <el-row class="one">文件夹</el-row>
                            <el-row class="two">{{situationList[0].folder}}</el-row>
                        </div>
                    </div>
                </el-card>
                <el-card class="card-bottom">
                    <div class="card-bottom-title">文件明细情况</div>
                    <el-table :data="situationList" style="font-size: 16px">>
                        <el-table-column label="文档" prop="document_num" align="center"></el-table-column>
                        <el-table-column label="图象" prop="photo_num" align="center"></el-table-column>
                        <el-table-column label="视频" prop="movie_num" align="center"></el-table-column>
                        <el-table-column label="音乐" prop="void_num" align="center"></el-table-column>
                        <el-table-column label="其他" prop="other_num" align="center"></el-table-column> 
                    </el-table>  
                </el-card>   
            </el-row>
            <el-row class="center">
                <el-card class="card-upper">
                    <div class="upper-content">
                        <div class="upper-content-file"><i class="el-icon-document"></i></div>
                        <div class="upper-content-cont">
                            <el-row class="one">文件</el-row>
                            <el-row class="two">{{situationList[0].file}}</el-row>
                        </div>
                    </div> 
                </el-card>
                <el-card class="card-bottom">
                    <div class="card-bottom-title">已使用/剩余容量（单位: MB）</div>
                    <el-table :data="situationList" style="font-size: 16px">
                        <el-table-column label="已使用" prop="use_capacity" align="center"></el-table-column>
                        <el-table-column label="剩余容量" prop="current_num" align="center"></el-table-column> 
                    </el-table>
                  
                </el-card>   
            </el-row>
            <el-row class="right">
                <el-card class="right-content">
                    <div class="one"><h2>来自系统通知</h2></div>
                    <div class="two">
                        <p>工坊网盘是由工坊团队开发推出的在线存储服务。</p>
                    </div>
                    <div class="three"><p>用户可以轻松将自己的文件上传到网盘上，并可跨终端随时随地查看和分享。</p>
                                    <P>无需注册，使用QQ一键登录，注重用户的隐私。</P>
                                    <p>具有安全、简约、高效、不限速的特点。</p>
					</div>
                </el-card>   
            </el-row>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            // 请求路径
            situationUrl: '',
            // openId
            openId: '',
            // 总数据
            situationList: [{
                open_id: '',
                folder: 0,
                file: 0,
                use_capacity: 0,
                current_num: 0,
                document_num: 0,
                photo_num: 0,
                movie_num: 0,
                void_num: 0,
                other_num: 0,
            }]
        }
    },
    methods:{
        // 获取openId 
        getOpenId() {
            this.openId = window.sessionStorage.getItem('token');
        },
        // 请求数据
        async getSituationList() {
                this.situationUrl = 'http://fangyulong.online:8703/warehouse?openid=' + this.openId;
                const { data:res } = await this.$http.get(this.situationUrl);
                if (res.data.length == 0) return this.$message.error({message: '获取数据失败',duration: 1000})
                // console.log(res.data[0]);
                this.situationList = res.data;
                // console.log(this.situationList)      
        }
    },
    created() {
        this.getOpenId();
        this.getSituationList();
    },
}
</script>

<style lang="less" scoped>
.situation {
    margin: 10px;
    // 头部
    .situation-header {
        margin: -7px 0 20px 0;
        font-weight: 700;
        margin-bottom: 20px;
        font-size: 25px;
        width: 100%;
        i {
            margin-right: 8px;
        }
    }
    
    .situation-bottom {
        display: flex;
        .left {
            flex:1;
            .card-upper {
                .upper-content {
                    width: 100%;
                    display: flex;
                    justify-content:space-between;
                    align-items:center;
                    .upper-content-file {
                        width: 60px;
                        height: 60px;
                        border-radius: 50%;
                        line-height: 60px;
                        font-size: 28px;
                        background-color: #F68090;
                        text-align: center;
                        color: #fff;
                    }
                    .upper-content-cont {
                        .one {
                            font-size: 16px;
                            font-weight: 800;
                        }
                        .two {
                            font-size: 16px;
                            font-weight: 800;
                            text-align: right;
                        }
                    }
                }
            }
            .card-bottom{
                margin-top: 30px;
                .card-bottom-title {
                    border-bottom: 1px solid rgba(0,0,0,.125);
                    padding-bottom: 20px;
                    font-weight: 800;
                    letter-spacing: 2px;
                    font-size: 20px;
                    text-align:center;
                }
            }
        }
        .center {
            flex: 1;
            margin: 0 30px;
            .card-upper {
                .upper-content {
                    width: 100%;
                    display: flex;
                    justify-content:space-between;
                    align-items:center;
                        .upper-content-file {
                            width: 60px;
                            height: 60px;
                            border-radius: 50%;
                            line-height: 60px;
                            font-size: 28px;
                            background-color: #FBC1B5;
                            text-align: center;
                            color: #fff;
                        }
                        .upper-content-cont {
                            .one {
                                font-size: 16px;
                                font-weight: 800;
                            }
                            .two {
                                font-size: 16px;
                                font-weight: 800;
                                text-align: right;
                            }
                        }
                }
            }            
            .card-bottom{
                margin-top: 30px;
                .card-bottom-title {
                    border-bottom: 1px solid rgba(0,0,0,.125);
                    padding-bottom: 20px;
                    font-weight: 800;
                    letter-spacing: 2px;
                    font-size: 20px;
                    text-align:center;
                }
            }
        }
        .right {
            flex:1;
            .right-content {
                .two {
                    font-weight: 600;
                    text-indent: 2em;
                }
                .three {
                    text-indent: 2em;
                    font-weight: 600;
                    p {
                        display: block;
                        margin-block-start: 0em;
                        margin-block-end: 0em;
                        margin-inline-start: 0px;
                        margin-inline-end: 0px;
                    }
                }
            }
        }

    }
}

</style>