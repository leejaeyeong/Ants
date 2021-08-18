<template>
  <div id="content">
    <div id="main">
      <div class="q-px-sm q-py-lg" style="position:absolute; top:15px; left:1775px;">
        <q-fab v-model="mode" color="yellow-10" text-color="white" icon="keyboard_arrow_down" direction="down">
          <div>
            <q-fab-action color="yellow-9" text-color="white" @mouseover="colorMode1" @mouseout="colorMode11" @click="CheckMode1" icon="assignment_ind" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Today</strong>
            </q-tooltip>
          </div>
          <div>
            <q-fab-action color="yellow-9" text-color="white" @mouseover="colorMode2" @mouseout="colorMode22" @click="CheckMode2" icon="flight_takeoff" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Annual Leave</strong>
            </q-tooltip>
          </div>
          <div>
            <q-fab-action color="yellow-9" text-color="white" @mouseover="colorMode3" @mouseout="colorMode33" @click="CheckMode3" icon="leaderboard" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Weekly Report</strong>
            </q-tooltip>
          </div>
          <div>
            <q-fab-action color="yellow-9" text-color="white" @mouseover="colorMode4" @mouseout="colorMode44" @click="CheckMode4" icon="content_paste" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Recently Post</strong>
            </q-tooltip>
          </div>
          <div>
            <q-fab-action color="yellow-9" text-color="white" @mouseover="colorMode5" @mouseout="colorMode55" @click="CheckMode5" icon="toc" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Work Sharing</strong>
            </q-tooltip>
          </div>
          <div>
            <q-fab-action color="yellow-9" text-color="white" @mouseover="colorMode6" @mouseout="colorMode66" @click="CheckMode6" icon="hotel_class" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Bookmark</strong>
            </q-tooltip>
          </div>
          <div>
            <q-fab-action color="yellow-9" text-color="white"  @click="reset" icon="undo" />
            <q-tooltip anchor="center left" self="center right" :offset="[10, 10]" max-width="150px">
              <strong style="font-size: 12px;">Reset</strong>
            </q-tooltip>
          </div>

        </q-fab>
      </div>
      <div id="topLeft" v-show="mode1" class="shadow-1">
        <div class="name">Today</div>
        <div id="day">
          {{ formattedString }} {{ formattedString2 }}
        </div>
        <div id="mid1">
          <img :src="state.profileLocation" style="width:120px; height:150px; border-radius: 10px;">
        </div>
        <div class="userInfo">
          {{ loginUser.name }}
          <br>
          {{ loginUser.position }}님
          <br>
          {{ loginUser.department }}
        </div>
        <div>
          <q-btn flat @click="go" class="checkbtn" label="출근" />
          <q-btn flat @click="out" class="checkbtn" label="퇴근" />
          <div id="detail1">
            <div style="font-size:16px; margin-bottom:10px;">출근 시간<div class="time">{{ checkInTime }}</div></div>
            <div style="font-size:16px;">퇴근 시간<div class="time">{{ checkOutTime }}</div></div>
          </div>
        </div>
        <div class="clock">
          <!-- <p class="realtime">{{ }}</p> -->
        </div>
      </div>
      <div id="bottomLeft" v-show="mode2" class="bottomleft shadow-1">
        <div class="name">Annual Leave</div>
        <a style="text-decoration:none; color:grey;" href="" class="vacation to-right-underline"><strong>More ></strong></a>
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
          <q-btn class="icon" unelevated filled rounded color="green-6" label="14" />
          <q-btn class="icon" unelevated filled rounded color="green-6" label="0" />
          <q-btn class="icon" unelevated filled rounded color="yellow-10" label="14" />
        </div>
      </div>
      <div id="topRight" v-show="mode3" class="shadow-1">
        <div class="name">Weekly Report</div>
        <span style="margin-left:25px; font-size:13px;">{{ inputText }}</span>
        <span @click="mvAttendance" class="to-right-underline" style="margin-left:320px; font-size:13px; color:grey; cursor: pointer;"><strong>More ></strong></span>
        <!-- <q-btn @click="mvAttendance" round style="background-color:#18C75E; color:white; float:right; margin-right:12px; margin-top:12px; width:12px;" color="deep-oranges" icon="trending_up" /> -->
        <div>
          <q-linear-progress stripe rounded style="border-radius:30px; height:40px; width:80%; margin-top:35px; margin-left:100px; cursor:pointer; color:#249752;" size="30px" :value="progress1">
            <div class="absolute-full flex flex-center">
                <q-badge color="white" style="color:#249752;" :label="progressLabel1" />
            </div>
          </q-linear-progress>
        </div>
        <span style="font-size:16px; margin-top:13px; float:left; margin-left:100px;">{{totalHourOfWeek}} Hour</span>
        <spfan style="float:right; margin-right:70px; font-size:18px; margin-top:13px; font-weight:bold;">40 Hour</spfan>
      </div>
      <div id="botRight" v-show="mode4" class="shadow-1">
        <div class="name">Recently Post</div>
        <span @click="mvAttendance" class="to-right-underline" style="margin-left:535px; font-size:13px; color:grey; cursor: pointer;"><strong>More ></strong></span>
        <!-- <q-btn @click="mvBoard" round style="background-color:#18C75E; color:white; float:right; margin-right:5px; margin-top:12px; margin-right:12px; width:12px;" color="deep-oranges" icon="trending_up" /> -->
        <div class="q-pa-md">
          <q-table
            :rows="rowsM"
            :columns="columnsM"
            row-key="title"
            v-model:pagination="pagination"
            style="cursor:pointer;"
            hide-pagination
          />
        </div>
      </div>
      <div id="endRight" v-show="mode5" class="shadow-1">
        <div class="name" style="margin-bottom:10px;">Work Sharing</div>
        <q-fab flat v-model="bt" style="background-color:withe; font-weight:bold; color:#249752; float:right; margin-right:5px; margin-top:10px;" padding="sm" icon="add" direction="left">
          <div id="todoForm">
            <q-time v-model="todoTime" color="yellow-10" text-color="black" flat/>
            <q-input v-model="state.todoText" color="teal" style="display:inline-block; width:72%; margin-top:5px; margin-left: 5px;" label="오늘의 할 일을 입력하세요" />
            <q-btn @click="registTodo" style="background-color:#249752; color:white; font-size:14px; margin-top:-15px; margin-left:6px;" label="등록"/>
          </div>
        </q-fab>
        <div id="todoView">
          <div class="todo" v-for="(todo, idx) in todoList" :key="idx">
            <div class="row">
              <div class="col-1">
              </div>
              <div v-if="todo.do" class="col-3" style="text-decoration:line-through">
                {{ todo.time }}
              </div>
              <div v-if="!todo.do" class="col-3">
                {{ todo.time }}
              </div>
              <div v-if="todo.do" class="col-7" style="text-decoration:line-through">
                {{ todo.title }}
              </div>
              <div v-if="!todo.do" class="col-7">
                {{ todo.title }}
              </div>
              <div class="col-1">
                <q-checkbox v-model="todo.do"/>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="endBottom" v-show="mode6" class="shadow-1">
        <div class="name">Bookmark</div>
        <div class="row" style="margin: 1%;">
          <div class="col-1"></div>
          <div class="col-7">
            <q-input v-model="text" id="inputSiteURL" color="teal" style="width: 100%; margin-left:30px;" label="url을 등록하세요"/>
          </div>
          <div class="col-4">
            <q-btn flat @click="addLink(loginUser.id, text)" style="width: 100px; color: #249752; margin-top:12px; margin-left:30px;">등록하기</q-btn>
          </div>
          <!-- <q-input v-model="text" id="inputSiteURL" style="width: 75%"/>
          <q-btn @click="addLink(loginUser.id, text)" style="width: 20%; color: green;">등록하기</q-btn> -->
        </div>
        <div id="weblist" style="width: 100%; height: 75%; overflow-y: scroll">
          <div v-for="link in linkList" :key="link.id">
            <div class="row">
              <q-card style="width: 100%; margin: 2%;">
                <div class="row" style="height: 100px;">
                  <div class="col-2">
                    <a :href="link.link" target="_blank" style="text-decoration: none; color: black;">
                      <img :src="link.image" class="col-2" style="width: 100px; height: 100px;"/>
                    </a>
                  </div>
                  <div class="col-9" style="line-height:30px;">
                    <a :href="link.link" target="_blank" style="text-decoration: none; color: black;">
                      <div style="margin-left: 20px; margin-top: 15px; font-size: 120%;">{{ link.title }}</div>
                      <div style="margin-left: 20px;">{{ link.link }}</div>
                    </a>
                  </div>
                  <div class="col-1">
                    <div style="line-height: 90px; cursor: pointer;">
                      <q-icon @click="deleteSite(link.id)" class="deleteIcon" name="delete"/>
                    </div>
                  </div>
                </div>
              </q-card>
            </div>
            <!-- <div class="row">
              <q-card style="width: 100%; margin: 2%;">
              <a :href="link.link" class="col-12 row" style="text-decoration: none; color: black;">
                <img :src="link.image" class="col-2" style="width: 100px; height: 100px;"/>
                <div class="col-8" style="margin: auto 2%;">
                  <div style="font-size: 120%;">{{ link.title }}</div>
                  <div style="margin-top: 2%;">{{ link.link }}</div>
                </div>
                <div class="col-1">
                  <div style="line-height: 90px;">
                    <q-icon @click="deleteSite()" class="deleteIcon" name="delete"/>
                  </div>
                </div>
              </a>
              </q-card>
            </div> -->
          </div>
            <!-- <div>Meta Keyword: <div id="kw"></div></div>
            <div>Description: <div id="des"></div></div>
            <div>image: <div id="img"></div></div> -->
        </div>
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
    const mode = ref(false)
    const mode1 = computed(() => store.getters['module/getMode1'])
    const mode2 = computed(() => store.getters['module/getMode2'])
    const mode3 = computed(() => store.getters['module/getMode3'])
    const mode4 = computed(() => store.getters['module/getMode4'])
    const mode5 = computed(() => store.getters['module/getMode5'])
    const mode6 = computed(() => store.getters['module/getMode6'])
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
      rowsPerPage: 10
    })
    const linkList = computed(() => store.getters['module/getLinkList'])

    onMounted(() => {
      var count = 1
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
            result.data[i].do = false
            result.data[i].time = str1 + '시 ' + str2 + '분'
            tmp.push(result.data[i])
          }
          store.commit('module/setTodoList', tmp)
        })
      // // 현재시간 불러오기
      // const getTimenow = function () {
      //   const time = new Date()
      //   const hour = time.getHours()
      //   const minutes = time.getMinutes()
      //   const seconds = time.getSeconds()
      //   document.querySelector('.realtime').innerHTML = `${hour < 10 ? `0${hour}` : hour}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`
      // }
      // // 1초마다 실행하기
      // const run = function () {
      //   setInterval(getTimenow, 1000)
      // }
      // run()
      // 이동 함수 시작
      const today = document.getElementById('topLeft')
      var x1 = 0
      var y1 = 0
      var mousedown1 = false

      today.addEventListener('mousedown', function (e) {
        mousedown1 = true
        x1 = today.offsetLeft - e.clientX
        y1 = today.offsetTop - e.clientY
        today.style.zIndex = count++
      }, true)

      today.addEventListener('mouseup', function (e) {
        mousedown1 = false
      }, true)

      today.addEventListener('mousemove', function (e) {
        if (mousedown1) {
          today.style.left = e.clientX + x1 + 'px'
          today.style.top = e.clientY + y1 + 'px'
          // store.commit('module/setModePx1', [e.clientX + x1, e.clientX + y1])
        }
      })

      const vacation = document.getElementById('bottomLeft')
      var x2 = 0
      var y2 = 0
      var mousedown2 = false

      vacation.addEventListener('mousedown', function (e) {
        mousedown2 = true
        x2 = vacation.offsetLeft - e.clientX
        y2 = vacation.offsetTop - e.clientY
        vacation.style.zIndex = count++
      }, true)

      vacation.addEventListener('mouseup', function (e) {
        mousedown2 = false
      }, true)

      vacation.addEventListener('mousemove', function (e) {
        if (mousedown2) {
          vacation.style.left = e.clientX + x2 + 'px'
          vacation.style.top = e.clientY + y2 + 'px'
          // store.commit('module/setModePx2', [e.clientX + x2, e.clientX + y2])
        }
      })

      const week = document.getElementById('topRight')
      var x3 = 0
      var y3 = 0
      var mousedown3 = false

      week.addEventListener('mousedown', function (e) {
        mousedown3 = true
        x3 = week.offsetLeft - e.clientX
        y3 = week.offsetTop - e.clientY
        week.style.zIndex = count++
      }, true)

      week.addEventListener('mouseup', function (e) {
        mousedown3 = false
      }, true)

      week.addEventListener('mousemove', function (e) {
        if (mousedown3) {
          week.style.left = e.clientX + x3 + 'px'
          week.style.top = e.clientY + y3 + 'px'
          // store.commit('module/setModePx3', [e.clientX + x3, e.clientX + y3])
        }
      })

      const recent = document.getElementById('botRight')
      var x4 = 0
      var y4 = 0
      var mousedown4 = false

      recent.addEventListener('mousedown', function (e) {
        mousedown4 = true
        x4 = recent.offsetLeft - e.clientX
        y4 = recent.offsetTop - e.clientY
        recent.style.zIndex = count++
      }, true)

      recent.addEventListener('mouseup', function (e) {
        mousedown4 = false
      }, true)

      recent.addEventListener('mousemove', function (e) {
        if (mousedown4) {
          recent.style.left = e.clientX + x4 + 'px'
          recent.style.top = e.clientY + y4 + 'px'
          // store.commit('module/setModePx4', [e.clientX + x4, e.clientX + y4])
        }
      })

      const todo = document.getElementById('endRight')
      var x5 = 0
      var y5 = 0
      var mousedown5 = false

      todo.addEventListener('mousedown', function (e) {
        mousedown5 = true
        x5 = todo.offsetLeft - e.clientX
        y5 = todo.offsetTop - e.clientY
        todo.style.zIndex = count++
      }, true)

      todo.addEventListener('mouseup', function (e) {
        mousedown5 = false
      }, true)

      todo.addEventListener('mousemove', function (e) {
        if (mousedown5) {
          todo.style.left = e.clientX + x5 + 'px'
          todo.style.top = e.clientY + y5 + 'px'
          // store.commit('module/setModePx5', [e.clientX + x5, e.clientX + y5])
        }
      })

      const end = document.getElementById('endBottom')
      var x6 = 0
      var y6 = 0
      var mousedown6 = false

      end.addEventListener('mousedown', function (e) {
        mousedown6 = true
        x6 = end.offsetLeft - e.clientX
        y6 = end.offsetTop - e.clientY
        end.style.zIndex = count++
      }, true)

      end.addEventListener('mouseup', function (e) {
        mousedown6 = false
      }, true)

      end.addEventListener('mousemove', function (e) {
        if (mousedown6) {
          end.style.left = e.clientX + x6 + 'px'
          end.style.top = e.clientY + y6 + 'px'
          // store.commit('module/setModePx6', [e.clientX + x6, e.clientX + y6])
        }
      })

      store.dispatch('module/getLinkList', store.getters['module/getLoginUser'].id)
        .then(function (result) {
          console.log(result.data, '외부링크 목록')
          store.commit('module/setLinkList', result.data)
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
      store.dispatch('module/go')
        .then(function (result) {
          console.log('go', result.data.substring(0, 8))
          store.commit('module/setCheckInTime', result.data.substring(0, 8))
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
      store.dispatch('module/out')
        .then(function (result) {
          console.log('out', result.data.substring(0, 8))
          store.commit('module/setCheckOutTime', result.data.substring(0, 8))
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
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">먼저 출근을 진행해주세요.</span>',
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
      const userId = localStorage.getItem('id')
      store.dispatch('module/registTodo', { userId: userId, departmentId: departmentId, time: todoTime.value, title: state.todoText })
        .then(function () {
          const dpId = localStorage.getItem('departmentId')
          store.dispatch('module/getTodoList', dpId)
            .then(function (result) {
              var tmp = []
              for (let i = 0; i < result.data.length; i++) {
                result.data[i].do = false
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
    const CheckMode1 = function () {
      const tmp = store.getters['module/getMode1']
      store.commit('module/setMode1', !tmp)
      mode.value = true
    }
    const CheckMode2 = function () {
      const tmp = store.getters['module/getMode2']
      store.commit('module/setMode2', !tmp)
      mode.value = true
    }
    const CheckMode3 = function () {
      const tmp = store.getters['module/getMode3']
      store.commit('module/setMode3', !tmp)
      mode.value = true
    }
    const CheckMode4 = function () {
      const tmp = store.getters['module/getMode4']
      store.commit('module/setMode4', !tmp)
      mode.value = true
    }
    const CheckMode5 = function () {
      const tmp = store.getters['module/getMode5']
      store.commit('module/setMode5', !tmp)
      mode.value = true
    }
    const CheckMode6 = function () {
      const tmp = store.getters['module/getMode6']
      store.commit('module/setMode6', !tmp)
      mode.value = true
    }
    const reset = function () {
      store.commit('module/setModePx1', [15, 20])
      store.commit('module/setModePx2', [561, 20])
      store.commit('module/setModePx3', [15, 385])
      store.commit('module/setModePx4', [261, 385])
      store.commit('module/setModePx5', [15, 1215])
      store.commit('module/setModePx6', [381, 1215])

      const today = document.getElementById('topLeft')
      const vacation = document.getElementById('bottomLeft')
      const week = document.getElementById('topRight')
      const recent = document.getElementById('botRight')
      const todo = document.getElementById('endRight')
      const end = document.getElementById('endBottom')

      today.style.top = 15 + 'px'
      today.style.left = 20 + 'px'
      vacation.style.top = 561 + 'px'
      vacation.style.left = 20 + 'px'
      week.style.top = 15 + 'px'
      week.style.left = 385 + 'px'
      recent.style.top = 261 + 'px'
      recent.style.left = 385 + 'px'
      todo.style.top = 15 + 'px'
      todo.style.left = 1215 + 'px'
      end.style.top = 381 + 'px'
      end.style.left = 1215 + 'px'

      store.commit('module/setMode1', true)
      store.commit('module/setMode2', true)
      store.commit('module/setMode3', true)
      store.commit('module/setMode4', true)
      store.commit('module/setMode5', true)
      store.commit('module/setMode6', true)

      mode.value = true
    }
    const addLink = function (id, link) {
      this.text = ''
      const body = {
        id: id,
        link: link
      }
      store.dispatch('module/addLinkList', body)
        .then(function (result) {
          store.commit('module/addLinkList', result.data)
          console.log(result.data)
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">해당 주소는 북마크 지정할 수 없습니다.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
      setTimeout(function () {
        const bar = document.getElementById('weblist')
        bar.scrollTop = bar.scrollHeight
      }, 300)
    }
    const deleteSite = function (id) {
      console.log(id)
      store.dispatch('module/deleteLink', id)
        .then(function () {
          store.dispatch('module/getLinkList', store.getters['module/getLoginUser'].id)
            .then(function (result) {
              store.commit('module/setLinkList', result.data)
            })
        })
    }
    const colorMode1 = function () {
      document.getElementById('topLeft').style.background = 'rgb(212, 212, 212)'
    }
    const colorMode11 = function () {
      document.getElementById('topLeft').style.background = 'white'
    }
    const colorMode2 = function () {
      document.getElementById('bottomLeft').style.background = 'rgb(212, 212, 212)'
    }
    const colorMode22 = function () {
      document.getElementById('bottomLeft').style.background = 'white'
    }
    const colorMode3 = function () {
      document.getElementById('topRight').style.background = 'rgb(212, 212, 212)'
    }
    const colorMode33 = function () {
      document.getElementById('topRight').style.background = 'white'
    }
    const colorMode4 = function () {
      document.getElementById('botRight').style.background = 'rgb(212, 212, 212)'
    }
    const colorMode44 = function () {
      document.getElementById('botRight').style.background = 'white'
    }
    const colorMode5 = function () {
      document.getElementById('endRight').style.background = 'rgb(212, 212, 212)'
    }
    const colorMode55 = function () {
      document.getElementById('endRight').style.background = 'white'
    }
    const colorMode6 = function () {
      document.getElementById('endBottom').style.background = 'rgb(212, 212, 212)'
    }
    const colorMode66 = function () {
      document.getElementById('endBottom').style.background = 'white'
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
      check: ref(false),
      mode1,
      mode2,
      mode3,
      mode4,
      mode5,
      mode6,
      CheckMode1,
      CheckMode2,
      CheckMode3,
      CheckMode4,
      CheckMode5,
      CheckMode6,
      mode,
      reset,
      text: ref(''),
      linkList,
      addLink,
      // dragStart,
      colorMode1,
      colorMode11,
      colorMode2,
      colorMode22,
      colorMode3,
      colorMode33,
      colorMode4,
      colorMode44,
      colorMode5,
      colorMode55,
      colorMode6,
      colorMode66,
      deleteSite
    }
  }
})

</script>

<style scoped>
#content{
  width:100%;
  height: 908px;
}
#main{
  width:1858px;
  height:908px;
  float:right;
  position: absolute;
  top: 60px;
  left: 60px;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: rgb(242, 247, 244);
}
#topLeft{
  width:350px;
  height:530px;
  border: 1px solid rgb(212, 212, 212);
  display:inline-block;
  position:absolute;
  top:15px;
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
  cursor:pointer;
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
.userInfo {
  float: left;
  width: 150px;
  height: 180px;
  text-align: center;
  line-height: 40px;
  font-size: 16px;
  margin-top: 25px;
}
.clock {
  height: 50px;
  width: 250px;
  margin-left: 37px;
  margin-top: 350px;
  text-align: center;
  font-size: 45px;
  font-family: 'LAB디지털';
  color: rgb(240, 171, 44)
}
.checkbtn {
  border-right:1px;
  Font-size:20px;
  color: #249752;
  font-weight: bold;
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
  height: 333px;
  float: left;
  border: 1px solid rgb(212, 212, 212);
  position: absolute;
  top:561px;
  left:20px;
  border-radius: 4%;
  animation: leftFadeIn 1.1s ease-in-out;
}
.img {
  float: left;
  width: 33%;
  color: #249752;
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
  margin-top: 30px;
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
  top:15px;
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
  top:15px;
  left:1215px;
  border-radius: 10px;
  animation: rightFadeIn 1.1s ease-in-out;
}
#endBottom{
  float:right;
  width:550px;
  height:513px;
  border:0.5px solid rgb(212, 212, 212);
  background-color: white;
  position: absolute;
  top:381px;
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
  height:633px;
  top:261px;
  left:385px;
  border:0.5px solid rgb(212, 212, 212);
  background-color:white;
  border-radius: 1.5%;
  animation: bottomFadeIn 1.1s ease-in-out;
}
#todoForm{
  /* width:290px; */
  height:440px;
  background-color:white;
  margin-top:380px;
  border:1px solid rgb(212, 212, 212);
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
.to-right-underline{
  position: relative;
}
.to-right-underline:after{
  content: "";
  position: absolute;
  left: 0;
  bottom: -10px;
  width: 0px;
  height: 2px;
  margin: 5px 0 0;
  transition: all 0.2s ease-in-out;
  transition-duration: 0.3s;
  opacity: 0;
  background-color: #8fd3f4;
}
.to-right-underline:hover:after{
  width: 100%;
  opacity: 1;
}
.deleteIcon {
  font-size: 2.0rem;
  color: gray;
}
.deleteIcon:hover {
  color: rgb(241, 74, 74);
}
#weblist {
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
}
#weblist::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
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
@font-face {
  font-family: 'LAB디지털';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/LAB디지털.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
body {
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
