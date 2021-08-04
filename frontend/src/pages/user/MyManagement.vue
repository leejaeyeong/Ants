<template>
  <div id="myData">
    <div class="q-pa-md">
      <q-markup-table separator="cell" flat bordered>
          <thead>
            <tr>
              <th colspan="7">
                <div class="row no-wrap items-center">
                  <div class="year">{{ viewYear }}</div>
                  <br>
                  <div class="month">{{ viewMonth }}</div>
                  <br>
                  <div class="nav">
                      <button class="nav-btn go-prev" @click="prevMonth">&lt;</button>
                      <button class="nav-btn go-today">Today</button>
                      <button class="nav-btn go-next" @click="nextMonth">&gt;</button>
                  </div>
                </div>
              </th>
            </tr>
            <tr>
              <th class="text-right">Sun.</th>
              <th class="text-right">Mon.</th>
              <th class="text-right">Tue.</th>
              <th class="text-right">Wed.</th>
              <th class="text-right">Thu</th>
              <th class="text-right">Fri.</th>
              <th class="text-right">Sat.</th>
            </tr>
          </thead>
        <tbody>
          <tr v-for="(date, idx) in renderCalendar()" :key="idx" >
            <td
              v-for="(day, secondIdx) in date" :key="secondIdx" class="q-td--no-hover" style="height: 110px; width:100px;" @click="clickday(day)"
            >
              <span v-if="(idx > 3 && day < 6) || (idx <2 && day >25)" style="opacity: .3; ">{{ day }}</span>
              <span v-else-if="(secondIdx === 0)" style="color: red;">{{ day }}</span>
              <span v-else-if="(secondIdx === 6)" style="color: blue;">{{ day }}</span>
              <span v-else-if="(Today === day)" class="today">{{ day }}</span>
              <span v-else>{{ day }}</span>
            </td>
          </tr>
        </tbody>
      </q-markup-table>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'management',
  setup () {
    const date = new Date()
    const viewYear = date.getFullYear()
    const viewMonth = date.getMonth() + 1

    // 달력 렌더링함수
    const renderCalendar = function (month) {
      const viewYear = date.getFullYear()
      const viewMonth = date.getMonth()
      // if(month === 0) {
      //   viewMonth ==

      // }
      // year-month 채우기

      // 지난 달 마지막 Date, 이번 달 마지막 Date
      const prevLast = new Date(viewYear, viewMonth, 0)
      const thisLast = new Date(viewYear, viewMonth + 1, 0)

      const PLDate = prevLast.getDate()
      const PLDay = prevLast.getDay()

      const TLDate = thisLast.getDate()
      const TLDay = thisLast.getDay()

      // Dates 기본 배열들
      const prevDates = []
      const thisDates = [...Array(TLDate + 1).keys()].slice(1)
      const nextDates = []

      // prevDates 계산
      if (PLDay !== 6) {
        for (let i = 0; i < PLDay + 1; i++) {
          prevDates.unshift(PLDate - i)
        }
      }

      // nextDates 계산
      for (let i = 1; i < 7 - TLDay; i++) {
        nextDates.push(i)
      }

      // 저번달, 이번달, 다음달 날짜 합치기
      const monthday = []
      let weekday = []
      const dates = prevDates.concat(thisDates, nextDates)
      for (let i = 0; i < dates.length; i++) {
        weekday.push(dates[i])
        if (weekday.length === 7) {
          monthday.push(weekday)
          weekday = []
        }
      }
      console.log(monthday, '함수안에서monthday')
      return monthday
    }
    // const resultMonth = renderCalendar()
    // console.log(resultMonth, 'resultmonth')

    const prevMonth = function () {
      date.setMonth(date.getMonth() - 1)
      console.log('prev')
      renderCalendar()
    }

    const nextMonth = function () {
      date.setMonth(date.getMonth() + 1)
      renderCalendar()
    }
    // 오늘날짜 표시해주기
    const Today = date.getDate()
    console.log(Today)
    // // // 날짜가져옴
    // const timeStamp = Date.now()
    // const viewYear = date.formatDate(timeStamp, 'YYYY')
    // const viewMonth = date.formatDate(timeStamp, 'MM')
    // console.log(viewYear, viewMonth)

    // // 지난달
    // const prevLast = new Date(viewYear, viewMonth - 1, 0)
    // // 마지막날짜
    // const PLDate = prevLast.getDate()
    // // 요일
    // const PLDay = prevLast.getDay()
    // console.log('지난달', prevLast, PLDate, PLDay)
    // // 이번달
    // const thisLast = new Date(viewYear, viewMonth, 0)
    // const TLDate = thisLast.getDate()
    // const TLDay = thisLast.getDay()
    // console.log('이번달', thisLast, TLDate, TLDay)

    // const prevDates = []
    // const thisDates = [...Array(TLDate + 1).keys()].slice(1)
    // const nextDates = []
    // console.log(thisDates)
    // if (PLDay !== 6) {
    //   for (let i = 0; i < PLDay + 1; i++) {
    //     prevDates.unshift(PLDate - i)
    //   }
    // }

    // for (let i = 1; i < 7 - TLDay; i++) {
    //   nextDates.push(i)
    // }
    // // 저번달, 이번달, 다음달 날짜 합치기
    // const monthday = []
    // let weekday = []
    // const day = 0
    // const dates = prevDates.concat(thisDates, nextDates)
    // console.log(dates)
    // console.log(monthday, weekday, day, dates, '96라인')
    // console.log(dates.length)
    // for (let i = 0; i < dates.length; i++) {
    //   weekday.push(dates[i])
    //   if (weekday.length === 7) {
    //     monthday.push(weekday)
    //     weekday = []
    //   }
    // }

    // 날짜 클릭이벤트 만들기 => 매일 출퇴근시간 받아오기, 투두리스트 crud
    // 모달창?
    const clickday = function (day) {
      alert(day, '클릭함')
    }
    // 스타일 정리하기
    return {
      viewYear,
      viewMonth,
      Today,
      prevMonth,
      nextMonth,
      clickday,
      renderCalendar
    }
  }
})
</script>

<style scoped>
.today {
  color: #ffffff;
  display: block;
  width: 30px;
  background-color: #19CE60;
  height: 30px;
  border-radius: 50%;
  position: relative;
  opacity: .6;
}
</style>
