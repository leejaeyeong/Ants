<template>
    <div id="side">
        <div @click="mvHome" class="list">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="home"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>HOME</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white;">
                Home
            </div> -->
        </div>
        <div @click="mvBoard" class="list">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="content_paste"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>Board</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                Board
            </div> -->
        </div>
        <div @click="mvGroup" class="list">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="people_alt"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>Group</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                Group
            </div> -->
        </div>
        <div @click="mvRTC" class="list">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="support_agent"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>RTC</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                RTC
            </div> -->
        </div>
        <div class="list"  @click="mvDownloads">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="download"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>다운로드</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                다운로드
            </div> -->
        </div>
        <div class="list">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="settings"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>Settings</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: white; ">
                Setting
            </div> -->
        </div>
        <div @click="logout" class="list" id="logout">
            <div class="img">
                <q-icon style="font-size: 3.5em; color: white;" name="logout"/>
                <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong>Logout</strong>
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
    },
    mvGroup () {
      this.$router.push('/group')
    },
    mvRTC () {
      this.$router.push('/rtc')
    },
    mvDownloads () {
      this.$router.push('/downloads')
    }
  },
  setup () {
    const store = useStore()
    const router = useRouter()
    const logout = function () {
      localStorage.removeItem('token')
      localStorage.removeItem('id')
      localStorage.removeItem('password')
      localStorage.removeItem('name')
      router.push('/')
    }
    let rows = []
    let boardList = []
    const mvBoard = function () {
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          const pn = Math.ceil(rows.length / 8)
          store.commit('module/setPageNumber', pn)
          rows = []
          store.dispatch('module/boardList', { })
            .then(function (result) {
              for (let i = 0; i < result.data.length; i++) {
                boardList.push(result.data[i])
              }
              store.commit('module/setBoardList', boardList)
              boardList = []
              router.push('/board')
            })
            .catch(function () {
              alert('오류발생')
            })
        })
        .catch(function () {
          alert('오류발생')
        })
    }
    return {
      outlinedAllInbox,
      logout,
      mvBoard
    }
  }
})
</script>

<style scoped>
#side{
  width:80px;
  height:100%;
  float:left;
  border-right:0.5px solid rgb(212, 212, 212);
  border-bottom:0.5px solid rgb(212, 212, 212);
  background: linear-gradient( to top, #90e5ab, green );
}
.list{
  height:70px;
}

.list.selected{
  background-color: rgb(223, 241, 231);
}

.list:hover{
  background-color:rgb(11, 63, 4);
  cursor: pointer;
}
.img{
  text-align:center;
  padding-top: 8px;
  margin-top:20px;
}
.detail{
  line-height:33px;
  text-align:center;
  font-size:20px;
}
#logout{
  margin-top:359px;
}
</style>
