<template>
  <div id="header">
    <div id="headerLeft">
      <q-img src="~assets/images/logo.png" style="width:180px; margin-top:-53px;"/>
    </div>
    <div id="headerRight">
      <img :src="state.profileLocation" style="border-radius: 50%; width:50px; margin-top:10px; margin-right:140px; float:right;">
      <q-btn-dropdown class="nav-item"
            title="알림"
            flat
            no-icon-animation
            dropdown-icon="notifications"
            style="font-size: 2em; width:20px; margin-top:2px; margin-right:5px; float:right; color:#18C75E;"
            size="md" >
        <q-list>
          <q-item clickable v-close-popup @click="onItemClick">
            <q-item-section avatar>
              <q-avatar icon="message" color="primary" text-color="white" />
            </q-item-section>
            <q-item-section>
              <q-item-label>범진님의 메세지</q-item-label>
              <q-item-label caption>Juiy 25, 2021</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="info" color="amber" />
            </q-item-section>
          </q-item>
          <q-item clickable v-close-popup @click="onItemClick">
            <q-item-section avatar>
              <q-avatar icon="message" color="primary" text-color="white" />
            </q-item-section>
            <q-item-section>
              <q-item-label>재영님의 메세지</q-item-label>
              <q-item-label caption>Juiy 24, 2021</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-icon name="info" color="amber" />
            </q-item-section>
          </q-item>
        </q-list>
      </q-btn-dropdown>
      <div class="dropdown">
        <q-btn class="dropbtn" flat>{{ state.name }} 님 &#9660;</q-btn>
        <div class="dropdown-content">
          <router-link :to="{ path: 'management' }">근태관리</router-link>
          <router-link :to="{ path: 'mypage' }">개인정보 수정</router-link>
          <a href="" @click="logout">로그아웃</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  methods: {
    mvManagement () {
      this.$router.push('/management')
    },
    mvMypage () {
      this.$router.push('/mypage')
    }
  },
  setup () {
    const state = reactive({
      name: localStorage.getItem('name'),
      profileLocation: localStorage.getItem('profileLocation')
    })
    const router = useRouter()
    const logout = function () {
      localStorage.removeItem('token')
      localStorage.removeItem('id')
      localStorage.removeItem('password')
      localStorage.removeItem('name')
      router.push('/')
    }
    return {
      state,
      onItemClick () {
        console.log('아이템 클릭')
      },
      logout
    }
  }
})
</script>

<style scoped>
#header{
  width:90%;
  height:75px;
  border-bottom:1px solid rgb(212, 212, 212);
  position: absolute;
  top: 0px;
  left:100px;
}
#headerLeft{
  width:100%;
  height:75px;
  font-size:35px;
  position: absolute;
  /* border-right:0.5px solid rgb(212, 212, 212); */
}
#headerRight{
  width:88%;
  height:75px;
  float:right;
}
.dropdown {
  float:right;
  margin-right:8px;
  line-height:50px;
  margin-top: 15px;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 120px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 10px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>
