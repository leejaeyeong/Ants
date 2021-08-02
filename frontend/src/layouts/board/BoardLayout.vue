<template>
  <board-side></board-side>
  <board-content></board-content>
</template>

<script>
import { defineComponent } from 'vue'
import BoardSide from '../../components/BoardSide.vue'
import BoardContent from './BoardContent.vue'

export default defineComponent({
  name: 'userlayout',
  components: {
    BoardSide,
    BoardContent
  }
})
</script>

<style scoped>
#myData{
  width: 1130px;
  height: 690px;
  position: absolute;
  top:60px;
  left:325px;
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
