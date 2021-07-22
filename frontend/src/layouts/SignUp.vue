<template>
    <div class="header">
        <!--Content before waves-->
        <div class="inner-header flex">
            <div id='loginbox'>
                <div id="right">
                  <div id="mark">
                    <q-img src="~assets/logo.png" style="width:200px; margin-left:140px;" height="150px" />
                  </div>
                  <div class="q-pa-md" id="form" style="max-width: 400px">
                    <q-form
                      class="q-gutter-md"
                      ref="signupForm"
                      :model="state.form"
                    >
                      <q-input
                        class="input"
                        filled
                        label="ID *"
                        v-model="state.form.userId"
                        lazy-rules
                        :rules="[
                        val => !!val || '필수입력항목 입니다.',
                        val => val.length > 1 && val.length <= 16 || '2 ~ 16자까지 입력 가능합니다. '
                        ]"
                      />
                      <q-input
                        class="input"
                        filled
                        label="Name *"
                        v-model="state.form.name"
                        lazy-rules
                        :rules="[
                        val => !!val || '필수입력항목 입니다.',
                        val => val.length > 1 && val.length <= 16 || '2 ~ 16자까지 입력 가능합니다. '
                        ]"
                      />
                      <q-input class="input" filled :type="isPwd ? 'password' : 'text'"  label="Password *" v-model="state.form.password"
                      lazy-rules
                        :rules="[
                          val => val && val.length > 0 || '필수입력항목 입니다.',
                        ]">
                        <template v-slot:append>
                          <q-icon
                            :name="isPwd ? 'visibility_off' : 'visibility'"
                            class="cursor-pointer"
                            @click="isPwd = !isPwd"
                          />
                        </template>
                      </q-input>
                      <!-- 비밀번호 체크 -->
                      <!-- <q-input class="input" filled :type="isPwdCheck ? 'password' : 'text'"   label="Password Check *"
                      lazy-rules
                        :rules="[
                          val => val && val.length > 0 || '비밀번호 확인을 입력해주세요',
                        ]">
                        <template v-slot:append>
                          <q-icon
                            :name="isPwdCheck ? 'visibility_off' : 'visibility'"
                            class="cursor-pointer"
                            @click="isPwdCheck = !isPwdCheck"
                          />
                        </template>
                      </q-input> -->
                      <div class="btnform">
                        <q-btn @click="validate" label="Submit" type="submit" color="secondary"/>
                        <q-btn @click="back" label="back" color="amber" style="margin-left:10px;" />
                      </div>
                    </q-form>
                  </div>
                </div>
                <div id="left">
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
import { defineComponent, ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'signup',
  methods: {
    back () {
      this.$router.push('/')
    }
  },
  setup () {
    const signupForm = ref(null)

    const state = reactive({
      form: {
        userId: '',
        name: '',
        password: ''
      }
    })
    const router = useRouter()
    const store = useStore()

    function validate () {
      signupForm.value.validate().then(success => {
        if (success) {
          // yay, models are correct
          console.log('유효함')
          store.dispatch('module/requestSignup', {
            userId: state.form.userId,
            name: state.form.name,
            password: state.form.password
          })
            .then(function (result) {
              alert('회원가입이 되었습니다.')
              router.push('/login')
            })
            .catch(function (err) {
              alert(err)
            })
        } else {
          // oh no, user has filled in
          // at least one invalid value
          alert('회원가입이 유효하지 않습니다!')
        }
      })
    }
    // to reset validations:
    function reset () {
      signupForm.value.resetValidation()
    }

    return {
      signupForm,
      validate,
      reset,
      isPwd: ref(true),
      isPwdCheck: ref(true),
      state
    }
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
  width:60%;
  margin:10px auto;
}
#right{
  width:50%;
  height:100%;
  margin:0;
  float:right;
}
#left{
  width:50%;
  height:100%;
  margin:0;
  float:left;
  border-top-left-radius: 15px;
  border-bottom-left-radius: 15px;
  background-image: url('assets/office2.jpg');
  background-size: 900px;
}
#form{
  margin-left: 14%;
}
.btn{
  margin:0 5px;
}
.btnform {
  margin-top: -5px;
  margin-left: -5px;
}
.header {
  position:relative;
  text-align:center;
  background: linear-gradient(60deg, #84A0AD 0%, #F0F7FA 100%);
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
