<template>
    <div id="side">
        <div @click="mvHome" class="list clicked">
            <div class="img">
                <q-icon style="font-size: 3.0em; color: white;" name="home"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong style="font-size: 14px;">메인화면</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white;">
                Home
            </div> -->
        </div>
        <div class="list" @click="mvManagement">
          <div class="img">
              <q-icon style="font-size: 3.0em; color: white;" name="event_available"/>
                <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 14px;">근태관리</strong>
                </q-tooltip>
          </div>
          <!-- <div class="detail" style="color: white; ">
              Setting
          </div> -->
        </div>
        <div @click="mvRTC" class="list">
            <div class="img">
                <q-icon style="font-size: 3.0em; color: white;" name="support_agent"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong style="font-size: 14px;">회의룸</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                RTC
            </div> -->
        </div>
        <div @click="mvBoard" class="list">
            <div class="img">
                <q-icon style="font-size: 3.0em; color: white;" name="content_paste"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong style="font-size: 14px;">사내 게시글</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                Board
            </div> -->
        </div>
        <div class="list" @click="mvDownloads">
            <div class="img">
                <q-icon style="font-size: 3.0em; color: white;" name="download"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong style="font-size: 14px;">자료 공유함</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                다운로드
            </div> -->
        </div>

        <div @click="mvGroup" class="list">
            <div class="img">
                <q-icon style="font-size: 3.0em; color: white;" name="groups"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong style="font-size: 14px;">그룹관리</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                Group
            </div> -->
        </div>
        <div @click="logout" class="list" id="logout">
            <div class="img">
                <q-icon style="font-size: 3.0em; color: white;" name="logout"/>
                <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 14px;">로그아웃</strong>
                </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                LogOut
            </div> -->
        </div>
      </div>
</template>

<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'
import { outlinedAllInbox } from '@quasar/extras/material-icons-outlined'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'login',
  methods: {
    mvHome () {
      this.$router.push('/main')
      this.sideMenuClick(1)
    },
    mvRTC () {
      this.$router.push('/rtc')
      this.sideMenuClick(3)
    },
    mvDownloads () {
      this.$router.push('/downloads')
      this.sideMenuClick(5)
    },
    mvManagement () {
      this.$router.push('/management')
      this.sideMenuClick(2)
    }
  },
  setup () {
    const store = useStore()
    const router = useRouter()
    const logout = function () {
      // localStorage.removeItem('token')
      // localStorage.removeItem('id')
      // localStorage.removeItem('password')
      // localStorage.removeItem('name')
      // localStorage.removeItem('userState')
      // localStorage.removeItem('profileLocation')
      // localStorage.removeItem('department')
      // localStorage.removeItem('departmentId')
      localStorage.clear()
      const logoutUser = {
        id: '',
        name: '',
        department: '',
        position: ''
      }
      store.commit('module/setLoginUser', logoutUser)
      router.push('/')
    }
    let rows = []
    let boardList = []
    const mvBoard = function () {
      this.sideMenuClick(4)
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].registrationTime = result.data[i].registrationTime.substr(0, 16)
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          const pn = Math.ceil(rows.length / 8)
          store.commit('module/setPageNumber', pn)
          rows = []
          store.dispatch('module/boardList', { })
            .then(function (res) {
              for (let i = 0; i < res.data.length; i++) {
                boardList.push(res.data[i])
              }
              store.commit('module/setBoardList', boardList)
              boardList = []
              const userId = localStorage.getItem('id')
              store.dispatch('module/markList', userId)
                .then(function (r) {
                  for (let i = 0; i < r.data.length; i++) {
                    r.data[i].registrationTime = r.data[i].registrationTime.substr(0, 16)
                  }
                  store.commit('module/setMarkList', r.data)
                  router.push('/board')
                })
            })
            .catch(function () {
              alert('오류발생')
            })
        })
        .catch(function () {
          alert('오류발생')
        })
    }
    const mvGroup = function () {
      const Swal = require('sweetalert2')
      const userstate = localStorage.getItem('userState')
      console.log(userstate + '유저 스테이트')
      this.sideMenuClick(6)

      if (userstate !== '1') {
        Swal.fire({
          title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">접근 권한이 없습니다.</span>',
          confirmButtonColor: '#ce1919',
          confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
        })
      } else {
        console.log(userstate)
        router.push('/group')
      }
    }
    const clearSideClick = function () {
      const el = document.getElementsByClassName('list')
      for (let i = 0; i < el.length; i++) {
        el[i].classList.remove('clicked')
      }
    }
    const sideMenuClick = function (idx) {
      clearSideClick()
      const sideMenu = document.querySelector(`.list:nth-child(${idx})`)
      sideMenu.classList.add('clicked')
    }
    return {
      outlinedAllInbox,
      logout,
      mvBoard,
      mvGroup,
      sideMenuClick
    }
  }
})
</script>

<style scoped>
#side{
  width:60px;
  height:100%;
  float:left;
  border-right:0.5px solid rgb(212, 212, 212);
  border-bottom:0.5px solid rgb(212, 212, 212);
  background: linear-gradient( to top, #51bb74, green );
  position: absolute;
  top:0px;
}
.list{
  height:70px;
}

.list.selected{
  background-color: rgb(223, 241, 231);
}

.list:hover{
  background-color:rgb(20, 75, 13);
  cursor: pointer;
}
.clicked {
  background-color:rgb(20, 75, 13);
}
.img{
  text-align:center;
  padding-top: 15px;
  /* margin-top:20px; */
}
.detail{
  line-height:33px;
  text-align:center;
  font-size:20px;
}
#logout{
  margin-top:470px;
}
</style>
