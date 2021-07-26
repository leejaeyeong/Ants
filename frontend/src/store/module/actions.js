import $axios from 'axios'

// export function someAction (/* context */) {
// }
export function login ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = 'http://localhost:8080/api/v1/auth/login'
  const body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = 'http://localhost:8080/api/v1/users'
  const body = payload
  console.log(body)
  return $axios.post(url, body)
}

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
