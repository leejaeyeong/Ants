<template>
  <div id="board">
    <div id="detail">
      <q-input v-model="detail.title" style="width:30%; font-size:20px; color:black;" filled readonly/>
      <div class="q-pa-md" style="max-width: 800px; margin-left:-17px;">
        <q-input
          filled
          type="textarea"
          readonly
          v-model="detail.content"
          rows="13"
          style="font-size:20px;"
        />
      </div>
      <q-input style="font-size:20px; width:50%; display:inline-block;" label="댓글입력란" filled/>
      <q-btn style="color: white; background-color: #18C75E; font-size:20px; margin-top:-20px; margin-left:10px; padding:10px;" label="등록" />
      <q-btn @click="back" style="font-size:20px; margin-top:-20px; margin-left:10px; padding:10px;" color="amber" glossy label="뒤로" />
      <div class="comment"></div>
    </div>
  </div>
</template>

<script>
import { reactive, ref, computed, defineComponent } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'board',
  methods: { },
  setup () {
    const store = useStore()
    const router = useRouter()
    const detail = computed(() => store.getters['module/getDetail'])
    
    const back = function () {
      router.back()
    }
    
    return {
      detail,
      back
    }
  }
})
</script>

<style scoped>
#board{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:75px;
    left:390px;
}
#detail{
    margin-top:22px;
    margin-left:40px;
}
.comment{
    border:1px solid green;
    width:610px;
    height:50px;
    margin-top:10px;
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
