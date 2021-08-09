<template>
<div>
  <div class="q-pa-md">
    <div class="row" style="margin-top: 65px;">
      <div class="col-2" style="border-right: 1px solid black; box-shadow: 1px 1px 1px 1px gray; border-radius: 5%;">
        <div class="side">
          <downloads-side></downloads-side>
        </div>
      </div>
      <div class="col-10">
        .col
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { defineComponent, reactive } from 'vue'
// import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import DownloadsSide from './DownloadsSide.vue'

export default defineComponent({
  name: 'downloads2',
  components: {
    DownloadsSide
  },
  setup () {
    const store = useStore()
    const form = reactive({
      image: null,
      tmp: '',
      images: ''
    })
    const Swal = require('sweetalert2')
    function sweetAlert (title) {
      Swal.fire({
        title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">' + title + '</span>',
        confirmButtonColor: '#19CE60',
        confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
      })
    }
    function regist () {
      if (form.image == null) {
        sweetAlert('업로드할 파일을 지정해주세요.')
        return
      }
      const frm = new FormData()
      frm.append('file', form.image)
      store.dispatch('module/uploadFile', frm)
        .then(function (result) {
          console.log(result.data)
          if (result.status === 200) {
            sweetAlert('파일이 정상적으로 업로드 됐습니다.')
            return true
          }
        })
        .catch(function (err) {
          console.log(err)
          sweetAlert('파일 업로드 중 문제가 발생했습니다.')
        })
    }
    // 기존 파일업로드 버튼 숨기고 q-btn으로 대체
    function imgLabelClick () {
      const inputImg = document.getElementById('profile_img_upload') // input file 태그 저장
      console.log(inputImg)
      inputImg.click() // 클릭이벤트 실행
    }
    function uploadImage (file) {
      const xxx = new FormData()
      //   const image = file
      form.images = URL.createObjectURL(file)
      form.image = file
      xxx.append('file', file)
      console.log(xxx)
      console.log('이게 되어야되는데 업로드')
      // store.dispatch('module/uploadFile', xxx)
      //   .then(function (result) {
      //     console.log(result.data)
      //   })
      //   .catch(function (err) {
      //     alert(err)
      //   })
      // 엑시오스
    }
    function test () {
      const frame = document.getElementById('unSelectFileFrame')
      frame.style.borderColor = 'green'
    }
    function dragLeave () {
      const frame = document.getElementById('unSelectFileFrame')
      frame.style.borderColor = 'black'
    }
    function dropInputTag (event) {
      console.log('이게 되어야되는데 드롭다운')
      const file = Array.from(event.dataTransfer.files, v => v)[0]
      uploadImage(file)
    }
    function clickInputTag () {
      console.log('이게 되어야되는데 클릭?')
      // this.$refs.image.click()
      form.images = '/media/profile/test-1/test.gif'// URL.createObjectURL(file)
    }
    return {
      imgLabelClick, form, regist, uploadImage, dropInputTag, clickInputTag, test, dragLeave, sweetAlert
    }
  }
})
</script>

<style scoped>
.row {
  margin-top: 20px;
  margin-bottom: 20px;
  /* border: 1px solid rgba(86,61,124,.2) */
}
.margin-auto {
  margin-left: auto;
  margin-right: auto;
}
.upload-btn {
  background-color: #00BF6F;
}
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateX(-20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
.side {
  animation: fadeIn 0.5s ease-in-out;
}
</style>
