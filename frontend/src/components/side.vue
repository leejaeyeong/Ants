<template>
    <div id="side">
        <div @click="mvHome" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="home"/>
            </div>
            <div class="detail" style="color: #18C75E; font-weight:bold;">
                Home
            </div>
        </div>
        <div @click="mvBoard" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="content_paste"/>
            </div>
            <div class="detail" style="color: #18C75E; font-weight:bold;">
                Board
            </div>
        </div>
        <div @click="mvGroup" class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="people_alt"/>
            </div>
            <div class="detail" style="color: #18C75E; font-weight:bold;">
                Group
            </div>
        </div>
        <div class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="support_agent"/>
            </div>
            <div class="detail" style="color: #18C75E; font-weight:bold;">
                RTC
            </div>
        </div>
        <div class="list">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="settings"/>
            </div>
            <div class="detail" style="color: #18C75E; font-weight:bold;">
                Setting
            </div>
        </div>
        <div @click="logout" class="list" id="logout">
            <div class="img">
                <q-icon style="font-size: 2.8em; color: #18C75E;" name="logout"/>
            </div>
            <div class="detail" style="color: #18C75E; font-weight:bold;">
                LogOut
            </div>
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
      logout,
      links1: [
        { icon: 'home', text: '홈', name: 'main' },
        { icon: 'volume_up', text: '공지사항', name: 'notice' },
        { icon: 'content_paste', text: '게시판', name: 'board' },
        { icon: 'people_alt', text: '화상회의', name: 'rtc' },
        { icon: 'settings', text: '설정', name: 'set' }
      ]
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
    height:80px;
    border:1px solid white;
}
.list:hover{
    background-color:gainsboro;
    cursor: pointer;
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
