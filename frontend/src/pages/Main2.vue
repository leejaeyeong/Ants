<template>
  <div id="content">
    <div id="main">
      <div id="topLeft" class="shadow-1">
        <div class="name">Today</div>
        <div id="day">
          {{ formattedString }} {{ formattedString2 }}
        </div>
        <div id="mid1">
          <img :src="state.profileLocation" style="width:120px; height:150px;">
        </div>
        <div class="userInfo">
          {{ loginUser.name }}
          <br>
          {{ loginUser.position }}님
          <br>
          {{ loginUser.department }}
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
      <div class="bottomleft shadow-1">
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
      <div id="topRight" class="shadow-1">
        <div class="name">Weekly Report</div><span style="margin-left:25px; font-size:13px;">{{ inputText }}</span>
        <q-btn @click="mvAttendance" round style="background-color:#18C75E; color:white; float:right; margin-right:10px; margin-top:10px; width:10px;" color="deep-oranges" icon="trending_up" />
        <div>
          <q-linear-progress stripe rounded style="border-radius:30px; height:40px; width:80%; margin-top:35px; margin-left:100px; cursor:pointer; color:#18C75E;" size="30px" :value="progress1">
            <div class="absolute-full flex flex-center">
                <q-badge color="white" style="color:#18C75E;" :label="progressLabel1" />
            </div>
          </q-linear-progress>
        </div>
        <span style="font-size:16px; margin-top:13px; float:left; margin-left:100px;">{{totalHourOfWeek}} Hour</span>
        <span style="float:right; margin-right:70px; font-size:18px; margin-top:13px; font-weight:bold;">40 Hour</span>
      </div>
      <div id="botRight" class="shadow-1">
        <div class="name">최근 게시물</div>
        <q-btn @click="mvBoard" round style="background-color:#18C75E; color:white; float:right; margin-right:5px; margin-top:10px; margin-right:10px; width:10px;" color="deep-oranges" icon="trending_up" />
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
      <div id="endRight" class="shadow-1">
        <div class="name" style="margin-bottom:10px;">오늘의 할일</div>
        <q-fab v-model="bt" style="background-color:#18C75E; color:white; float:right; margin-right:5px; margin-top:10px;" padding="sm" glossy icon="add" direction="left">
          <div id="todoForm">
                <q-time v-model="todoTime" />
            <q-input v-model="state.todoText" color="teal" style="display:inline-block; width:72%; margin-top:5px;" filled label="입력란" />
            <q-btn @click="registTodo" style="background-color:#18C75E; color:white; font-size:20px; margin-top:-15px; margin-left:6px;" label="등록"/>
          </div>
        </q-fab>
        <div id="todoView">
          <div class="todo" v-for="(todo, idx) in todoList" :key="idx">
            <div class="row">
              <div class="col-1">
              </div>
              <div class="col-3">
                {{ todo.time }}
              </div>
              <div class="col-7">
                {{ todo.title }}
              </div>
              <div class="col-1">
                <q-checkbox v-model="check" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="endBottom" class="shadow-1">
        <div class="name">뭐하지❓</div>
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
  data () {
    return {
      modal: false
    }
  },
  methods: {
    mvAttendance () {
      this.$router.push('/management')
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
    const totalHourOfWeek = computed(() => store.getters['module/getTotalHourOfWeek'])
    const bt = ref(false)
    const todoTime = ref('14:40')
    const todoList = computed(() => store.getters['module/getTodoList'])
    const state = reactive({
      time: date.formatDate(currentTime, 'HH:mm:ss'),
      name: localStorage.getItem('name'),
      profileLocation: localStorage.getItem('profileLocation'),
      todoText: ''
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
          store.commit('module/setTotalHourOfWeek', result.data.totalHour)
          progress1.value = result.data.totalHour / 40
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">일주일 근태정보 조회중 문제가 발생하였습니다.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
      const dpId = localStorage.getItem('departmentId')
      store.dispatch('module/getTodoList', dpId)
        .then(function (result) {
          var tmp = []
          for (let i = 0; i < result.data.length; i++) {
            const str1 = result.data[i].time.substr(0, 2)
            const str2 = result.data[i].time.substr(3, 2)
            result.data[i].time = str1 + '시 ' + str2 + '분'
            tmp.push(result.data[i])
          }
          store.commit('module/setTodoList', tmp)
        })
      console.log('312381273891371298')
      console.log(rowsM)
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
          store.dispatch('module/loadAttendanceByWeek', { })
            .then(function (result) {
              store.commit('module/setTotalHourOfWeek', result.data.totalHour)
              progress1.value = result.data.totalHour / 40
            })
            .catch(function () {
              Swal.fire({
                title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">일주일 근태정보 조회중 문제가 발생하였습니다.</span>',
                confirmButtonColor: '#ce1919',
                confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
              })
            })
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

    const registTodo = function () {
      const departmentId = localStorage.getItem('departmentId')
      store.dispatch('module/registTodo', { departmentId: departmentId, time: todoTime.value, title: state.todoText })
        .then(function () {
          const dpId = localStorage.getItem('departmentId')
          store.dispatch('module/getTodoList', dpId)
            .then(function (result) {
              var tmp = []
              for (let i = 0; i < result.data.length; i++) {
                const str1 = result.data[i].time.substr(0, 2)
                const str2 = result.data[i].time.substr(3, 2)
                result.data[i].time = str1 + '시 ' + str2 + '분'
                tmp.push(result.data[i])
              }
              store.commit('module/setTodoList', tmp)
            })
        })
      state.todoText = ''
      bt.value = false
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
      inputText,
      totalHourOfWeek,
      bt,
      registTodo,
      todoTime,
      todoList,
      check: ref(false)
    }
  }
})

</script>

<style scoped>
#content{
  width:100%;
  height: 860px;
}
#main{
  width:1858px;
  height:876px;
  float:right;
  position: absolute;
  top: 60px;
  left: 60px;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: rgb(242, 247, 244);
}
#topLeft{
  width:350px;
  height:500px;
  border: 1px solid rgb(212, 212, 212);
  display:inline-block;
  position:absolute;
  top:20px;
  left:20px;
  background-color: white;
  border-radius: 3%;
  animation: leftFadeIn 0.5s ease-in-out;
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
  top:534px;
  left:20px;
  border-radius: 4%;
  animation: leftFadeIn 1.1s ease-in-out;
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
#topRight{
  float:right;
  width:815px;
  height:230px;
  border:0.5px solid rgb(212, 212, 212);
  position: absolute;
  top:20px;
  left:385px;
  background-color: white;
  border-radius: 10px;
  animation: topFadeIn 1.1s ease-in-out;
}
#endRight{
  float:right;
  width:550px;
  height:350px;
  border:0.5px solid rgb(212, 212, 212);
  background-color: white;
  position: absolute;
  top:20px;
  left:1215px;
  border-radius: 10px;
  animation: rightFadeIn 1.1s ease-in-out;
}
#endBottom{
  float:right;
  width:550px;
  height:420px;
  border:0.5px solid rgb(212, 212, 212);
  background-color: white;
  position: absolute;
  top:390px;
  left: 1215px;
  border-radius: 1.5%;
  animation: bottomFadeIn 1.1s ease-in-out;
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
  top:270px;
  left:385px;
  border:0.5px solid rgb(212, 212, 212);
  background-color:white;
  border-radius: 1.5%;
  animation: bottomFadeIn 1.1s ease-in-out;
}
#todoForm{
  width:290px;
  height:440px;
  background-color:whitesmoke;
  margin-top:380px;
}
.todo{
  width:90%;
  height:50px;
  line-height:50px;
  margin:0 auto;
  border-bottom:1px solid rgb(212, 212, 212);
  font-size:20px;
}
#todoView{
  width:550px;
  height:282px;
  overflow-y: auto;
}
@keyframes leftFadeIn {
  from {
    opacity: 0;
    transform: translateX(-20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
@keyframes rightFadeIn {
  from {
    opacity: 0;
    transform: translateX(20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
@keyframes topFadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
@keyframes bottomFadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
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
