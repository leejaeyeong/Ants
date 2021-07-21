import $axios from 'axios'

// export function someAction (/* context */) {
// }
export function login ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/api/v1/auth/login'
  const body = payload
  return $axios.post(url, body)
}
