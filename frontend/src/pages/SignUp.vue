<template>
  <div class="header">
    <div class="inner-header flex">
      <div id='loginbox'>
        <div id="right1">
          <div id="mark">
            <q-img src="~assets/images/logo.png" style="width:200px; margin-left:140px;" height="150px" />
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
                color="teal"
                v-model="state.form.userId"
                @blur="checkId"
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
                color="teal"
                v-model="state.form.name"
                lazy-rules
                :rules="[
                val => !!val || '필수입력항목 입니다.',
                val => val.length > 1 && val.length <= 16 || '2 ~ 16자까지 입력 가능합니다. '
                ]"
              />
              <q-input class="input" filled :type="isPwd ? 'password' : 'text'"  label="Password *" color="teal" v-model="state.form.password"
              lazy-rules
                :rules="[
                  val => val && val.length > 0 || '필수입력항목 입니다.'
                ]">
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class="cursor-pointer"
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>
              <div class="btnform">
                <q-btn @click="back" flat style="color: #00BF6F; margin-right:10px;" label="← Login"/>
                <!-- <q-btn @click="validate" label="Submit" type="submit" style="background-color: #00BF6F;"/> -->
                <q-btn @click="next" label="Next" style="background-color: #00BF6F;"/>
              </div>
            </q-form>
          </div>
        </div>
        <div id="right2" style="display: none"> <!-- 추가정보 입력-->
          <div id="mark">
            <q-img src="~assets/images/logo.png" style="width:200px; margin-left:140px;" height="150px" />
          </div>
          <div class="q-pa-md" id="form" style="max-width: 400px">
            <div class="input">
                <div>
                  <q-btn @click="imgLabelClick" style="background-color: #00BF6F;">Upload Image</q-btn>
                  <!-- <label for="image_uploads" style="color: black;" @click="imgLabelClick">Choose images to upload (PNG, JPG)</label> -->
                  <input ref="imageInput" type="file" style="color: black; opacity: 0;" @change="onChangeImages" accept=".jpg, .jpeg, .png, .gif" id="profile_img_upload">
                </div>
                <img
                v-if="state.tmp"
                :src="state.tmp"
                style="border-radius: 50%; width: 100px; height: 100px;"
                id="thumb"
                />
              </div>
            <q-form class="q-gutter-md" ref="signupForm" :model="state.form">
              <q-input
                class="input"
                filled
                label="Email *"
                type="email"
                color="teal"
                v-model="state.form.email"
                lazy-rules
                :rules="[
                val => !!val || '필수입력항목 입니다.'
                ]"
              />
              <q-select id="dptId" class="input" filled v-model="state.form.department" :options="options" label="부서 선택" />
              <!-- <q-uploader
                class="input"
                label="Upload profile Image"
                color="green-14"
                bordered
                style="max-width: 300px;"
                v-model="state.form.profileimg"
                accept=".jpg, image/*"
                @rejected="onRejected"
                :headers="[{name: 'Content-Type', value: 'multipart/form-data'}]"
              /> -->
              <div class="btnform">
                <q-btn @click="back2" flat style="color: #00BF6F; margin-right:10px;" label="← Back"/>
                <q-btn @click="validate" label="Submit" type="submit" style="background-color: #00BF6F;"/>
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
import { defineComponent, ref, reactive, computed, watch } from 'vue'
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
      tmp: null,
      form: {
        userId: '',
        name: '',
        password: '',
        email: '',
        image: '',
        department: ''
      }
    })

    const router = useRouter()
    const store = useStore()
    const Swal = require('sweetalert2')
    const department = computed(() => store.getters['module/getDepartmentInfo'])
    const selectOptions = []
    for (let i = 0; i < department.value.length; i++) {
      selectOptions.push(department.value[i].departmentName)
    }
    watch(department, () => {
      console.log('watch 선언')
      console.log(department)
      console.log('watchp 선언')
    })
    console.log('set up 선언')
    console.log(department)
    console.log('set up 선언')
    function checkId () {
      console.log('아이디중복체크')
      store
        .dispatch('module/requestCheckId', state.form.userId)
        .then(function (result) {
          if (result.status === 200) {
            Swal.fire({
              title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">사용할 수 있는 아이디입니다.</span>',
              confirmButtonColor: '#19CE60',
              confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
            })
            return true
          }
        })
        .catch(function (err) {
          if (err.request.status === 409) {
            console.log('아이디 중복오류')
            Swal.fire({
              title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">이미 존재하는 아이디입니다.</span>',
              confirmButtonColor: '#ce1919',
              confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
            })
          } else {
            console.log(err)
          }
        })
    }
    // 회원가입보냄
    function validate () {
      signupForm.value.validate().then(success => {
        if (success) {
          // 회원가입 유효함
          const photoFile = document.getElementById('profile_img_upload')
          const frm = new FormData()
          frm.append('profile', photoFile.files[0])
          frm.append('userId', state.form.userId)
          frm.append('name', state.form.name)
          frm.append('password', state.form.email)
          frm.append('email', state.form.userId)
          let selectedIndex = -1
          for (let i = 0; i < department.value.length; i++) {
            if (selectOptions[i] === state.form.department) {
              selectedIndex = i + 1
              break
            }
          }
          alert(state.form.department)
          alert(selectedIndex)
          frm.append('department', selectedIndex)
          store.dispatch('module/requestSignup', frm)
            .then(function (result) {
              console.log(result)
              Swal.fire({
                title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">회원가입이 완료되었습니다.</span>',
                confirmButtonColor: '#19CE60',
                confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
              })
              router.push('/')
            })
            .catch(function (err) {
              alert(err)
            })
        } else {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">회원가입이 유효하지 않습니다.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        }
      })
    }
    // 사진업로드
    function onClickImageUpload () {
      this.$refs.imageInput.click()
    }
    function onChangeImages (e) {
      console.log(e.target.files)
      console.log(e.target)
      const file = e.target.files[0] // Get first index in files
      this.state.tmp = URL.createObjectURL(file)
      this.state.form.image = file // Create File URL
    }
    // 기존 파일업로드 버튼 숨기고 q-btn으로 대체
    function imgLabelClick () {
      const inputImg = document.getElementById('profile_img_upload') // input file 태그 저장
      console.log(inputImg)
      inputImg.click() // 클릭이벤트 실행
    }

    // 추가정보 입력 보이기
    function next () {
      document.getElementById('right1').style.display = 'none'
      document.getElementById('right2').style.display = 'block'
    }
    function back2 () {
      document.getElementById('right1').style.display = 'block'
      document.getElementById('right2').style.display = 'none'
    }
    return {
      signupForm,
      validate,
      next,
      back2,
      isPwd: ref(true),
      isPwdCheck: ref(true),
      email: ref(''),
      file: ref(null),
      state,
      checkId,
      options: selectOptions,
      onClickImageUpload,
      onChangeImages,
      imgLabelClick
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
#right1{
  width:50%;
  height:100%;
  margin:0;
  float:right;
}
#right2{
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
  background-image: url('https://images.unsplash.com/photo-1616531770192-6eaea74c2456?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjZ8fG9ubGluZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60');
  background-size: 750px;
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
  animation: move-forever 25s cubic-bezier(.55,.5,.45,.5) infinite;
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
</style>
