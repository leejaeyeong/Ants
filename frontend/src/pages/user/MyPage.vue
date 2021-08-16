  <template>
  <div id="myData">
    <div style="margin-top:25px; margin-left:200px; font-weight:bold; font-size:25px;">
      <p>개인 정보 수정</p>
    </div>
    <div class="all shadow-2">
      <table class="mypage">
        <tr>
          <td class="left">이미지</td>
          <td>
            <div>
              <img :src="userInfo.profileLocation" style="margin-top: 50px; width: 220px; height: 270px; border-radius: 10px;" />
              <q-btn @click="imgLabelClick" class="imgbtn" >이미지 변경</q-btn>
              <input ref="imageInput" type="file" style="display: none; opacity: 0;" @change="onChangeImages" accept=".jpg, .jpeg, .png, .gif" id="profile_img_upload">
            </div>
          </td>
        </tr>
        <tr>
          <td class="left">이름</td>
          <td>{{ userInfo.name }}</td>
        </tr>
        <tr>
          <td class="left">아이디</td>
          <td>{{ userInfo.userId }}</td>
        </tr>
        <tr>
          <td class="left">이메일</td>
          <td><q-input color="teal" square outlined v-model="userInfo.email" style="width:400px;"/></td>
        </tr>
        <tr>
          <td class="left">휴가</td>
          <td>{{ userInfo.holiday }}</td>
        </tr>
        <tr>
          <td class="left">부서</td>
          <td>{{ userInfo.department }}</td>
        </tr>
        <tr>
          <td class="left">직책</td>
          <td>{{ userInfo.position }}</td>
        </tr>
      </table>
      <div class="submitbtn">
        <div class="row"></div>
        <div class="row">
          <div class="col-2"></div>
          <div class="col-3">
            <div @click="remove" class="btn">
          <q-icon class="icon" name="delete"/>
          <span>회원 탈퇴</span>
        </div>
          </div>
          <div class="col-4">
            <div @click="myEdit" class="btn">
          <q-icon class="icon" name="build"/>
          <span>정보 수정</span>
        </div>
          </div>
          <div class="col-2"></div>
        </div>
        <!-- <div @click="remove" class="btn">
          <q-icon class="icon" name="delete"/>
          <span>회원 탈퇴</span>
        </div>
        <div @click="myEdit" class="btn">
          <q-icon class="icon" name="build"/>
          <span>정보 수정</span>
        </div> -->
      </div>
    </div>

  </div>
</template>

<script>
import { defineComponent, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'mypage',
  setup () {
    const router = useRouter()
    const userInfo = computed(() => store.getters['module/getUserInfo'])
    const store = useStore()
    const Swal = require('sweetalert2')
    store.dispatch('module/requestInfo')
      .then(function (result) {
        console.log(result.data)
        const userInfo = {
          userId: result.data.userId,
          name: result.data.name,
          department: result.data.department,
          profileLocation: result.data.profileLocation,
          email: result.data.email,
          holiday: result.data.holiday,
          position: result.data.position
        }
        console.log(userInfo)
        store.commit('module/setUserInfo', userInfo)
      })
      .catch(function (err) {
        console.log(err)
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
      this.userInfo.image = file // Create File URL
    }
    // 기존 파일업로드 버튼 숨기고 q-btn으로 대체
    function imgLabelClick () {
      const inputImg = document.getElementById('profile_img_upload') // input file 태그 저장
      console.log(inputImg)
      inputImg.click() // 클릭이벤트 실행
    }
    // 수정하기
    function myEdit () {
      const frm = new FormData()
      frm.append('email', userInfo.value.email)
      frm.append('profile', userInfo.value.image)
      if (userInfo.value.image === undefined) {
        Swal.fire({
          title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">프로필 사진을 변경해주세요.</span>',
          confirmButtonColor: '#18C75E',
          confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
        })
        return
      }
      store.dispatch('module/editInfo', frm)
        .then(function (res) {
          console.log(res)
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">회원정보가 성공적으로 수정되었습니다.</span>',
            confirmButtonColor: '#18C75E',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
            .then(function () {
              store.dispatch('module/requestInfo')
                .then(function (result) {
                  console.log(result.data)
                  const userInfo = {
                    userId: result.data.userId,
                    name: result.data.name,
                    department: result.data.department,
                    profileLocation: result.data.profileLocation,
                    email: result.data.email,
                    holiday: result.data.holiday,
                    position: result.data.position
                  }
                  console.log(userInfo)
                  store.commit('module/setUserInfo', userInfo)
                })
                .catch(function (err) {
                  console.log(err)
                })
            })
        })
        .catch(function (err) {
          console.log(err)
          alert(err)
        })
    }
    // 회원 탈퇴
    const remove = function () {
      Swal.fire({
        title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">정말 탈퇴 하시겠습니까?</span>',
        showCancelButton: true,
        confirmButtonColor: '#ce1919',
        cancelButtonColor: '#18C75E',
        cancelButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">취소</span>',
        confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">탈퇴</span>'
      }).then((result) => {
        if (result.isConfirmed) {
          store.dispatch('module/deleteUser')
            .then(function (res) {
              console.log(res)
              Swal.fire({
                title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">성공적으로 탈퇴되었습니다.</span>',
                confirmButtonColor: '#18C75E',
                confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
              })
                .then(function () {
                  router.push('/')
                })
            })
            .catch(function (err) {
              console.log(err)
              Swal.fire({
                title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:16px;">회원탈퇴에 실패했습니다.</span>',
                confirmButtonColor: '#ce1919',
                confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
              })
            })
        }
      })
    }
    return {
      userInfo,
      remove,
      myEdit,
      onClickImageUpload,
      onChangeImages,
      imgLabelClick
    }
  }
})
</script>

<style scoped>
table {
  margin: auto;
}
table, td, th{
  border:1.5px solid rgb(153, 149, 149);
  border-collapse: collapse;
  border-right: none;
  border-left: none;
  border-bottom: none;
  border-top: none;
  background-color: white;
  margin-top: 10px;
}
.left{
  /* font-weight:bold; */
  width: 100px;
  height: 50px;
  /* align-content: left; */
}
.all{
  width: 775px;
  background-color: white;
  height: 800px;
  margin-left: 500px;
  border-radius: 10px;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

td {
  width: 500px;
  margin-right: 4px;
  margin-top: 10px;
}
tr {
  height: 50px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.mypage {
  text-align: left;
  background-color: white;
  margin-top: 20px;
}
.btn{
  cursor: pointer;
  width: 150px;
  float: left;
  margin-right: 320px;
  text-align: center;
}
.icon{
  margin-bottom:5px;
  margin-left:15px;
  font-size: 2em;
  color: #18C75E;
}
.imgbtn {
  margin-left: 50px;
  background-color: rgb(0, 191, 111);
  margin-bottom: 27px;
}
.submitbtn {
  position: absolute;
  left: 400px;
  margin-top: 10px;
  margin-left: 150px;
}

</style>
