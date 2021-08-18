<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="mainSide">
                <div style="margin-top:30px; width:70%; height:37px; margin-left:37px; border-radius:5px; font-weight:bold; text-align:center; padding-top:7px; font-size:18px; color: white; background-color: #6581A6;">SSAFY 사무국</div>
                <div id="listTop">
                    <div class="list1">
                        <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #6581A6;" name="list"/>
                        <span class="list2">그룹원 목록</span>
                    </div>
                    <div class="list1">
                        <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #6581A6;" name="send"/>
                        <span class="list2">그룹원 초대</span>
                    </div>
                </div>
            </div>
            <div id="mainContent">
                <span style="margin-left:60px; margin-top:40px; display:inline-block; font-weight:bold; font-size:25px;">목록</span>
                <span style="margin-left:565px; font-weight:bold; font-size:25px;">세부 정보</span>
                <div id="left"></div>
                <div id="right"></div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import Header from '../components/Header.vue'
import Side from '../Side.vue'

export default defineComponent({
  name: 'login',
  components: {
    Header,
    Side
  }
})
</script>

<style scoped>
#content{
    width:100%;
    height: 680px;
}
#main{
    width:95%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:whitesmoke;
}
#mainSide{
    width: 250px;
    height: 690px;
    border-right:1px solid rgb(212, 212, 212);
    position: absolute;
    top:60px;
    left:75px;
}
#listTop{
    width:80%;
    height:85px;
    margin:10px auto;
    border-bottom:1px solid rgb(216, 210, 210);
}
.list1{
    width:80%;
    margin-top:5px;
    margin-left:20px;
}
.list1:hover{
    background-color:rgb(216, 210, 210);
    cursor: pointer;
}
.list2{
    margin-left:15px;
}
#mainContent{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:60px;
    left:325px;
}
#left{
    width:550px;
    height:560px;
    border:1px solid #6581A6;
    position: absolute;
    top:80px;
    left:60px;
    background-color:#ffffff;
    overflow-y: scroll;
}
#right{
    width:400px;
    height:560px;
    border:1px solid #6581A6;
    position: absolute;
    top:80px;
    left:670px;
    background-color:#ffffff;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
div{
  font-family: 'NEXON Lv1 Gothic OTF';
}
span{
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
