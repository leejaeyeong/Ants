<template>
    <div id="header">
        <div id="headerLeft">
            <q-img src="~assets/logo.png" style="width:150px; margin-top:-43px;"/>
        </div>
        <div id="headerRight">
            <q-img src="~assets/user.png" style="width:40px; margin-top:10px; margin-right:35px; float:right;"/>
            <q-img src="~assets/bell.png" style="width:30px; margin-top:15px; margin-right:5px; float:right;"/>
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
        </div>
        <div id="main">
        공지사항
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import { fabYoutube } from '@quasar/extras/fontawesome-v5'

export default defineComponent({
  name: 'login',
  methods: {
    menuSelect (link) {
      this.$router.push('/' + link.name)
    }
  },
  setup () {
    return {
      fabYoutube,
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
    width:16%;
    height:60px;
    float:left;
    text-align: center;
    font-size:35px;
    border-right:0.5px solid rgb(212, 212, 212);
}
#headerRight{
    width:84%;
    height:60px;
    float:right;
}
#content{
    width:100%;
    height: 680px;
}
#side{
    width:16%;
    height:692px;
    float:left;
    border-right:0.5px solid rgb(212, 212, 212);
    border-bottom:0.5px solid rgb(212, 212, 212);
}
#main{
    width:84%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:whitesmoke;
}
</style>
