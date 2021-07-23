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
  const url = 'http://localhost:8080/api/v1/auth/users'
  const body = payload
  console.log(body)
  return $axios.post(url, body)
}

export function requestCheckId ({ state }, id) {
  console.log('requestCheckId', state, id)
  const url = 'http://localhost:8080/api/v1/users/' + id
  return $axios.get(url)
}
