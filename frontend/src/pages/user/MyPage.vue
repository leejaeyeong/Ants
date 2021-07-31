<template>
  <div id="myData">
      <div style="margin-top:25px; margin-left:30px; font-weight:bold; font-size:25px;">회원 정보</div>
      <table width="800" height="350" style="text-align:center; margin-left:30px; margin-top:15px;">
        <tr>
          <td colspan="2" rowspan="4" width="35">사진</td>
          <td width="100" class="left">성명</td>
          <td width="200">{{ profile.name }}</td>
          <td width="100" class="left">ID</td>
          <td width="200">{{ profile.id }}</td>
        </tr>
        <tr>
          <td class="left">생년월일</td>
          <td><q-input outlined label="" disable /></td>
          <td class="left">재직여부</td>
          <td>재직</td>
        </tr>
        <tr>
          <td class="left">소속</td>
          <td><q-input outlined label="" disable /></td>
          <td class="left">부서</td>
          <td><q-input outlined label="" disable />{{ profile.department }}</td>
        </tr>
        <tr>
          <td class="left">직책</td>
          <td><q-input outlined label="" disable /></td>
          <td class="left">핸드폰</td>
          <td><q-input outlined label="" disable /></td>
        </tr>
        <tr>
          <td colspan="2" rowspan="2">
            <q-btn style="width:125px; color: white; background-color: #18C75E;" icon="photo_camera" label="사진변경" /><br>
          </td>
          <td colspan="2" class="left">이메일</td>
          <td colspan="2"><q-input outlined label="" disable /></td>
        </tr>
        <tr>
          <td colspan="2" class="left">주소</td>
          <td colspan="2"><q-input outlined label="" disable /></td>
        </tr>
      </table>
      <!-- <q-btn style="margin-left:735px; margin-top:10px; width:95px; color: white; background-color: #18C75E;" icon="build" label="수정" /> -->
  </div>
</template>

<script>
import { defineComponent, reactive } from 'vue'

export default defineComponent({
  name: 'mypage',
  setup () {
    // 개인정보
    const profile = reactive({
      id: localStorage.getItem('id'),
      name: localStorage.getItem('name'),
      department: localStorage.getItem('department'),
      position: localStorage.getItem('position')
    })
    return {
      profile
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
