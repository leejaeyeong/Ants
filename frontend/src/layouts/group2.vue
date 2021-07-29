<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="top">
                SSAFY 사무국
            </div>
            <div id="bot">
                <div style="float:right;
                margin-right:30px;
                margin-top:15px;
                font-weight:bold;
                font-size:18px;">관리자 : 정재윤(프로)</div>
                <div id="b1">회원 목록</div>
                <div id="b2">
                    <div id="table"></div>
                </div>
                <div id="b3">
                    <div id="detail"></div>
                    <q-btn color="purple" text-color="white" label="수정하기" style="font-size:16px; width:46%; margin-left:10px; margin-top:280px;"/>
                    <q-btn color="deep-orange" text-color="white" label="삭제하기" style="font-size:16px; width:46%; margin-left:10px; margin-top:280px;" />
                </div>
                <div id="b4">
                    <div id="invite">
                        <q-icon style="margin-bottom:10px; font-size: 3.8em; color:whitesmoke;" name="person_add"/>
                        <span style="margin-top:10px; font-weight:bold; font-size:25px; color:whitesmoke; margin-left:10px;">그룹 초대하기</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'

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
#top{
    width:300px;
    height:60px;
    position:absolute;
    top:90px;
    left:110px;
    border-radius: 15px;
    background-color: #6581A6;
    font-weight:bold;
    font-size:30px;
    padding-top:8px;
    text-align:center;
}
#bot{
    width:1340px;
    height:550px;
    position:absolute;
    top:180px;
    left:110px;
    border-radius: 15px;
    background-color: #B0BAD9
}
#b1{
    width:250px;
    height:50px;
    position:absolute;
    top:30px;
    left:30px;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
    background-color: #6581A6;
    font-size:30px;
    font-weight:bold;
    padding-left:20px;
    padding-top:7px;
}
#b2{
    width:850px;
    height:440px;
    position:absolute;
    top:80px;
    left:30px;
    border-top-right-radius: 15px;
    border-bottom-left-radius: 15px;
    border-bottom-right-radius: 15px;
    background-color: #6581A6;
}
#table{
    width:830px;
    height:420px;
    position:absolute;
    top:10px;
    left:10px;
    background-color: whitesmoke;
    border-radius: 15px;
}
#b3{
    width:400px;
    height:330px;
    position:absolute;
    top:80px;
    left:910px;
    border-radius: 15px;
    background-color: #6581A6;
}
#detail{
    width:380px;
    height:260px;
    position:absolute;
    top:10px;
    left:10px;
    border-radius: 15px;
    background-color: whitesmoke;
}
#b4{
    width:400px;
    height:100px;
    position:absolute;
    top: 420px;
    left:910px;
    border-radius: 15px;
    background-color: #6581A6;
}
#invite{
    width:220px;
    margin-left:90px;
    margin-top:20px;
}
#b4:hover{
    opacity: 0.6;
    cursor: pointer;
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
