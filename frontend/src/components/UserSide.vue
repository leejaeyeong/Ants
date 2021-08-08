<template>
  <div id="mainSide">
    <q-btn @click="mvMypage" style="margin-top:30px; width:70%; margin-left:37px; color: white; background-color: #18C75E;" icon="account_box" label="마이페이지" />
    <div id="listTop">
        <div class="list1" @click="mvEdit">
            <q-icon class="icon" name="build"/>
            <span class="list2">회원 정보 수정</span>
        </div>
        <div class="list1"  @click="mvManage">
            <q-icon class="icon" name="folder"/>
            <span class="list2">근태 정보</span>
        </div>
        <div @click="remove" class="list1">
            <q-icon class="icon" name="delete"/>
            <span class="list2">회원 탈퇴</span>
        </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'mypage',
  methods: {
    mvMypage () {
      this.$router.push('/mypage')
    },
    mvEdit () {
      this.$router.push('/myedit')
    },
    mvManage () {
      this.$router.push('/management')
    }
  },
  setup () {
    // 회원 탈퇴
    const Swal = require('sweetalert2')
    const store = useStore()
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
              this.$router.push('/')
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
      remove
    }
  }
})
</script>

<style>
#mainSide{
  width: 300px;
  height: 892px;
  border-right:1px solid rgb(212, 212, 212);
  position: absolute;
  top:75px;
  left:90px;
}
#listTop{
  width:80%;
  height:135px;
  margin:12px auto;
  border-bottom:1px solid rgb(216, 210, 210);
}
.list1{
  width:95%;
  margin-top:5px;
  margin-left:0px;
}
.list1:hover{
  background-color:rgb(223, 241, 231);
  cursor: pointer;
}
.list2{
  margin-left:17px;
  font-size:18px;
}
.icon{
  margin-bottom:5px;
  margin-left:15px;
  font-size: 2em;
  color: #18C75E;
}
</style>
