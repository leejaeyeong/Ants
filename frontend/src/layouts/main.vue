<template>
    <div id="header">
        <div id="headerLeft">
            <q-img src="~assets/logo.png" style="width:150px; margin-top:-43px;"/>
        </div>
        <div id="headerRight">
            <q-img src="~assets/user.png" style="width:40px; margin-top:10px; margin-right:45px; float:right;"/>
            <q-img src="~assets/bell.png" style="width:30px; margin-top:15px; margin-right:5px; float:right;"/>
            <span style="font-weight:bold; float:right; margin-right:8px; line-height:60px; font-size:15px;">{{ state.id }} 님</span>
        </div>
    </div>
    <div id="content">
        <div id="side">
            <q-item  @click="menuSelect(link)" v-for="link in links1" :key="link.text" v-ripple clickable>
                <q-item-section avatar>
                    <q-icon color="grey" :name="link.icon" />
                </q-item-section>
                <q-item-section>
                    <q-item-label>{{ link.text }}</q-item-label>
                </q-item-section>
            </q-item>
            <q-btn @click="logout" id="logoutbtn" align="between" class="btn-fixed-width" color="accent" label="로그아웃" icon="flight_takeoff" />
        </div>
        <div id="main">
            <div id="topLeft">
            </div>
            <div id="topRight">
            </div>
            <div id="botLeft">
            </div>
            <div id="botRight">
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, defineComponent } from 'vue'
import { fabYoutube } from '@quasar/extras/fontawesome-v5'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'login',
  methods: {
    menuSelect (link) {
      this.$router.push('/' + link.name)
    }
  },
  setup () {
    const state = reactive({
      id: localStorage.getItem('id')
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
      fabYoutube,
      logout,
      links1: [
        { icon: 'home', text: '홈', name: 'main' },
        { icon: 'flag', text: '공지사항', name: 'notice' },
        { icon: 'feedback', text: '익명게시판', name: 'board' },
        { icon: 'subscriptions', text: '회의', name: 'rtc' },
        { icon: 'settings', text: '설정', name: 'setting' }
      ]
    }
  }
})
</script>

<style scoped>
#header{
    width:100%;
    height:60px;
    border-bottom:0.5px solid rgb(212, 212, 212);
}
#headerLeft{
    width:12%;
    height:60px;
    float:left;
    text-align: center;
    font-size:35px;
    /* border-right:0.5px solid rgb(212, 212, 212); */
}
#headerRight{
    width:88%;
    height:60px;
    float:right;
}
#content{
    width:100%;
    height: 680px;
}
#side{
    width:12%;
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
#main{
    width:88%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:whitesmoke;
}
#topLeft{
    width:35%;
    height:200px;
    border: 1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:30px;
    left:50px;
    background-color:rgb(250, 250, 110);
}
#topRight{
    width:54%;
    height:200px;
    border: 1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:30px;
    left:100px;
}
#botLeft{
    width:45%;
    height:400px;
    border:1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:60px;
    left:50px;
}
#botRight{
    width:45%;
    height:400px;
    border:1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:60px;
    left:90px;
}
</style>
