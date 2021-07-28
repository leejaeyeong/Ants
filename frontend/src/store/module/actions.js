import $axios from 'axios'

// export function someAction (/* context */) {
// }
// 로그인
export function login ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = 'https://localhost:8443/api/v1/auth/login'
  const body = payload
  console.log(body)
  return $axios.post(url, body)
}
// 회원가입
export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = 'http://localhost:8080/api/v1/users'
  const body = payload
  console.log(body)
  return $axios.post(url, body)
}
// 아이디 중복체크
export function requestCheckId ({ state }, id) {
  console.log('requestCheckId', state, id)
  const url = 'http://localhost:8080/api/v1/users/' + id
  return $axios.get(url)
}

export function go ({ state }, payload) {
  console.log('go', state, payload)
  const id = localStorage.getItem('id')
  const url = 'http://localhost:8080/api/v1/users/' + id + '/check-in'
  const body = payload
  return $axios.post(url, body)
}

export function out ({ state }, payload) {
  console.log('out', state, payload)
  const id = localStorage.getItem('id')
  const url = 'http://localhost:8080/api/v1/users/' + id + '/check-out'
  const body = payload
  return $axios.put(url, body)
}

export function check ({ state }, payload) {
  console.log('out', state, payload)
  const id = localStorage.getItem('id')
  const url = 'http://localhost:8080/api/v1/users/' + id + '/attendance'
  const body = payload
  return $axios.get(url, body)
}

export function requestInfo ({ state }, header) {
  console.log('requestInfo', state, header)
  const url = 'http://localhost:8080/api/v1/users/me'
  const userToken = localStorage.token
  console.log(userToken, '<-토큰이랑 헤더')
  console.log('리퀘스트유저정보')
  return $axios.get(url, { headers: { Authorization: `Bearer ${userToken}` } })
}
