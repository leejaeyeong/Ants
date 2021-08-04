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
export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = baseUrl + 'api/v1/users'
  const body = payload
  console.log(body)
  return $axios.post(url, body, { headers: { 'Content-Type': 'multipart/form-data' } })
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

// 근태 정보(날짜, 출퇴근 시간)
