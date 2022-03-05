<template>
    <div>
        <div class="login_container" >
            <div class="login_background" >
                <img src="../../assets/背景5.jpg" class="img" width="fullWidth">
            </div>

            <div>
                <div class="avatar_box" >
                    <img src="../../assets/瑞翼2.jpg" class="icon_img" width="fullWidth">
                </div>

                <div class="text_title">
                    <img src="../../assets/云盘txt.png" class="title">
                    <!-- <span class="line"></span> -->
                    <img src="../../assets/云盘小标.png" class="title2" >
                    <el-row class="btn">
                         <el-button  round @click="login" class="loginClick">Login</el-button>
                    </el-row>
                </div>

                <!-- <span class="spann">瑞 翼</span> -->
   
            </div>
            
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            
           
        }
    },



    methods:{
        //点击登录跳转
        login(){
            //2. 通过编程式导航跳转到登录，路由地址是 /login2
                this.$router.push('/login2')
        },

        

        
    }
}
</script>

<style lang="less" scoped>
.login_container{
    height: 100%;
    
    
    
}
.login_background{
    height: 100%;
    width: 100%;

    // background-image: url("../assets/背景5.jpg");
    background-size: cover; /* 使图片平铺满整个浏览器（从宽和高的最大需求方面来满足，会使某些部分无法显示在区域中） */
    position: absolute; 
    z-index: -1;
    background-repeat: no-repeat;
    
    
    
    .img{
        // position: relative;
        height: 100%;
        width: 100%;
        
    }
}

.avatar_box{
    // border: 1px solid #eee;
    border-radius: 50%;
    height: 100px;
    width: 100px;
    padding: 6px;
    box-shadow: 0 0 10px #edd;
    position: absolute;
    left: 2%;
    top: 10%;
    // left: 50%;
    // transform: translate(-50%,-50%);
    background-color: #fff;
    .icon_img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
    }
}

.text_title{
    position: relative;
    width: 100%;
    height: 100%;

    .title{
        display: flex;
        position: absolute;
        top: -60px;
        
        height: 550px;
        width: 550px;
    }
    .line{
        display: flex;
        position: absolute;
        background-color: #58294b;
        height: 2px;
        width: 250px;
        top: 250px;
        left: 18%;
    }
    .title2{
        position: absolute;
        height: 100px;
        width:250px;
        top: 220px;
        left: 18%;
    }
    .btn{
        position: absolute;
        top:290px;
        left: 30%;
        .loginClick:hover{
            border: none;
            color: white;
            background-color: purple;
            height: 100%;
            width: 100%;            
        }
    }
}


.spann{
    position: relative;
    font-size:17px;
    color: aliceblue; 
    top: 10px;
    left: 12px;
}


</style>