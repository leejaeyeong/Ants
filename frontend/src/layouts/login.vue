<template>
    <div class="header">
        <!--Content before waves-->
        <div class="inner-header flex">
            <div id='loginbox'>
                <div id="left">
                  <div id="mark">
                    <q-img src="~assets/logo.png" style="width:200px; margin-left:140px;"/>
                  </div>
                  <div id="form">
                    <q-input v-model="state.form.id" class="input" filled label="ID" />
                    <q-input v-model="state.form.password" class="input" filled type="password" label="password" />
                    <q-btn @click="login" class="btn" color="primary" label="Login" />
                    <q-btn @click="mvSignUp" class="btn" color="deep-orange" glossy label="SignUp" />
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
      this.$router.push('/signUp')
    }
  },
  setup () {
    const state = reactive({
      form: {
        id: '',
        password: ''
      }
    })
    const router = useRouter()
    // router를 사용하기 위해선 useRouter import후 변수 선언하여 사용 해야함 !
    const store = useStore()
    // store를 사용하기 위해선 useStore import후 변수 선언하여 사용 해야함 !
    const login = function () {
      store.dispatch('module/login', { id: state.form.id, password: state.form.password })
        .then(function (result) {
          console.log(result)
          alert('accessToken: ' + result.data.accessToken)
          // 로컬스토리지 등록하기
          localStorage.setItem('token', result.data.accessToken)
          localStorage.setItem('id', state.form.id)
          localStorage.setItem('password', state.form.password)
          router.push('/main')
        })
        .catch(function () {
          alert('아이디, 비밀번호를 확인해주세요 !')
        })
    }
    return { state, login }
  }
})
</script>

<style scoped>
#loginbox{
    width:1000px;
    height:500px;
    margin:120px auto;
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
  background-image: url('assets/office.jpg');
}
#form{
  margin-top:-40px;
}
.btn{
  margin:0 5px;
}
.header {
  position:relative;
  text-align:center;
  background: linear-gradient(60deg, rgba(84,58,183,1) 0%, rgba(0,172,193,1) 100%);
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
</style>
