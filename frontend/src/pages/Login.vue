<template>
    <div class="header">
        <!--Content before waves-->
        <div class="inner-header flex">
            <div id='loginbox'>
                <div id="left">
                  <div id="mark">
                    <q-img src="~assets/images/logo.png" style="width:200px; margin-left:135px; margin-top:75px;"/>
                  </div>
                  <div id="form">
                    <q-input color="teal" v-on:keyup.enter="login" v-model="state.form.id" class="input" filled label="ID" />
                    <q-input color="teal" v-on:keyup.enter="login" v-model="state.form.password" class="input" filled type="password" label="Password" />
                    <q-btn @click="login" class="btn;" style="background-color: #00BF6F;" label="Login" />
                    <q-btn @click="mvSignUp" class="btn" style="color: #00BF6F;" flat label="SignUp →" />
                    <!-- <q-btn @click="mvSignUp" flat style="color: black;" label="signUp"/> -->
                  </div>
                </div>
                <div id="right">
                </div>
            </div>
        </div>
        <!--Waves Container-->
        <div>
        <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
        viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
        <defs>
        <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
        </defs>
        <g class="parallax">
        <use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(255,255,255,0.7" />
        <use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(255,255,255,0.5)" />
        <use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(255,255,255,0.3)" />
        <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" />
        </g>
        </svg>
        </div>
        <!--Waves end-->

        </div>
        <!--Header ends-->

        <!--Content starts-->
        <div class="content flex">

        </div>
        <!--Content ends-->
</template>

<script>
import { reactive, defineComponent } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'login',
  methods: {
    mvSignUp () {
      this.$router.push('/signup')
    }

  },
  setup () {
    const state = reactive({
      form: {
        id: '',
        password: ''
      },
      image: null
    })
    const router = useRouter()
    // router를 사용하기 위해선 useRouter import후 변수 선언하여 사용 해야함 !
    const store = useStore()
    // store를 사용하기 위해선 useStore import후 변수 선언하여 사용 해야함 !
    var rowsM = []
    // 로그인
    const Swal = require('sweetalert2')
    const login = function () {
      store.dispatch('module/login', { id: state.form.id, password: state.form.password })
        .then(function (result) {
          // Swal.fire('accessToken: ' + result.data.accessToken)
          // 로컬스토리지 등록하기
          localStorage.setItem('token', result.data.accessToken)
          localStorage.setItem('id', state.form.id)
          localStorage.setItem('password', state.form.password)
          store.dispatch('module/requestInfo')
            .then(response => {
              const userInfo = {
                id: response.data.userId,
                name: response.data.name,
                department: response.data.department,
                profileLocation: response.data.profileLocation,
                userState: response.data.state,
                dpID: response.data.department,
                position: response.data.position
              }
              console.log('유저정보', userInfo)
              localStorage.setItem('name', userInfo.name)
              localStorage.setItem('userState', userInfo.userState)
              localStorage.setItem('departmentId', userInfo.department)
              localStorage.setItem('position', userInfo.position)
              store.dispatch('module/departmentInfo')
                .then(function (result) {
                  for (let i = 0; i < result.data.length; i++) {
                    if (userInfo.department === result.data[i].id) {
                      userInfo.department = result.data[i].departmentName
                      store.commit('module/setLoginUser', userInfo)
                      localStorage.setItem('department', userInfo.department)
                      localStorage.setItem('profileLocation', userInfo.profileLocation)
                    }
                  }
                })
              // store.state.userId = userInfo.id
              // store.state.name = userInfo.name
              store.dispatch('module/board', { })
                .then(function (result) {
                  for (let i = 0; i < result.data.length; i++) {
                    rowsM.push(result.data[i])
                  }
                  store.commit('module/setRowsM', rowsM)
                  rowsM = []
                  store.commit('module/setChatMode', false)

                  const userstate = localStorage.getItem('userState')

                  if (userstate === '3') {
                    Swal.fire({
                      title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">승인 대기 상태입니다.</span>',
                      confirmButtonColor: '#ce1919',
                      confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
                    })
                  } else {
                    Swal.fire({
                      title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">환영합니다.</span>',
                      confirmButtonColor: '#19CE60',
                      confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
                    })
                      .then(function () {
                        router.push('/main')
                      })
                  }
                })
            }).catch(err => {
              console.log(err, '에러입니다')
            })
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">아이디 또는 비밀번호를 확인해주세요</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }
    store
      .dispatch('module/departmentInfo')
      .then(function (result) {
        console.log('axios response')
        console.log(result.data)
        console.log('axios response')
        store.commit('module/setDepartmentInfo', result.data)

        console.log(store.department)
      })
      .catch(function (err) {
        console.log(err)
      })
    return { state, login }
  }
})
</script>

<style scoped>
#loginbox{
    width:1000px;
    height:500px;
    margin:220px auto;
    border-radius: 15px;
    background-color:whitesmoke;
    z-index: 100;
}
#mark{
    width:100px;
    text-align:center;
    font-size:40px;
    color:black;
    margin-left:15px;
}
.input{
  width:40%;
  margin:20px auto;
}
#left{
  width:50%;
  height:100%;
  margin:0;
  float:left;
}
#right{
  width:50%;
  height:100%;
  margin:0;
  float:right;
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  background-image: url('https://images.unsplash.com/photo-1535957998253-26ae1ef29506?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHdvcmt8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60');
  background-size:500px;
}
#form{
  margin-top:30px;
}
.btn{
  margin:0 5px;
}
.swal-wide{
  width:50px !important;
}
.header {
  position:relative;
  text-align:center;
  background: linear-gradient(60deg, #19CE60 0%, #00B0B9 100%);
  /* background: linear-gradient(60deg, rgba(84,58,183,1) 0%, rgba(0,172,193,1) 100%); */
  color:white;
}

.inner-header {
  height:65vh;
  width:100%;
  margin: 0;
  padding: 0;
}

.flex { /*Flexbox for containers*/
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.waves {
  position:relative;
  width: 100%;
  height:15vh;
  margin-bottom:-7px; /*Fix for safari gap*/
  min-height:100px;
  max-height:150px;
}

.content {
  position:relative;
  height:20vh;
  text-align:center;
  background-color: white;
}

/* Animation */

.parallax > use {
  animation: move-forever 25s cubic-bezier(.55,.5,.45,.5)     infinite;
}
.parallax > use:nth-child(1) {
  animation-delay: -2s;
  animation-duration: 7s;
}
.parallax > use:nth-child(2) {
  animation-delay: -3s;
  animation-duration: 10s;
}
.parallax > use:nth-child(3) {
  animation-delay: -4s;
  animation-duration: 13s;
}
.parallax > use:nth-child(4) {
  animation-delay: -5s;
  animation-duration: 20s;
}

@keyframes move-forever {
  0% {transform: translate3d(-90px,0,0);}
  100% { transform: translate3d(85px,0,0);}
}
/*Shrinking for mobile*/
@media (max-width: 768px) {
  .waves {
    height:40px;
    min-height:40px;
  }
  .content {
    height:30vh;
  }
  h1 {
    font-size:24px;
  }
}
@font-face {
  font-family: 'NEXON Lv1 Gothic OTF';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
body{
  font-family: 'NEXON Lv1 Gothic OTF';
}
div{
  font-family: 'NEXON Lv1 Gothic OTF';
}
span{
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
