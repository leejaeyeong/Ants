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

export default defineComponent({
  name: 'login',
  methods: {
    mvHome () {
      this.$router.push('/main')
    },
    mvGroup () {
      this.$router.push('/group')
    },
    mvBoard () {
      this.$router.push('/board')
    },
    mvRTC () {
      this.$router.push('/rtc')
    }
  },
  setup () {
    const router = useRouter()
    const logout = function () {
      localStorage.removeItem('token')
      localStorage.removeItem('id')
      localStorage.removeItem('password')
      localStorage.removeItem('name')
      router.push('/')
    }
    return {
      outlinedAllInbox,
      logout
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
#logoutbtn{
    position: fixed;
    top:93%;
    left:2%;
}
.list{
  height:60px;
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
    margin-top:220px;
}
</style>
