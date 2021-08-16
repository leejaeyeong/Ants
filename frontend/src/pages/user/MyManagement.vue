<template>
  <div id="myData">
    <div class="q-pa-md">
      <div class="section">
        <div class="subtitle txt">
          <q-btn flat round color="white" text-color="black" label="&lt;" @click="calendarData(-1)"/>
          {{ year }}년 {{ month }}월
          <q-btn flat round color="white" text-color="black" label="&gt;" @click="calendarData(1)"/>
        </div>
        <q-markup-table separator="cell" flat bordered class=" has-text-centered calendar">
          <thead>
            <th v-for="day in days" :key="day" style="background-color: rgb(3 185 74); font-size: 15px;">{{ day }}</th>
          </thead>
          <tbody>
            <tr v-for="(date, idx) in dates" :key="idx">
              <td
                v-for="(day, secondIdx) in date"
                :key="secondIdx"
                class="q-td--no-hover"
                style="height: 130px; width:80px;"
                @click="requestLog(year, month, day)"
              >
                <span v-if="(idx > 3 && day < 6) || (idx <2 && day >25)" class="other">{{ day }}</span>
                <span v-else-if="(secondIdx === 0)" style="color: red;">{{ day }}</span>
                <span v-else-if="(secondIdx === 6)" style="color: blue;">{{ day }}</span>
                <span v-else-if="(today === day && month === currentMonth)" class="today">{{ day }} Today</span>
                <span v-else>{{ day }}</span>
                <div v-for="(work, idx) in monthwork" :key="idx">
                  <div v-if="(idx === day)" class="work">{{ work[0] }}</div>
                  <div v-if="(idx === day)" class="work">{{ work[1] }}</div>
                </div>
              </td>
            </tr>
          </tbody>
        </q-markup-table>
      </div>
    </div>
    <div class="subDay">
      <div class="title">오늘의 기록</div>
      <q-scroll-area style="height: 420px; max-width: 300px;">
        <div v-for="(data, idx) in dayLog" :key="idx">
          <ul>
            <div class="vl"></div>
            <div class="user">
              <img :src= "data.profileLocation" style="width:30px; height:30px; margin-right:8px;">
              <span v-if="data.writer">{{data.writer}}님이 작성했습니다.</span>
              <span v-if="data.uploader">{{data.uploader}}님이 업로드 했습니다.</span>
              <span v-if="data.userName">{{data.userName}}님이 작성했습니다.</span>
            </div>
            <div class="logTitle">
              <q-item-label v-if="data.title">{{data.title}}</q-item-label>
              <q-item-label v-if="data.fileName">{{data.fileName}}</q-item-label>
            </div>
              <!-- <q-item-label caption lines="2" v-if="data.writer">{{data.writer}}님이 작성했습니다.</q-item-label>
              <q-item-label caption lines="2" v-if="data.uploader">{{data.uploader}}님이 업로드했습니다.</q-item-label>
              <q-item-label caption lines="2" v-if="data.userName">{{data.userName}}님이 작성했습니다.</q-item-label> -->
            <div class="time">
              <q-item-label caption>{{data.time}}</q-item-label>
            </div>
          </ul>
        </div>
      </q-scroll-area>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { useStore } from 'vuex'
