import $axios from 'axios'

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  const body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/users'
  const body = payload
  return $axios.post(url, body)
}
export function requestCheckId ({ state }, id) {
  console.log('requestCheckId', state, id)
  const url = '/users/' + id
  return $axios.get(url)
}

export function requestUserAuth ({ state }, token) {
  console.log('[action]requestUserAuth', state, token)
  const url = '/users/me'
  return $axios.get(url, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  })
}
