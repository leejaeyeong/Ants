import $axios from 'axios'

const baseUrl = 'https://localhost:8443/'
// const baseUrl = 'https://i5b103.p.ssafy.io:8443/'

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
  console.log('유저 정보 조회', state)
  const url = baseUrl + 'api/v1/users/me'
  const userToken = localStorage.token
  return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}
// 유저정보 수정
export function editInfo ({ state }, body) {
  console.log('editInfo', state, body)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id
  // const userToken = localStorage.token
  return $axios.put(url, body)
}
// 유저 탈퇴
export function deleteUser ({ state }) {
  console.log('유저 탈퇴', state)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id
  return $axios.delete(url)
}
// 출근
export function go ({ state }) {
  console.log('go', state)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id + '/check-in'
  return $axios.post(url)
}
// 퇴근
export function out ({ state }) {
  console.log('out', state)
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id + '/check-out'
  return $axios.put(url)
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

export function getRooms () {
  const url = baseUrl + 'api/v1/rtc/'
  return $axios.get(url)
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
  console.log(state, '게시글등록')
  const url = baseUrl + 'api/v1/board'
  return $axios.post(url, body)
}

export function registComment ({ state }, body) {
  console.log('registComment', state, body)
  const id = body.id
  const url = baseUrl + 'api/v1/board/' + id + '/comment'
  return $axios.post(url, body)
}
// 파일 업로드
export function uploadFile ({ state }, body) {
  console.log(state)
  const url = baseUrl + 'api/v1/files/upload' + '?userId=' + localStorage.getItem('id')
  return $axios.post(url, body)
}

export function loadFileDataByFileName ({ state }, fileName) {
  console.log(state)
  const url = baseUrl + 'api/v1/files/search' + '?fileName=' + fileName
  return $axios.get(url)
}
// // 그룹 전체목록
// export function getGroup ({ state }, body) {
//   const url = baseUrl + 'api/v1/group'
//   return $axios.get(url, body)
// }
// // 그룹 생성
// export function setGroup({ state }, body) {
//   const url = baseUrl + 'api/v1/group'
//   return $axios.post(url, body)
// }
export function downloadFile ({ state }, id) {
  console.log(state)
  const url = baseUrl + 'api/v1/files/download/' + id
  return $axios.get(url, { responseType: 'blob' })
}

export function loadFileData () {
  const url = baseUrl + 'api/v1/files'
  return $axios.get(url)
}

// 조횟수 증가
export function countView ({ state }, id) {
  console.log('countView', state, id)
  const url = baseUrl + 'api/v1/board/' + id
  return $axios.put(url)
}

// 가입한 모든 회원정보 조회
export function allUser ({ state }) {
  console.log('전체유저조회', state)
  const url = baseUrl + 'api/v1/users'
  return $axios.get(url)
}

export function montlyWork ({ state }, yearmonth) {
  console.log('한달근태요청')
  const year = yearmonth.split('.')[0]
  const month = yearmonth.split('.')[1]
  const id = localStorage.getItem('id')
  const url = baseUrl + 'api/v1/users/' + id + '/attendance/' + year + '/' + month
  return $axios.get(url)
}

// 가입한 모든 회원정보 조회
export function memberList ({ state }) {
  console.log('memberList', state)
  const url = baseUrl + 'api/v1/users'
  return $axios.get(url)
}

export function loadFileDataByExtension ({ state }, extension) {
  const url = baseUrl + 'api/v1/files/extension' + '?extension=' + extension
  return $axios.get(url)
}
// 유저 권한변경
export function chageUser ({ state }, id) {
  console.log('유저 권한 변경', state, id)
  const url = baseUrl + 'api/v1/users/' + id + '/auth'
  return $axios.put(url)
}

export function registTodo ({ state }, body) {
  console.log('registTodo', state, body)
  const url = baseUrl + 'api/v1/todo/register'
  return $axios.post(url, body)
}

export function getTodoList ({ state }, departmentId) {
  console.log('getTodoList', state, departmentId)
  const url = baseUrl + 'api/v1/todo/' + '?departmentId=' + departmentId
  return $axios.get(url)
}

export function markList ({ state }, userId) {
  console.log('markList', state, userId)
  const url = baseUrl + 'api/v1/board/marker/' + '?userId=' + userId
  return $axios.get(url)
}

export function mark ({ state }, id) {
  console.log('mark', state, id)
  const userId = state.loginUser.id
  const url = baseUrl + 'api/v1/board/' + id + '/marker/?userId=' + userId
  return $axios.put(url)
}

export function boardMe ({ state }, id) {
  console.log('boardMe', state, id)
  const url = baseUrl + 'api/v1/board/userId/?userId=' + id
  return $axios.get(url)
}

export function dmRoomList ({ state }, id) {
  const url = baseUrl + 'api/v1/dm/' + '?userId=' + id
  return $axios.get(url)
}

export function sendDm ({ state }, body) {
  const url = baseUrl + 'api/v1/dm/send'
  return $axios.post(url, body)
}

export function getDmHistory ({ state }, room) {
  const url = baseUrl + 'api/v1/dm/history?roomId=' + room
  return $axios.get(url)
}

export function sendEmail ({ state }, { name, email }) {
  console.log(state, name, email, '이메일 보냄')
  const url = baseUrl + 'api/v1/email/' + name + '/' + email
  return $axios.get(url)
}

export function loadFileDataByDepartment ({ state }, id) {
  const url = baseUrl + 'api/v1/files/department' + '?id=' + id
  return $axios.get(url)
}

// 매일매일의 기록 요청
export function getAlllog ({ state }, { year, month, day, departmentId }) {
  console.log(year, month, day, departmentId, '정해진 날의 기록 요청')
  if (String(month).length === 1) {
    month = '0' + String(month)
  }
  const date = year + '-' + month + '-' + day
  console.log(date)
  const url = baseUrl + 'api/v1/calendar' + '?date=' + date + '&departmentId=' + departmentId
  return $axios.get(url)
}

export function getLinkList ({ state }, id) {
  const url = baseUrl + 'api/v1/open/links?id=' + id
  return $axios.get(url)
}

export function addLinkList ({ state }, body) {
  const url = baseUrl + 'api/v1/open/addLink?id=' + body.id + '&link=' + body.link
  return $axios.get(url)
}

export function deleteLink ({ state }, id) {
  const url = baseUrl + 'api/v1/open/link/' + id
  return $axios.delete(url)
}
