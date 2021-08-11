<template>
  <div class="side shadow-1">
    <div align="center">
      <h6 ><span class="highlight-green">파일 업로드</span></h6>
    </div>
    <div class="row">
    <div v-if="form.images" class="margin-auto file-frame"
    @drop.prevent="dropInputTag($event)"
    @dragover.prevent>
    <div style="margin-left: 60px; margin-top:10px;">
      <img :src="form.images" alt="image" class="present-image"><br>
    </div>
    <div align="center">안녕하dasdasdas세요 안녕</div>
    </div>
    <div id="unSelectFileFrame" v-else class="margin-auto file-frame"
      @click="clickInputTag()"
      @drop.prevent="dropInputTag($event)"
      @dragenter="test()"
      @dragleave="dragLeave()"
      @dragover.prevent>
      <div align="center" style="line-height:150px">파일을 끌어서 올려주세요</div>
      <input ref="image" id="input" type="file" name="image" @change="uploadImage()" v-show="false">
    </div>
  </div>
  <div class="row">
    <div class="margin-auto">
      <q-btn class="reset-btn">초기화</q-btn>&nbsp;&nbsp;&nbsp;
      <q-btn @click="regist" class="upload-btn ">파일 업로드</q-btn>
    </div>
  </div>
  <q-separator style="margin-bottom:20px" color="green" inset />
  <div align="center">
    <h6><span class="highlight-yellow">부서별로 모아보기</span></h6>
  </div>
  <div class="department-area">
  </div>
    <div v-for="department in departmentInfo" :key="department.id" style="margin-left: 40px;">
      <div class="row">
        <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 2.5em; color: #18C75E;" name="description"/>
        <span class="department-list">{{ department.departmentName }}</span>
      </div>
    </div>
  </div>
  <div class="detail-search" align="center" @click="detailClick()">
    <q-icon style="font-size: 1.8em; color: #18C75E;" name="north"/><br>
    <span>상세 검색</span>
  </div>
  <div class="sub-side shadow-1" v-if="form.subSdieVisible">
    <div align="right" style="padding-top: 10px;" @click="close()">
      <span style="padding-right:15px; color: red; cursor: pointer;">X</span>
    </div>
    <div align="center">
      <h6 style="margin-top: 15px"><span class="highlight-orange">확장자 별 검색</span></h6>
    </div>
  </div>
</template>

<script>
import { defineComponent, reactive, computed } from 'vue'
// import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'DonwloadsSide',
  components: {
  },
  setup () {
    const router = useRouter()
    const store = useStore()
    const form = reactive({
      image: null,
      tmp: '',
      images: '',
      subSdieVisible: false
    })
    const departmentInfo = computed(() => store.getters['module/getDepartmentInfo'])
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
            loadFileData()
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
    function detailClick () {
      form.subSdieVisible = !form.subSdieVisible
      console.log(form.subSdieVisible)
    }
    function close () {
      form.subSdieVisible = false
      console.log('????')
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
      // const file = new File(['image'], './aaa.jpg')
      form.images = '/media/profile/test-1/test.gif'// URL.createObjectURL(file)
    }
    function loadFileData () {
      store.dispatch('module/loadFileData')
        .then(function (result) {
          console.log(result.data + '잘 받아오는지')
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].imageLocation = mountImageUrl(result.data[i].fileExtension)
          }
          // setFileinfoList
          store.commit('module/setFileinfoList', result.data)
          console.log(store.getters['module/getFileInfoList'], '파일 게터스')
          // console.log(router)
          console.log(router.currentRoute._rawValue.path)
          if (router.currentRoute._rawValue.path === '/downloads') {
            router.push('/downloads2')
          } else {
            router.push('/downloads')
          }
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
      imgLabelClick,
      form,
      regist,
      uploadImage,
      dropInputTag,
      clickInputTag,
      test,
      dragLeave,
      sweetAlert,
      departmentInfo,
      detailClick,
      close
    }
  }
})
</script>

<style scoped>
.row {
  margin-bottom: 20px;
  /* border: 1px solid rgba(86,61,124,.2) */
}
.margin-auto {
  margin-left: auto;
  margin-right: auto;
}
.file-frame {
  width: 220px;
  height: 150px;
  border: 2px dashed gray;
  margin-top:20px;
  border-radius: 5%;
}
.upload-btn {
  background-color: #00BF6F;
}
.reset-btn {
  background-color: wheat;
}
.present-image {
  width: 100px;
  height: 100px;
}
ul{
  list-style:none;
}
.side {
  width: 350px;
  height: 800px;
  position: absolute;
  top: 85px;
  left: 90px;
  border: 1px solid rgb(212, 212, 212);
  border-radius: 10px;
  background-color: white;
}
h6 {
  margin-bottom: 0px;
}
.department-list:hover {
  background-color:rgb(223, 241, 231);
  cursor: pointer;
}
.department-area {
  margin-top: 20px;
}
.highlight-green {
  background: linear-gradient(to top, #36fead 40%, transparent 40%);
}
.highlight-orange {
  background: linear-gradient(to top, orange 40%, transparent 40%);
}
.highlight-yellow {
  background: linear-gradient(to top, khaki 40%, transparent 40%);
}
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
.sub-side {
  width: 350px;
  height: 370px;
  position: absolute;
  top: 480px;
  left: 90px;
  border-radius: 10px;
  background: white;
  border: 1px solid green;
  animation: fadeIn 0.5s ease-in-out;
}
.detail-search {
  position: absolute;
  top: 820px;
  left: 230px;
  cursor: pointer;
}
</style>
