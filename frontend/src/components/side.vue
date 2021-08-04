<template>
    <div id="side">
        <div @click="mvHome" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="home"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>HOME</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: #18C75E; font-weight:bold;">
                Home
            </div> -->
        </div>
        <div @click="mvBoard" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="content_paste"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>Board</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: #18C75E; font-weight:bold;">
                Board
            </div> -->
        </div>
        <div @click="mvGroup" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="people_alt"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>Group</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: #18C75E; font-weight:bold;">
                Group
            </div> -->
        </div>
        <div @click="mvRTC" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="support_agent"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>RTC</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: #18C75E; font-weight:bold;">
                RTC
            </div> -->
        </div>
        <div class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="settings"/>
                  <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                    <strong>Settings</strong>
                  </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: #18C75E; font-weight:bold;">
                Setting
            </div> -->
        </div>
        <div @click="logout" class="list" id="logout">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="logout"/>
                <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong>Logout</strong>
                </q-tooltip>
            </div>
            <!-- <div class="detail" style="color: #18C75E; font-weight:bold;">
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
    var rows = []
    var boardList = []
    const mvBoard = function () {
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          var pn = Math.ceil(rows.length / 8)
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
  width:75px;
  height:692px;
  float:left;
  border-right:0.5px solid rgb(212, 212, 212);
  border-bottom:0.5px solid rgb(212, 212, 212);
}
.list{
  height:63px;
  border:1px solid white;
}

.list.selected{
  background-color: rgb(223, 241, 231);
}

.list:hover{
  background-color:rgb(223, 241, 231);
  cursor: pointer;
  border-radius: 10%;
}
.img{
  text-align:center;
  margin-top:5px;
}
.detail{
  line-height:33px;
  text-align:center;
  font-size:14px;
}
#logout{
  margin-top:320px;
}
</style>
