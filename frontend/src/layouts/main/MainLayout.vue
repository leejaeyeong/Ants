<template>
  <Side></Side>
  <top-navbar></top-navbar>
  <main-content></main-content>
  <chat></chat>
</template>

<script>
import { defineComponent } from 'vue'
import TopNavbar from './TopNavbar.vue'
import Side from '../../components/side.vue'
import MainContent from './Content.vue'
import Chat from '../../components/chat.vue'

export default defineComponent({
  name: 'login',
  components: {
    TopNavbar,
    Side,
    MainContent,
    Chat
  }
})
</script>

<style scoped>
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
div{
  font-family: 'NEXON Lv1 Gothic OTF';
}
span{
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
