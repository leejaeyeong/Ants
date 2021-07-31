<template>
  <div id="board">
      <q-input v-model="form.title" style="width:54%; margin-left:30px; margin-top:25px; display:inline-block;" filled label="제목" />
      <q-btn style="margin-left:10px; padding:10px; margin-top:-20px; color:white; background-color:#6581A6;" label="등록" />
      <q-btn @click="back" style="margin-left:10px; padding:10px; margin-top:-20px; color:white;" color="deep-orange" label="뒤로" />
      <!-- <q-input v-model="form.content" style="width:80%; margin-left:30px; margin-top:15px;  font-size:17px;"
      filled
      type="textarea"
      rows="30"
      label="내용"
      /> -->
      <div class="q-pa-md q-gutter-sm">
        <q-editor v-model="editor" min-height="34rem" style="margin-left:22px;"/>
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
      title: '',
      content: ''
    })

    const back = function () {
      router.back()
      console.log(form.title)
      console.log(1111)
      console.log(editor.value)
      console.log(1111)
    }

    return {
      back,
      form,
      editor
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
