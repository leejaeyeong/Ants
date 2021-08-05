<template>
  <div id="board">
      <q-select color="teal" filled :options="options" style="margin-top:20px; width:15%; margin-left:40px; float:left;" label="분류" />
      <q-input color="teal" v-model="form.title" style="width:54%; margin-left:15px; margin-top:20px; display:inline-block;" filled label="제목" />
      <q-btn style="margin-left:10px; padding:10px; margin-top:-20px; color:white; background-color: #00BF6F;" label="등록" />
      <q-btn @click="back" style="margin-left:10px; padding:10px; margin-top:-20px; color:white;" color="deep-orange" label="뒤로" />
      <div class="q-pa-md q-gutter-sm">
        <q-editor v-model="form.content" min-height="32rem" style="margin-left:22px; font-size:30px;"/>
      </div>
  </div>

</template>

<script>
import { ref, defineComponent, reactive } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'write',
  setup () {
    const router = useRouter()
    const editor = ref('')
    const form = reactive({
      type: '',
      title: '',
      content: ''
    })
    const options = [
        'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
      ]

    const back = function () {
      router.back()
      console.log(form.title)
      console.log(form.content)
    }

    return {
      back,
      form,
      editor,
      options
    }
  }
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
