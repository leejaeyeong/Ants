<template>
  <div id="board">
    
  </div>

</template>

<script>
import { reactive, ref, computed, defineComponent } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'board',
  methods: { }
})
</script>

<style scoped>

#board{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:60px;
    left:325px;
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
