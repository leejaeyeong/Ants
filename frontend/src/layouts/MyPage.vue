<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="mainSide">
                <q-btn style="margin-top:30px; width:70%; margin-left:37px; color: white; background-color: #18C75E;" icon="account_box" label="마이페이지" />
                <div id="listTop">
                    <div class="list1" @click="mvEdit">
                        <q-icon class="icon" name="build"/>
                        <span class="list2">회원 정보 수정</span>
                    </div>
                    <div class="list1">
                        <q-icon class="icon" name="folder"/>
                        <span class="list2">근태 정보</span>
                    </div>
                    <div @click="remove" class="list1">
                        <q-icon class="icon" name="delete"/>
                        <span class="list2">회원 탈퇴</span>
                    </div>
                </div>
            </div>
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
        </div>
    </div>
</template>

<script>
import { defineComponent, reactive } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'

export default defineComponent({
  name: 'mypage',
  components: {
    Header,
    Side
  },
  methods: {
    mvEdit () {
      this.$router.push('/mypageedit')
    }
  },
  setup () {
    // 회원 탈퇴
    const Swal = require('sweetalert2')
    const remove = function () {
      Swal.fire({
        title: '정말 탈퇴하시겠습니까 ?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#6581A6',
        confirmButtonText: 'Sure'
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire(
            '탈퇴 완료',
            '그동안 이용해주셔서 감사합니다.',
            'success'
          )
        }
      })
    }
    // 개인정보
    const profile = reactive({
      id: localStorage.getItem('id'),
      name: localStorage.getItem('name'),
      department: localStorage.getItem('department'),
      position: localStorage.getItem('position')
    })
    return {
      remove,
      profile
    }
  }
})
</script>

<style scoped>
#content{
    width:100%;
    height: 680px;
}
#main{
    width:95%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:white;
}
#mainSide{
    width: 250px;
    height: 690px;
    border-right:1px solid rgb(212, 212, 212);
    position: absolute;
    top:60px;
    left:75px;
}
#listTop{
    width:80%;
    height:120px;
    margin:10px auto;
    border-bottom:1px solid rgb(216, 210, 210);
}
.list1{
    width:80%;
    margin-top:5px;
    margin-left:20px;
}
.list1:hover{
    background-color:rgb(223, 241, 231);
    cursor: pointer;
    border-radius: 5%;
}
.list2{
    margin-left:15px;
}
.icon{
  margin-bottom:5px;
  margin-left:15px;
  font-size: 2em;
  color: #18C75E;
}
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
