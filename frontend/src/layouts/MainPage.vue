<template>
  <q-layout view="hHh lpR fFf" class="bg-grey-1">
    <q-header elevated class="bg-white text-grey-8 q-py-xs" height-hint="58">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="toggleLeftDrawer"
          aria-label="Menu"
          icon="menu"
        />
        <!-- 로고 들어가는 부분 -->
        <q-btn flat no-caps no-wrap class="q-ml-xs" v-if="$q.screen.gt.xs">
          <q-img src="~assets/logo.png" style="width:100px; margin-top:-10px;"/>
        </q-btn>

        <q-space />
        <q-space />

        <div class="q-gutter-sm row items-center no-wrap">
          <q-btn round dense flat color="grey-8" icon="apps" v-if="$q.screen.gt.sm">
            <q-tooltip>Apps</q-tooltip>
          </q-btn>
          <q-btn round dense flat color="grey-8" icon="message" v-if="$q.screen.gt.sm">
            <q-tooltip>Messages</q-tooltip>
          </q-btn>
          <q-btn round dense flat color="grey-8" icon="notifications">
            <q-badge color="red" text-color="white" floating>
              2
            </q-badge>
            <q-tooltip>Notifications</q-tooltip>
          </q-btn>
          <q-btn round flat>
            <q-avatar size="26px">
              <img src="~assets/user.png">
            </q-avatar>
            <q-tooltip>Account</q-tooltip>
          </q-btn>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      class="bg-grey-2"
      :width="240"
    >
      <q-scroll-area class="fit">
        <q-list padding>
          <q-item v-for="link in links1" :key="link.text" v-ripple clickable>
            <q-item-section avatar>
              <q-icon color="grey" :name="link.icon" />
            </q-item-section>
            <q-item-section>
              <q-item-label>{{ link.text }}</q-item-label>
            </q-item-section>
          </q-item>

          <q-separator class="q-my-md" />

          <q-item v-for="link in links4" :key="link.text" v-ripple clickable>
            <q-item-section avatar>
              <q-icon color="grey" :name="link.icon" />
            </q-item-section>
            <q-item-section>
              <q-item-label>{{ link.text }}</q-item-label>
            </q-item-section>
          </q-item>

          <q-separator class="q-mt-md q-mb-lg" />

        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <div id="content">
        <div id="side">
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
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { ref } from 'vue'
import { fabYoutube } from '@quasar/extras/fontawesome-v5'

export default {
  name: 'MyLayout',
  setup () {
    const leftDrawerOpen = ref(false)
    const search = ref('')
    function toggleLeftDrawer () {
      leftDrawerOpen.value = !leftDrawerOpen.value
    }
    return {
      fabYoutube,
      leftDrawerOpen,
      search,
      toggleLeftDrawer,
      links1: [
        { icon: 'home', text: 'Home' },
        { icon: 'folder', text: 'Library' },
        { icon: 'feedback', text: 'Notice' },
        { icon: 'restore', text: 'History' },
        { icon: 'watch_later', text: 'Conference' }
      ],
      links4: [
        { icon: 'settings', text: 'Settings' },
        { icon: 'flag', text: 'Report history' },
        { icon: 'help', text: 'Help' }
      ]
    }
  }
}
</script>

<style lang="sass">
.YL
  &__toolbar-input-container
    min-width: 100px
    width: 55%
  &__toolbar-input-btn
    border-radius: 0
    border-style: solid
    border-width: 1px 1px 1px 0
    border-color: rgba(0,0,0,.24)
    max-width: 60px
    width: 100%
  &__drawer-footer-link
    color: inherit
    text-decoration: none
    font-weight: 500
    font-size: .75rem
    &:hover
      color: #000
</style>

<style scooped>
#content{
  width:100%;
  height: 680px;
}
#main{
  width:100%;
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
