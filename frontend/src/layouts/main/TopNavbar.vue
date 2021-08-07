<template>
    <div id="header">
      <div id="headerLeft">
        <q-img src="~assets/images/ants-log.png" style="margin-top: 10px; width:120px; height:70px;"/>
      </div>
      <div id="headerRight">
        <img @click="mvMypage" :src="state.profileLocation" style="border-radius: 50%; width:50px; margin-top:10px; margin-right:140px; float:right; position:absolute; left: 1500px">
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
            <div>
              <span style="font-weight:bold; float:right; margin-right:8px; line-height:75px; font-size:30px;">{{ state.name }} 님</span>
            </div>
      </div>
    </div>
</template>

<script>
import { reactive, defineComponent } from 'vue'

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
    return {
      state,
      onItemClick () {
        console.log('아이템 클릭')
      }
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
</style>