import { computed } from 'vue'
export default {
  name: 'management',
  data () {
    return {
      days: [
        'Sun',
        'Mon',
        'Tue',
        'Wed',
        'Thu',
        'Fri',
        'Sat'
      ],
      dates: [],
      currentYear: 0,
      currentMonth: 0,
      year: 0,
      month: 0,
      lastMonthStart: 0,
      nextMonthStart: 0,
      today: 0,
      monthwork: [],
      worklog: []
    }
  },
  created () {
    const date = new Date()
    this.currentYear = date.getFullYear() // 현재 년, 월
    this.currentMonth = date.getMonth() + 1
    this.year = this.currentYear
    this.month = this.currentMonth
    this.today = date.getDate() // 오늘 날짜
    this.calendarData(this.month)
    this.getWork(this.year, this.month)
  },
  methods: {
    calendarData (arg) { // 인자를 추가
      if (arg < 0) { // -1이 들어오면 지난 달 달력으로 이동
        this.month -= 1
      } else if (arg === 1) { // 1이 들어오면 다음 달 달력으로 이동
        this.month += 1
      }
      if (this.month === 0) { // 작년 12월
        this.year -= 1
        this.month = 12
      } else if (this.month > 12) { // 내년 1월
        this.year += 1
        this.month = 1
      }
      const [
        monthFirstDay,
        monthLastDate,
        lastMonthLastDate
      ] = this.getFirstDayLastDate(this.year, this.month)
      this.dates = this.getMonthOfDays(
        monthFirstDay,
        monthLastDate,
        lastMonthLastDate
      )
      // 백에 요청
      this.getWork(this.year, this.month)
    },
    getFirstDayLastDate (year, month) {
      const firstDay = new Date(year, month - 1, 1).getDay() // 이번 달 시작 요일
      const lastDate = new Date(year, month, 0).getDate() // 이번 달 마지막 날짜
      let lastYear = year
      let lastMonth = month - 1
      if (month === 1) {
        lastMonth = 12
        lastYear -= 1
      }
      const prevLastDate = new Date(lastYear, lastMonth, 0).getDate() // 지난 달 마지막 날짜
      return [firstDay, lastDate, prevLastDate]
    },
    getMonthOfDays (
      monthFirstDay,
      monthLastDate,
      prevMonthLastDate
    ) {
      let day = 1
      let prevDay = (prevMonthLastDate - monthFirstDay) + 1
      const dates = []
      let weekOfDays = []
      while (day <= monthLastDate) {
        if (day === 1) {
          // 1일이 어느 요일인지에 따라 테이블에 그리기 위한 지난 셀의 날짜들을 구함
          for (let j = 0; j < monthFirstDay; j += 1) {
            if (j === 0) this.lastMonthStart = prevDay // 지난 달에서 제일 작은 날짜
            weekOfDays.push(prevDay)
            prevDay += 1
          }
        }
        weekOfDays.push(day)
        if (weekOfDays.length === 7) {
          // 일주일 채우면
          dates.push(weekOfDays)
          weekOfDays = [] // 초기화
        }
        day += 1
      }
      const len = weekOfDays.length
      if (len > 0 && len < 7) {
        for (let k = 1; k <= 7 - len; k += 1) {
          weekOfDays.push(k)
        }
      }
      if (weekOfDays.length > 0) dates.push(weekOfDays) // 남은 날짜 추가
      this.nextMonthStart = weekOfDays[0] // 이번 달 마지막 주에서 제일 작은 날짜
      console.log(dates)
      return dates
    },
    // 한달근태기록 조회
    getWork (year, month) {
      if (String(month).length === 1) {
        month = '0' + String(month)
      }
      const baseUrl = 'https://localhost:8443/'
      // const baseUrl = 'https://i5b103.p.ssafy.io:8443/'
      const id = localStorage.getItem('id')
      const requestData = {
        method: 'get',
        url: baseUrl + 'api/v1/users/' + id + '/attendance/' + year + '/' + month
      }
      // const worklog = new Array(31)
      axios(requestData)
        .then((response) => {
          this.worklog = response.data
          // 31개 빈배열
          this.monthwork = []
          for (let i = 0; i < 32; i++) {
            this.monthwork.push('')
          }
          for (let i = 0; i < this.worklog.length; i++) {
            const workdate = this.worklog[i].date
            const workcheckin = this.worklog[i].checkInTime
            const workcheckout = this.worklog[i].checkOutTime
            // 일한날짜
            const workday = workdate.split('-')[2]
            this.monthwork[Number(workday)] = [workcheckin, workcheckout]
          }
        })
        .catch(function (err) {
          console.log(err)
        })
    }
  },
  setup () {
    const dayLog = computed(() => store.getters['module/getDayLog'])
    const store = useStore()
    const requestLog = function (year, month, day) {
      console.log(year, month, day, '누른 날짜')
      const departmentId = localStorage.getItem('departmentId')
      store.dispatch('module/getAlllog', { year, month, day, departmentId })
        .then(function (result) {
          const daylog = []
          console.log(result)
          console.log(result.data, '달력에서 로그요청보냄')
          for (let i = 0; i < result.data.length; i++) {
            daylog.push(result.data[i])
          }
          console.log(daylog, '데이로그')
          store.commit('module/setDayLog', daylog)
        })
    }
    return { requestLog, dayLog }
  }
}
</script>

<style scoped>
.section{
  width: 1450px;
  margin-left: 40px;
  height: 770px;
}
.calendar{
  margin-top: -18px;
  border-radius: 10px;
}
.txt{
  font-size: 21px;
}
.today {
  display: block;
  width: 32px;
  background-color: #19CE60;
  height: 30px;
  border-radius: 50%;
  position: relative;
}

.other {
  opacity: .3;
}

.subtitle {
  text-align: center;
  margin: 2% auto;
}
.work {
  color: rgb(219, 130, 14);
  font-size: 16px;
  text-align: center;
  width: 50px;
  margin-left: 50px;
}
.subDay {
  background-color: white;
  height: 500px;
  width: 300px;
  position: absolute;
  bottom: 300px;
  margin-left: 1530px;
  border-radius: 10px;
}
.title{
  font-weight:bold;
  margin-left: 38px;
  margin-top: 20px;
  font-size:20px;
}
.vl {
  border-left: 5px solid green;
  height: 70px;
  float: left;
  display: inline-block;
  border-radius: 8px;
}
.user{
  width: 250px;
  height: 50px;
  margin-left: 25px;
  color: grey;
  font-size: 12px;
}
.logTitle{
  width: 250px;
  height: 27px;
  margin-left: 25px;
  font-size: 15px;
  margin-bottom: 5px;
  margin-top: -10px;
}
.time{
  width: 250px;
  height: 25px;
  margin-left: 150px;
  margin-top: 7px;
  font-size: 12px;
}
</style>
