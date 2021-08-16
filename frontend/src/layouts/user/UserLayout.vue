<template>
  <user-content></user-content>
</template>

<script>
import { defineComponent } from 'vue'
import UserContent from './UserContent.vue'

export default defineComponent({
  name: 'userlayout',
  components: {
    UserContent
  }
})
</script>

<style scoped>
#myData{
  width:1858px;
  height:908px;
  float:right;
  position: absolute;
  top: 60px;
  left: 60px;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: rgb(242, 247, 244);
}

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
