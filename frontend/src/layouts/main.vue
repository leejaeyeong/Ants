<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="topLeft">
                <div id="name">출근 & 퇴근</div>
                <div id="mid1">
                    개발2팀 김싸피 사원님 환영합니다.
                </div>
                <div id="bot1">
                    <div id="day">
                        {{ formattedString }}<br>
                        <span style="padding-left:10px;">{{ formattedString2 }}</span>
                    </div>
                    <div id="detail1">
                        <div class="show">
                            <q-btn style="border-right:1px solid black; height:67px; font-size:20px; font-weight:bold; background: #d4d0d0; color: black" label="출근" />
                        </div>
                        <div class="show">
                            <q-btn style="border-right:1px solid black; height:67px; font-size:20px; font-weight:bold; background: #d4d0d0; color: black" label="퇴근" />
                        </div>
                    </div>
                </div>
            </div>
            <div id="topRight">
                <div id="name">내 통계</div>
                <div>
                    내 근로시간
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'
import { date } from 'quasar'

export default defineComponent({
  name: 'login',
  components: {
    Header,
    Side
  },
  setup () {
    const timeStamp = Date.now()
    const formattedString = date.formatDate(timeStamp, 'MM.DD')
    const formattedString2 = date.formatDate(timeStamp, 'ddd')
    return {
      formattedString,
      formattedString2
    }
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
#topLeft{
    width:500px;
    height:200px;
    border: 1px solid rgb(212, 212, 212);
    display:inline-block;
    position:absolute;
    top:100px;
    left:130px;
    background-color:rgb(250, 250, 110);
}
#name{
    display:inline-block;
    font-weight:bold;
    padding:10px;
    font-size:20px;
}
#mid1{
    height:40px;
    text-align:center;
    line-height:40px;
    font-size:16px;
}
#bot1{
    margin-top:10px;
    height:100px;
}
#day{
    width:15%;
    height:100px;
    font-size:25px;
    font-weight: bold;
    padding-top:10px;
    padding-left:25px;
    display:inline-block;
}
#detail1{
    float:right;
    width:85%;
    height:100px;
    padding-left:20px;
}
.show{
    border:1px solid black;
    display:inline-block;
    width:160px;
    height:70px;
    margin-top:13px;
    margin-left:20px;
}
#topRight{
    float:right;
    width:750px;
    height:200px;
    border:0.5px solid rgb(212, 212, 212);
    position: absolute;
    top:100px;
    left:700px;
    background-color:rgb(250, 250, 110);
}
</style>
