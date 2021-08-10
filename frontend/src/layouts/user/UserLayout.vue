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
  width: 1500px;
  height: 850px;
  position: absolute;
  top:65px;
  left:200px;
}
table, td, th{
  /* border:3px solid #6581A6; */
  border:1.5px solid rgb(153, 149, 149);
  border-collapse: collapse;
}
.left{
  background-color: #f9f9f9;
  font-weight:bold;
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
