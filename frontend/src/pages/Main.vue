<template>
    <div id="content">
        <div id="main">
            <div id="topLeft">
                <div id="name">출근 & 퇴근</div>
                <div id="mid1">
                    개발2팀 {{ state.name }}님 환영합니다.
                </div>
                <div id="bot1">
                    <div id="day">
                        {{ formattedString }}<br>
                        <span style="padding-left:10px;">{{ formattedString2 }}</span>
                    </div>
                    <div id="detail1">
                        <div class="show">
                            <q-btn @click="go" style="border-right:1px solid black; height:100%; font-size:20px; font-weight:bold; background: #18C75E; color: black;" label="출근" />
                            <span style="margin-left:7px; font-size:18px;">{{ state.checkInTime }}</span>
                        </div>
                        <div class="show">
                            <q-btn @click="out" style="border-right:1px solid black; height:100%; font-size:20px; font-weight:bold; background: #18C75E; color: black" label="퇴근" />
                            <span style="margin-left:7px; font-size:18px;">{{ state.checkOutTime }}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div id="topRight">
                <div id="name">내 통계</div>
                <q-btn @click="mvAttendance" round style="background-color:#18C75E; color:white; float:right; margin-right:5px; margin-top:5px; width:10px;" color="deep-oranges" icon="add" />
                <div>
                    <q-linear-progress stripe rounded style="border-radius:20px; width:80%; margin-top:35px; margin-left:80px; cursor:pointer; color:#18C75E;" size="30px" :value="progress1">
                        <div class="absolute-full flex flex-center">
                            <q-badge color="white" style="color:#18C75E; font-weight:bold;" :label="progressLabel1" />
                        </div>
                    </q-linear-progress>
                </div>
                <span style="font-weight:bold; font-size:18px; margin-top:8px; float:left; margin-left:80px;">이번주 근무시간 : {{state.totalHourOfWeek}}시간</span>
                <span style="float:right; margin-right:45px; font-size:18px; margin-top:8px; font-weight:bold;">40시간</span>
            </div>
            <div id="botLeft">
              사용자 프로필 이미지 테스트(헤더에 안올라감 ㅠ)<br>
              <img src="/media/profile/qqq/tree.jpg" style="border-radius: 50%; width: 50px; height: 50px;">
              <img :src="state.profileLocation" style="border-radius: 50%; width: 50px; height: 50px;">
            </div>
            <div id="botRight"></div>
        </div>
    </div>
</template>

<script>
import { reactive, defineComponent, onMounted, ref, computed } from 'vue'
import { date } from 'quasar'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'mainpage',
  components: {
  },
  methods: {
    mvAttendance () {
      this.$router.push('/attendance')
    }
  },
  setup () {
    const timeStamp = Date.now()
    const formattedString = date.formatDate(timeStamp, 'MM.DD')
    const formattedString2 = date.formatDate(timeStamp, 'ddd')
    const store = useStore()
    const router = useRouter()
    const currentTime = Date.now()
    const Swal = require('sweetalert2')

    const progress1 = ref(0)
    const state = reactive({
      time: date.formatDate(currentTime, 'HH:mm'),
      totalHourOfWeek: '',
      checkInTime: '',
      checkOutTime: '',
      name: localStorage.getItem('name'),
      profileLocation: localStorage.getItem('profileLocation')
    })
    onMounted(() => {
      store.dispatch('module/check', { })
        .then(function (result) {
          state.checkInTime = result.data.checkInTime
          state.checkOutTime = result.data.checkOutTime
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
      store.dispatch('module/loadAttendanceByWeek', { })
        .then(function (result) {
          state.totalHourOfWeek = result.data.totalHour
          console.log(result.data)
          progress1.value = state.totalHourOfWeek / 40
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">일주일 근태정보 조회중 문제가 발생하였습니다.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    })

    const go = function () {
      store.dispatch('module/go', { time: state.time })
        .then(function (result) {
          router.go()
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }

    const out = function () {
      store.dispatch('module/out', { time: state.time })
        .then(function (result) {
          router.go()
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }
    return {
      formattedString,
      formattedString2,
      go,
      out,
      state,
      progress1,
      progressLabel1: computed(() => (progress1.value * 100).toFixed(1) + '%')
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
  background-color:white;
}
#topLeft{
  width:500px;
  height:200px;
  border: 1px solid rgb(212, 212, 212);
  display:inline-block;
  position:absolute;
  top:100px;
  left:130px;
  /* background-color:rgb(250, 250, 110); */
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
  /* background-color:rgb(250, 250, 110); */
}
#botLeft{
  position: absolute;
  width:620px;
  height:390px;
  top:335px;
  left:130px;
  border:0.5px solid rgb(212, 212, 212);
  /* background-color:rgb(250, 250, 110); */
}
#botRight{
  position: absolute;
  width:620px;
  height:390px;
  top:335px;
  left:830px;
  border:0.5px solid rgb(212, 212, 212);
  /* background-color:rgb(250, 250, 110); */
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'Eulyoo1945-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/Eulyoo1945-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
