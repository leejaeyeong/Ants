<template>
  <div class="side">
    <div align="center">
      <h6 ><span class="highlight-orange">파일 업로드</span></h6>
    </div>
    <div class="row">
      <!-- 파일 지정된 상태 -->
    <div v-if="form.images" class="margin-auto file-mounted"
    @drop.prevent="dropInputTag($event)"
    @dragover.prevent>
    <div style="margin-left: 60px; margin-top:10px;">
      <img :src="form.images" alt="image" class="present-image"><br>
    </div>
    <div align="center">{{form.image.name}}</div>
    <div align="center">{{ fileSizeFilter }}</div>
    <!-- {{form.image.name}} -->
    </div>
    <!-- 파일 미지정 상태 -->
    <div id="unSelectFileFrame" v-else class="margin-auto file-frame"
      @click="clickInputTag()"
      @drop.prevent="dropInputTag($event)"
      @dragenter="dragEnter()"
      @dragleave="dragLeave()"
      @dragover.prevent>
      <div align="center" style="line-height:150px">파일을 끌어서 올려주세요</div>
      <!-- <input ref="image" id="input" type="file" name="image" @change="presentImage()" v-show="false"> -->
    </div>
  </div>
  <div class="row">
    <div class="margin-auto">
      <q-btn @click="reset" class="reset-btn">초기화</q-btn>&nbsp;&nbsp;&nbsp;
      <q-btn @click="regist" class="upload-btn ">파일 업로드</q-btn>
    </div>
  </div>
  <q-separator style="margin-bottom:10px" color="green" inset />
  <div align="center">
    <h6><span class="highlight-yellow">부서별로 모아보기</span></h6>
  </div>
  <div class="department-area">
  </div>
    <div v-for="department in departmentInfo" :key="department.id" style="margin-left: 40px;">
      <div class="row">
        <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 2.5em; color: #249752;" name="description"/>
        <span @click="searchByDepartment(department.id)" class="department-list">{{ department.departmentName }}</span>
      </div>
    </div>
  </div>
  <!-- <div class="detail-search" align="center" @click="detailClick()">
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
  </div> -->
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
  computed: {
    fileSizeFilter () {
      const size = this.form.image.size
      let convertedFileSize = ''
      if (size / 1000000 >= 1) {
        convertedFileSize = (size / 1000000).toFixed(2) + 'MB'
      } else if (size / 1000 >= 1) {
        convertedFileSize = (size / 1000).toFixed(2) + 'KB'
      } else {
        convertedFileSize = size + 'B'
      }
      return convertedFileSize
    }
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
    const fileImage = computed(() => store.getters['module/getFileImage']).value
    const departmentInfo = computed(() => store.getters['module/getDepartmentInfo'])
    const Swal = require('sweetalert2')
    function sweetAlert (title) {
      Swal.fire({
        title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">' + title + '</span>',
        confirmButtonColor: '#19CE60',
        confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
      })
    }
    function reset () {
      form.image = null
      form.images = null
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
    function presentImage (file) {
      console.log('presentImage start')
      form.images = URL.createObjectURL(file)
      console.log('presentImage start')
    }
    function detailClick () {
      form.subSdieVisible = !form.subSdieVisible
      console.log(form.subSdieVisible)
    }
    function close () {
      form.subSdieVisible = false
    }
    function dragEnter () {
      const frame = document.getElementById('unSelectFileFrame')
      // frame.style.borderColor = 'green'
      // file-mounted
      // frame.className = 'file-mounted'
      frame.classList.add('file-mounted', 'margin-auto')
    }
    function dragLeave () {
      const frame = document.getElementById('unSelectFileFrame')
      frame.classList.remove('file-mounted')
      frame.classList.add('file-frame')
      // frame.style.borderColor = 'gray'
    }
    function dropInputTag (event) {
      console.log('영역안에 파일이 지정됨')
      const file = Array.from(event.dataTransfer.files, v => v)[0]
      // 파일이 jpg, png가 아니면 다른 파일 객체에
      const extension = file.name.split('.')[1] // jpg, zip 등

      const imageExtension = ['jpg', 'JPG', 'jpeg', 'JPEG', 'png', 'PNG', 'gif', 'GIF']
      if (imageExtension.includes(extension)) {
        form.images = URL.createObjectURL(file)
      } else {
        form.images = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
      }
      form.image = file
      // presentImage(file)
      console.log('영역안에 파일이 지정됨 end')
    }
    function clickInputTag () {
      console.log('영역 안을 클릭함')
      console.log('영역 안을 클릭함 end')
    }
    function loadFileData () {
      store.dispatch('module/loadFileData')
        .then(function (result) {
          console.log(result.data + '잘 받아오는지')
          for (let i = 0; i < result.data.length; i++) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
          }
          // setFileinfoList
          store.commit('module/setFileinfoList', result.data)
          pageChange()
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }
    function pageChange () {
      console.log(router.currentRoute._rawValue.path)
      if (router.currentRoute._rawValue.path === '/downloads') {
        router.push('/downloads2')
      } else {
        router.push('/downloads')
      }
    }
    function searchByDepartment (id) {
      store.dispatch('module/loadFileDataByDepartment', id)
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
          }
          store.commit('module/setFileinfoList', result.data)
          pageChange()
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }
    return {
      form,
      regist,
      presentImage,
      dropInputTag,
      clickInputTag,
      dragEnter,
      dragLeave,
      sweetAlert,
      departmentInfo,
      detailClick,
      close,
      reset,
      searchByDepartment,
      pageChange
    }
  }
})
</script>

<style scoped>
.side {
  width: 350px;
  height: 907px;
  position: absolute;
  top: 60px;
  left: 60px;
  background-color: white;
}
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
  height: 160px;
  border: 2px dashed gray;
  margin-top:20px;
  border-radius: 5%;
}
.upload-btn {
  background-color: #249752;
}
.reset-btn {
  background-color: rgb(219, 130, 14);
}
.present-image {
  width: 100px;
  height: 100px;
}
ul{
  list-style:none;
}
h6 {
  margin-bottom: 0px;
}
.department-list{
  margin-top: 10px;
  margin-left: 10px;
}
.department-list:hover {
  background-color:rgb(223, 241, 231);
  cursor: pointer;
}
.department-area {
  margin-top: 20px;
}
.highlight-green {
  background: linear-gradient(to top, #249752 35%, transparent 40%);
}
.highlight-orange {
  background: linear-gradient(to top, rgb(219, 130, 14) 35%, transparent 40%);
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
.file-mounted {
  width: 220px;
  height: 160px;
  border: 2px dashed transparent;
  margin-top:20px;
  border-radius: 5%;
  animation: changeColor 1.8s infinite linear
}
@keyframes changeColor {
  0% {
    border-color: rgb(255, 10, 10);
  }
  20% {
    border-color: rgb(236, 145, 7);
  }
  40% {
    border-color: rgb(225, 238, 37);
  }
  60% {
    border-color: rgb(20, 155, 49);
  }
  80% {
    border-color: rgb(32, 67, 143);
  }
  100% {
    border-color: rgb(22, 17, 97);
  }
}
</style>
