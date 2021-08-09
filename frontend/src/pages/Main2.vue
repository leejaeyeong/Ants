<template>
  <div id="content">
    <div id="main">
      <div id="topLeft">
        <div class="name">Today</div>
        <div id="day">
          {{ formattedString }} {{ formattedString2 }}
        </div>
        <div id="mid1">
          <img :src="state.profileLocation" style="width:120px; height:150px;">
        </div>
        <div class="userInfo">
          {{ loginUser.name }}님
          <br>
          {{ loginUser.position }}
          <br>
          {{ loginUser.department }}팀
        </div>
        <div id="bot1">
          <q-btn flat @click="go" class="checkbtn" label="출근" />
          <q-btn flat @click="out" class="checkbtn" label="퇴근" />
          <div id="detail1">
            <div style="font-size:16px; margin-bottom:10px;">출근 시간<div class="time">{{ checkInTime }}</div></div>
            <div style="font-size:16px;">퇴근 시간<div class="time">{{ checkOutTime }}</div></div>
          </div>
        </div>
      </div>
      <div class="bottomleft">
        <div class="name">휴가현황</div>
        <a style="text-decoration:none; color:grey;" href="" class="vacation">휴가 신청>></a>
        <div class="totalimg">
          <div class="img">
            <q-icon name="mood"/>
            <span style="color: black; font-size:35px; margin: 10%;">-</span>
            <!-- <span style="font-size:16px; color:black; margin-top:-10%">총일수</span> -->
          </div>
          <div class="img">
            <q-icon name="mood_bad"/>
            <span style="color: black; font-size:35px; margin: 10%;">=</span>
          </div>
          <div class="img">
            <q-icon name="sentiment_satisfied"/>
          </div>
        </div>
        <div class="totaltext">
          <div class="txt">&nbsp;총 일수</div>
          <div class="txt">사용 일수</div>
          <div class="txt">남은 일수</div>
        </div>
        <div class="totalicon">
          <q-btn class="icon" unelevated filled rounded color="blue-2" label="14" />
          <q-btn class="icon" unelevated filled rounded color="blue-2" label="2" />
          <q-btn class="icon" unelevated filled rounded color="orange-5" label="12" />
        </div>
      </div>
      <div id="topRight">
        <div class="name">Weekly Report</div><span style="margin-left:25px; font-size:13px;">{{ inputText }}</span>
        <q-btn @click="mvAttendance" round style="background-color:#18C75E; color:white; float:right; margin-right:20px; margin-top:20px; width:10px;" color="deep-oranges" icon="add" />
        <div>
          <q-linear-progress stripe rounded style="border-radius:30px; height:40px; width:80%; margin-top:35px; margin-left:100px; cursor:pointer; color:#18C75E;" size="30px" :value="progress1">
            <div class="absolute-full flex flex-center">
                <q-badge color="white" style="color:#18C75E;" :label="progressLabel1" />
            </div>
          </q-linear-progress>
        </div>
        <span style="font-size:16px; margin-top:13px; float:left; margin-left:100px;">{{state.totalHourOfWeek}} Hour</span>
        <span style="float:right; margin-right:70px; font-size:18px; margin-top:13px; font-weight:bold;">40 Hour</span>
      </div>
      <div id="botRight">
        <div class="name">최근 게시물</div>
        <q-btn @click="mvBoard" round style="background-color:#18C75E; color:white; float:right; margin-right:5px; margin-top:5px; width:10px;" color="deep-oranges" icon="add" />
        <div class="q-pa-md">
          <q-table
            title=""
            :rows="rowsM"
            :columns="columnsM"
            row-key="title"
            v-model:pagination="pagination"
            style="cursor:pointer;"
            hide-pagination
          />
        </div>
      </div>
      <div id="endRight">
      </div>
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
    const formattedString = date.formatDate(timeStamp, 'YYYY.MM.DD')
    const formattedString2 = date.formatDate(timeStamp, 'ddd')
    const store = useStore()
    const router = useRouter()
    const currentTime = Date.now()
    const Swal = require('sweetalert2')
    const columnsM = computed(() => store.getters['module/getColumnsM'])
    const rowsM = computed(() => store.getters['module/getRowsM'])
    const progress1 = ref(0)
    const checkInTime = computed(() => store.getters['module/getCheckInTime'])
    const checkOutTime = computed(() => store.getters['module/getCheckOutTime'])
    const loginUser = computed(() => store.getters['module/getLoginUser'])
    const state = reactive({
      time: date.formatDate(currentTime, 'HH:mm:ss'),
      totalHourOfWeek: '',
      name: localStorage.getItem('name'),
      profileLocation: localStorage.getItem('profileLocation')
    })
    const pagination = ref({
      sortBy: 'desc',
      descending: false,
      page: 1,
      rowsPerPage: 8
      // rowsNumber: xx if getting data from a server
    })
    onMounted(() => {
      store.dispatch('module/check', { })
        .then(function (result) {
          store.commit('module/setCheckInTime', result.data.checkInTime)
          store.commit('module/setCheckOutTime', result.data.checkOutTime)
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
    const currentDay = new Date()
    const theYear = currentDay.getFullYear()
    const theMonth = currentDay.getMonth()
    const theDate = currentDay.getDate()
    const theDayOfWeek = currentDay.getDay()
    const thisWeek = []

    for (let i = 0; i < 7; i++) {
      const resultDay = new Date(theYear, theMonth, theDate + (i - theDayOfWeek))
      const yyyy = resultDay.getFullYear()
      let mm = Number(resultDay.getMonth()) + 1
      let dd = resultDay.getDate()
      mm = String(mm).length === 1 ? '0' + mm : mm
      dd = String(dd).length === 1 ? '0' + dd : dd
      thisWeek[i] = yyyy + '-' + mm + '-' + dd
    }

    const inputText = thisWeek[0] + ' ~ ' + thisWeek[6]
    console.log(thisWeek[0], '이번주1')
    console.log(thisWeek[6], '이번주2')

    console.log(inputText)

    const go = function () {
      store.dispatch('module/go', { time: state.time })
        .then(function (result) {
          console.log(result)
          store.commit('module/setCheckInTime', state.time)
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">이미 처리 되었습니다.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }

    const out = function () {
      store.dispatch('module/out', { time: state.time })
        .then(function (result) {
          console.log(result)
          store.commit('module/setCheckOutTime', state.time)
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }

    let rows = []
    let boardList = []
    const mvBoard = function () {
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          const pn = Math.ceil(rows.length / 8)
          store.commit('module/setPageNumber', pn)
          rows = []
          store.dispatch('module/boardList', { })
            .then(function (result) {
              for (let i = 0; i < result.data.length; i++) {
                boardList.push(result.data[i])
              }
              store.commit('module/setBoardList', boardList)
              boardList = []
              router.push('/board')
            })
            .catch(function () {
              alert('오류발생')
            })
        })
        .catch(function () {
          alert('오류발생')
        })
    }

    return {
      formattedString,
      formattedString2,
      go,
      out,
      state,
      progress1,
      progressLabel1: computed(() => (progress1.value * 100).toFixed(1) + '%'),
      columnsM,
      rowsM,
      mvBoard,
      checkInTime,
      checkOutTime,
      loginUser,
      pagination,
      inputText
    }
  }
})

</script>

<style scoped>
#content{
  width:100%;
  height: 100%;
}
#main{
  width:95%;
  height:100%;
  float:right;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: whitesmoke;
}
#topLeft{
  width:350px;
  height:500px;
  border: 1px solid rgb(212, 212, 212);
  display:inline-block;
  position:absolute;
  top:100px;
  left:120px;
  background-color: white;
  /* background-color:rgb(250, 250, 110); */
}
.name{
  display:inline-block;
  font-weight:bold;
  margin-left: 38px;
  margin-top: 20px;
  font-size:24px;
}
#day{
  height:30px;
  font-size:18px;
  margin-left: 38px;
  margin-top: 7px;
  margin-bottom: 7px;
}
#mid1{
  width: 150px;;
  height:180px;
  margin-left: 38px;
  margin-top: 10px;
  float: left;
}
.userInfo {
  float: left;
  width: 150px;
  height: 180px;
  text-align: center;
  line-height: 40px;
  font-size: 16px;
  margin-top: 10px;
}
.checkbtn {
  border-right:1px;
  Font-size:16px;
  color: #18C75E;
  float: left;
  margin-top: 13px;
  margin-left: 37px;
  margin-right: 37px;
  margin-bottom: 25px;
  width: 100px;
}
.time {
  float: right;
  margin-right: 10px;
}
.bottomleft {
  background-color: white;
  width: 350px;
  height: 275px;
  float: left;
  border: 1px solid rgb(212, 212, 212);
  position: absolute;
  top:615px;
  left:120px;
}
#bot1{
  margin-top:5px;
  height:140px;
}
#detail1{
  float:right;
  width:80%;
  height:60px;
  margin-right: 38px;
}
.img {
  float: left;
  width: 33%;
  color: cornflowerblue;
  font-size: 4rem;
}
.vacation{
  float: right;
  margin-top: 7%;
  font-size:14px;
  margin-right: 25px;
}
.totalimg {
  margin-left: 37px;
  margin-top: 10px;
}
.totaltext{
  margin-left: 37px;
  margin-top: 10px;
}
.icon {
  float: left;
  width: 60px;
  margin-top: 10px;
  margin-right: 14px;
  margin-left: 32px;
}
.txt {
  float: left;
  width: 33%;
  font-size: 16px;
}
.show{
  width:200x;
  height:100px;
  margin-top:18px;
  margin-left:30px;
  background-color: goldenrod;
}
#topRight{
  float:right;
  width:815px;
  height:230px;
  border:0.5px solid rgb(212, 212, 212);
  position: absolute;
  top:100px;
  left:480px;
  background-color: white;
}
#endRight{
  float:right;
  width:550px;
  height:280px;
  border:0.5px solid rgb(212, 212, 212);
  position: absolute;
  top:100px;
  left:1310px;
}
#botLeft{
  position: absolute;
  width:815px;
  height:530px;
  top:410px;
  left:120px;
  border:0.5px solid rgb(212, 212, 212);
  background-color:white;
}
#botRight{
  position: absolute;
  width:815px;
  height:540px;
  top:350px;
  left:480px;
  border:0.5px solid rgb(212, 212, 212);
  background-color:white;
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
