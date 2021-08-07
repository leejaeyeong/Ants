import $axios from 'axios'

const baseUrl = 'https://localhost:8443/'
// 로그인
export function login ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = baseUrl + 'api/v1/auth/login'
  const body = payload
  console.log(body)
  return $axios.post(url, body)
}
// 회원가입
export function requestSignup ({ state }, body) {
  console.log(state)
  const url = baseUrl + 'api/v1/users'
  console.log(body)
  console.dir(body)
  return $axios.post(url, body)
}
// 아이디 중복체크
export function requestCheckId ({ state }, id) {
  console.log('requestCheckId', state, id)
  const url = baseUrl + 'api/v1/users/' + id
  return $axios.get(url)
}
// 유저정보
export function requestInfo ({ state }, header) {
  console.log('requestInfo', state, header)
  const url = baseUrl + 'api/v1/users/me'
  const userToken = localStorage.token
  console.log(userToken, '<-토큰이랑 헤더')
  return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}
// 유저정보 수정
export function editInfo ({ state }, header, id) {
  console.log('editInfo', state, header)
  const url = baseUrl + 'api/v1/users/' + id
  const userToken = localStorage.token
  return $axios.put(url, { headers: { Authorization: `Bearer ${userToken}` } })
}
// 유저 탈퇴
export function deleteUser ({ state }, id) {
  console.log('deleteUser', state)
  const url = baseUrl + 'api/v1/users/' + id
  return $axios.delete(url)
}
// 출근
export function go ({ state }, payload) {
  console.log('go', state, payload)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id + '/check-in'
  const body = payload
  return $axios.post(url, body)
}
// 퇴근
export function out ({ state }, payload) {
  console.log('out', state, payload)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id + '/check-out'
  const body = payload
  return $axios.put(url, body)
}
// 당일근태
export function check ({ state }, payload) {
  console.log('out', state, payload)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id + '/attendance'
  const body = payload
  return $axios.get(url, body)
}
// 1주일 근태기록
export function loadAttendanceByWeek ({ state }, payload) {
  const id = localStorage.getItem('id')
  const currentDay = new Date()
  const theYear = currentDay.getFullYear()
  const theMonth = currentDay.getMonth()
  const theDate = currentDay.getDate()
  const theDayOfWeek = currentDay.getDay()
  const thisWeek = []
  for (let i = 0; i < 7; i++) {
    const resultDay = new Date(theYear, theMonth, theDate + (i - theDayOfWeek))
    if (currentDay < resultDay) {
      break
    }
    const yyyy = resultDay.getFullYear()
    let mm = Number(resultDay.getMonth()) + 1
    let dd = resultDay.getDate()
    mm = String(mm).length === 1 ? '0' + mm : mm
    dd = String(dd).length === 1 ? '0' + dd : dd
    thisWeek[i] = yyyy + '-' + mm + '-' + dd
  }
  console.log(thisWeek[0] + '  ' + thisWeek[thisWeek.length - 1])
  const url = baseUrl + 'api/v1/users/' + id + '/attendance/week?startDate=' + thisWeek[0] + '&endDate=' + thisWeek[thisWeek.length - 1]
  return $axios.get(url, payload)
}

// // 한달 근태
// export function getMonthwork (payload, date) {
//   console.log('action.js 함수실행')
//   console.log(date)
//   if (date.length !== 6) {
//     return alert('잘못된 요청입니다.')
//   }
//   const year = date.substring(0, 4)
//   const month = date.substring(4, 6)
//   const id = localStorage.getItem('id')
//   console.log(year, month)
//   const url = baseUrl + 'api/v1/users/' + id + '/attendance/' + year + '/' + month
//   return $axios.get(url, payload)
// }

export function getRooms () {
  const rooms = $axios.get('http://localhost:8080/api/v1/rtc/')
  return rooms
}

export function board ({ state }, payload) {
  console.log('board axios', state, payload)
  const url = baseUrl + 'api/v1/board'
  const body = payload
  return $axios.get(url, body)
}

export function boardList ({ state }, payload) {
  console.log('boardList axios', state, payload)
  const url = baseUrl + 'api/v1/board/type'
  const body = payload
  return $axios.get(url, body)
}

export function boardType ({ state }, id) {
  console.log('boardType axios', state, id)
  const url = baseUrl + 'api/v1/board/type/' + id
  return $axios.get(url)
}

export function boardDetail ({ state }, id) {
  console.log('boardDetail axios', state, id)
  const userId = state.loginUser.id
  const url = baseUrl + 'api/v1/board/' + id + '?userId=' + userId
  return $axios.get(url)
}

export function departmentInfo ({ state }) {
  console.log(state)
  const url = baseUrl + 'api/v1/department'
  return $axios.get(url)
}

// 게시글 등록
export function registBoard ({ state }, body) {
  console.log(state)
  const url = baseUrl + 'api/v1/board'
  return $axios.post(url, body)
}

// 파일 업로드
export function uploadFile ({ state }, body) {
  console.log(state)
  const url = baseUrl + 'api/v1/files/upload' + '?userId=test-1'
  return $axios.post(url, body)
}
