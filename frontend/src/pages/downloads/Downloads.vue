<template>
<div>
  <downloads-side></downloads-side>
  <downloads-content></downloads-content>
</div>
</template>

<script>
import { defineComponent } from 'vue'
import { useStore } from 'vuex'
import DownloadsSide from './DownloadsSide.vue'
import DownloadsContent from './DownloadsContent.vue'

export default defineComponent({
  name: 'downloads2',
  components: {
    DownloadsSide,
    DownloadsContent
  },
  setup () {
    const store = useStore()
    const Swal = require('sweetalert2')
    loadFileData()
    function loadFileData () {
      store.dispatch('module/loadFileData')
        .then(function (result) {
          console.log(result.data)
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].imageLocation = mountImageUrl(result.data[i].fileExtension)
          }
          store.commit('module/setFileinfoList', result.data)
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }
    function mountImageUrl (extension) {
      let imageUrl = ''
      switch (extension) {
        case '.ppt':
        case '.pptx':
          imageUrl = 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png'
          break
        case '.docx':
          imageUrl = 'https://i.pinimg.com/474x/ea/83/d6/ea83d672e55bdda2fa44e676eacad9ff.jpg'
          break
        case '.xlsx':
          imageUrl = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFEz9UnOx3StKSJUQs12DuWje3MwDOV6yAfufygK38zgZIuNsizJimqpCRI6ae2gbJuD0&usqp=CAU'
          break
        case '.pdf':
          imageUrl = 'https://blog.kakaocdn.net/dn/cj4Y3U/btqMPi7uAh8/sYik4nsvvqUmG36Hhbwwj1/img.png'
          break
        case '.jpg':
        case '.png':
        case '.JPG':
        case '.PNG':
          imageUrl = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQJ46V8yCbzB4RrjehKqyhVMB-maX5XI-Ysw5gFxdiLRLSX0R-KSFpHMcivZW2xaLUZbs&usqp=CAU'
          break
        default:
          imageUrl = 'https://i.pinimg.com/474x/78/6d/27/786d27105f7e9f02f92c31040169b2de.jpg'
      }
      return imageUrl
    }
    return {
      loadFileData
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
