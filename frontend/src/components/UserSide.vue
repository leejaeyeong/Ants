<template>
  <div id="mainSide">
    <q-btn @click="mvMypage" style="margin-top:30px; width:70%; margin-left:37px; color: white; background-color: #18C75E;" icon="account_box" label="마이페이지" />
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
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'mypage',
  methods: {
    mvMypage () {
      this.$router.push('/mypage')
    },
    mvEdit () {
      this.$router.push('/myedit')
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
    return {
      remove
    }
  }
})
</script>

<style>
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
</style>
