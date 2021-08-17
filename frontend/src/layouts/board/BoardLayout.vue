<template>
  <board-side></board-side>
  <board-content></board-content>
</template>

<script>
import { defineComponent } from 'vue'
import BoardSide from '../../components/boardSide.vue'
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
#allBoard{
  width: 1578px;
  height: 907px;
  position: absolute;
  top:60px;
  left:340px;
  background-color: rgb(242, 247, 244);
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
