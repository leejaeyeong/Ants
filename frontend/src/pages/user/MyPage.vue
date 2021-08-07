
  <!-- <div id="myData">
      <div style="margin-top:25px; margin-left:30px; font-weight:bold; font-size:25px;">회원 정보</div>
      <div id="userform">
      </div>
      <table width="800" height="350" style="text-align:center; margin-left:30px; margin-top:15px;">
        <tr>
          <td colspan="2" rowspan="4" width="35" >
            <img :src="userInfo.profileLocation" style="width:300px;" />
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
      </table>
  </div> -->
  <template>
  <div id="myData">
    <table class="mypage">
      <tr>
        <td class="left">사진</td>
        <td><img :src="userInfo.profileLocation" style="width: 150px; height: 200px;" /></td>
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
        <td>{{ userInfo.email }}</td>
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
  </div>
</template>

<script>
import { defineComponent, computed, ref } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'mypage',
  setup () {
    const userInfo = computed(() => store.getters['module/getUserInfo'])
    const store = useStore()
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
    return {
      userInfo,
      tab: ref('one')
    }
  }
})
</script>

<style scoped>
#myData{
  width: 1130px;
  height: 690px;
  position: absolute;
  top:60px;
  left:325px;
}
table {
  margin: auto;
  margin-top: 5%;
}
table, td, th{
  /* border:3px solid #6581A6; */
  border:1.5px solid rgb(153, 149, 149);
  border-collapse: collapse;
}
.left{
  background-color: #f9f9f9;
  font-weight:bold;
  width: 100px;
  align-content: left;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.my-card {
  width: 1000px;
}
td {
  width: 500px;
}
tr {
  height: 70px;
}
.mypage {
  text-align: center;
}
</style>
