<template>
    <div id="content">
        <div id="main">
            <div id="topLeft">
                <div class="name">출근 & 퇴근</div>
                <div id="mid1">
                    {{ loginUser.department }}팀 {{ loginUser.name }}님 환영합니다.
                </div>
                <div id="bot1">
                    <div id="day" style="padding-top:25px;">
                        {{ formattedString }}<br>
                        <span style="padding-left:23px;">{{ formattedString2 }}</span>
                    </div>
                    <div id="detail1">
                        <div class="show">
                            <q-btn @click="go" style="border-right:1px solid black; height:100%; font-size:30px; background: #18C75E; color: black;" label="출근" />
                            <span style="margin-left:5px; font-size:22px;">{{ checkInTime }}</span>
                        </div>
                        <div class="show">
                            <q-btn @click="out" style="border-right:1px solid black; height:100%; font-size:30px; background: #18C75E; color: black" label="퇴근" />
                            <span style="margin-left:5px; font-size:22px;">{{ checkOutTime }}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div id="topRight">
                <div class="name">내 통계</div>
                <q-btn @click="mvAttendance" round style="background-color:#18C75E; color:white; float:right; margin-right:5px; margin-top:5px; width:10px;" color="deep-oranges" icon="add" />
                <div>
                    <q-linear-progress stripe rounded style="border-radius:30px; height:40px; width:80%; margin-top:35px; margin-left:100px; cursor:pointer; color:#18C75E;" size="30px" :value="progress1">
                        <div class="absolute-full flex flex-center">
                            <q-badge color="white" style="color:#18C75E; font-weight:bold;" :label="progressLabel1" />
                        </div>
                    </q-linear-progress>
                </div>
                <span style="font-weight:bold; font-size:22px; margin-top:10px; float:left; margin-left:100px;">이번주 근무시간 : {{state.totalHourOfWeek}}시간</span>
                <span style="float:right; margin-right:100px; font-size:22px; margin-top:10px; font-weight:bold;">40시간</span>
            </div>
            <div id="botLeft">
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
    const columnsM = computed(() => store.getters['module/getColumnsM'])
    const rowsM = computed(() => store.getters['module/getRowsM'])
    const progress1 = ref(0)
    const checkInTime = computed(() => store.getters['module/getCheckInTime'])
    const checkOutTime = computed(() => store.getters['module/getCheckOutTime'])
    const loginUser = computed(() => store.getters['module/getLoginUser'])
    const totalHourOfWeek = computed(() => store.getters['module/getTotalHourOfWeek'])
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

    const go = function () {
      store.dispatch('module/go', { time: state.time })
        .then(function (result) {
          console.log(result)
          store.commit('module/setCheckInTime', state.time)
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

    var rows = []
    var boardList = []
    const mvBoard = function () {
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          var pn = Math.ceil(rows.length / 8)
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
      totalHourOfWeek
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
  background-color:white;
}
#topLeft{
  width:650px;
  height:280px;
  border: 1px solid rgb(212, 212, 212);
  display:inline-block;
  position:absolute;
  top:100px;
  left:120px;
  /* background-color:rgb(250, 250, 110); */
}
.name{
  display:inline-block;
  font-weight:bold;
  padding:10px;
  font-size:30px;
}
#mid1{
  height:40px;
  text-align:center;
  line-height:40px;
  font-size:16px;
}
#bot1{
  margin-top:13px;
  height:140px;
}
#day{
  width:20%;
  height:140px;
  font-size:30px;
  font-weight: bold;
  padding-top:10px;
  padding-left:40px;
  display:inline-block;
}
#detail1{
  float:right;
  width:80%;
  height:140px;
  padding-left:15px;
}
.show{
  border:1px solid black;
  display:inline-block;
  width:200px;
  height:100px;
  margin-top:18px;
  margin-left:30px;
}
#topRight{
  float:right;
  width:980px;
  height:280px;
  border:0.5px solid rgb(212, 212, 212);
  position: absolute;
  top:100px;
  left:800px;
  /* background-color:rgb(250, 250, 110); */
}
#botLeft{
  position: absolute;
  width:815px;
  height:485px;
  top:410px;
  left:120px;
  border:0.5px solid rgb(212, 212, 212);
  /* background-color:rgb(250, 250, 110); */
}
#botRight{
  position: absolute;
  width:815px;
  height:485px;
  top:410px;
  left:965px;
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
