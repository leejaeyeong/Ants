<template>
  <div id="myData">
      <div style="margin-top:25px; margin-left:30px; font-weight:bold; font-size:25px;">회원 정보 수정페이지</div>
      <table width="800" height="350" style="text-align:center; margin-left:30px; margin-top:15px;">
        <tr>
          <td colspan="2" rowspan="4" width="35">
            <div>
              <!-- <img
              v-if="state.tmp"
              :src="state.tmp"
              style="width: 250px; height: 300px;"
              id="thumb"
              /> -->
              <img :src="userInfo.profileLocation" style="width: 250px; height: 300px;" />
              <q-btn @click="imgLabelClick" style="background-color: #00BF6F;">사진 변경</q-btn>
              <input ref="imageInput" type="file" style="color: black; opacity: 0;" @change="onChangeImages" accept=".jpg, .jpeg, .png, .gif" id="profile_img_upload">
            </div>
            <!-- <q-btn style="width:125px; color: white; background-color: #18C75E;" icon="photo_camera" label="사진변경" /><br> -->
          </td>
          <td width="100" class="left">성명</td>
          <td width="200">{{ userInfo.name }}</td>
          <td width="100" class="left">ID</td>
          <td width="200">{{ userInfo.userId }}</td>
        </tr>
        <tr>
          <td class="left">이메일</td>
          <td>{{ userInfo.email }}</td>
          <td class="left">휴가</td>
          <td>{{ userInfo.holiday }}</td>
        </tr>
        <tr>
          <td class="left">부서</td>
          <td>{{ userInfo.department }}</td>
          <td class="left">직책</td>
          <td>{{ userInfo.position }}</td>
        </tr>
        <tr>

        </tr>
      </table>
      <q-btn @click="myEdit" style="margin-left:735px; margin-top:10px; width:95px; color: white; background-color: #18C75E;" icon="build" label="수정하기" />
  </div>
</template>

<script>
import { defineComponent, computed, reactive } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'mypageedit',
  setup () {
    // 개인정보
    const store = useStore()
    const userInfo = computed(() => store.getters['module/getUserInfo'])
    const state = reactive({
      tmp: null,
      form: {

      }
    })
    // 사진업로드
    function onClickImageUpload () {
      this.$refs.imageInput.click()
    }
    function onChangeImages (e) {
      console.log(e.target.files)
      console.log(e.target)
      const file = e.target.files[0] // Get first index in files
      this.userInfo.profileLocation = URL.createObjectURL(file)
      this.state.form.image = file // Create File URL
    }
    // 기존 파일업로드 버튼 숨기고 q-btn으로 대체
    function imgLabelClick () {
      const inputImg = document.getElementById('profile_img_upload') // input file 태그 저장
      console.log(inputImg)
      inputImg.click() // 클릭이벤트 실행
    }
    // 수정하기
    function myEdit () {
      store.dispatch('module/editInfo')
        .then(function (res) {
          console.log(res)
          alert('회원정보 수정완료')
        })
        .catch(function (err) {
          console.log(err)
        })
    }

    return {
      userInfo,
      state,
      onClickImageUpload,
      onChangeImages,
      imgLabelClick,
      myEdit
    }
  }
})
</script>

<style scoped>
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
