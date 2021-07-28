import $axios from 'axios'

// export function someAction (/* context */) {
// }
export function login ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = 'https://localhost:8443/api/v1/auth/login'
  const body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = 'api/v1/auth/users'
  const body = payload
  return $axios.post(url, body)
}
