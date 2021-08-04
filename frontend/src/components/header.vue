<template>
    <div id="header">
        <div id="headerLeft">
          <q-img src="~assets/images/logo.png" style="width:150px; margin-top:-43px;"/>
          <!-- <img :src="state.profileLocation">
          <img src="/media/profile/qqq/tree.jpg"> -->
          <h2>21</h2><br>
          <h2>21</h2><br>
          <h2>21</h2><br>
        </div>
        <div id="headerRight">
            <img @click="mvMypage" src="~assets/images/user.png" style="width:40px; margin-top:10px; margin-right:65px; float:right;" />
              <q-btn-dropdown class="nav-item"
                    title="알림"
                    flat
                    no-icon-animation
                    dropdown-icon="notifications"
                    style="font-size: 1.5em; width:20px; margin-top:2px; margin-right:5px; float:right; color:#18C75E;"
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
            <span style="font-weight:bold; float:right; margin-right:8px; line-height:60px; font-size:20px;">{{ state.name }} 님</span>
            <span>sss</span>
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
      this.$router.push('/mylayout')
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
</style>
