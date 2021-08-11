<template>
  <div id="board">
      <q-select color="teal" filled v-model="form.type" :options="options" style="margin-top:25px; width:15%; margin-left:30px; float:left;" label="분류" />
      <q-input color="teal" v-model="form.title" style="width:54%; margin-left:15px; margin-top:25px; display:inline-block;" filled label="제목" />
      <q-btn @click="regist" style="margin-left:10px; padding:10px; margin-top:-20px; color:white; background-color: #00BF6F;" label="등록" />
      <q-btn @click="back" style="margin-left:10px; padding:10px; margin-top:-20px; color:white;" color="deep-orange" label="뒤로" />
                  <q-btn @click="imgLabelClick" style="background-color: #00BF6F; margin-left:10px; padding:10px; margin-top:-20px; color:white;">이미지 업로드</q-btn>
                <div>
                  <!-- <label for="image_uploads" style="color: black;" @click="imgLabelClick">Choose images to upload (PNG, JPG)</label> -->
                  <input ref="imageInput" type="file" style="color: black; opacity: 0;" @change="onChangeImages" accept=".jpg, .jpeg, .png" id="profile_img_upload">
                </div>
                <img
                v-if="form.tmp"
                :src="form.tmp"
                style="border-radius: 50%; width: 100px; height: 100px;"
                id="thumb"
                />
      <div class="q-pa-md q-gutter-sm">
        <q-editor v-model="form.content" min-height="32rem" style="margin-left:22px; font-size:30px;"/>
      </div>
  </div>

</template>

<script>
import { ref, defineComponent, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'write',
  setup () {
    const store = useStore()
    const router = useRouter()
    const editor = ref('')
    const Swal = require('sweetalert2')
    const form = reactive({
      type: '',
      title: '',
      content: '',
      image: null,
      tmp: ''
    })
    const options = [
        'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
      ]

    const back = function () {
      router.back()
      console.log(form.title)
      console.log(form.content)
    }
    function regist () {
      const frm = new FormData()
      frm.append('type', 1) //form.type
      frm.append('writer', localStorage.getItem('id'))
      frm.append('title', form.title)
      frm.append('content', form.content)
      frm.append('image', form.image)
      store.dispatch('module/registBoard', frm)
            .then(function (result) {
              Swal.fire({
                title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">게시글이 등록되었습니다.</span>',
                confirmButtonColor: '#19CE60',
                confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
              })
               store.dispatch('module/board', { })
                .then(function (result) {
                  for (let i = 0; i < result.data.length; i++) {
                    rows.push(result.data[i])
                  }
                  store.commit('module/setRows', rows)
                  rows = []
                  const pn = Math.ceil(rows.length / 8)
                  store.commit('module/setPageNumber', pn)
                  router.push('/board')
                })
                .catch(function () {
                  alert('오류발생')
                })
            })
            .catch(function (err) {
              alert(err)
            })
    }
    function onChangeImages (e) {
      console.log(e.target.files)
      console.log(e.target)
      const file = e.target.files[0] // Get first index in files
      this.form.tmp = URL.createObjectURL(file)
      this.form.image = file // Create File URL
    }
    // 기존 파일업로드 버튼 숨기고 q-btn으로 대체
    function imgLabelClick () {
      const inputImg = document.getElementById('profile_img_upload') // input file 태그 저장
      console.log(inputImg)
      inputImg.click() // 클릭이벤트 실행
    }

    return {
      back,
      form,
      editor,
      options,
      imgLabelClick,
      onChangeImages,
      regist
    }
  }
})
</script>

<style scoped>
#board{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:85px;
    left:390px;
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
.btn {

}
</style>
