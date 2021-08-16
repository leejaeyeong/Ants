<template>
  <div id="mainContent">
    <div style="margin-top:25px; margin-left:100px; font-weight:bold; font-size:25px;">
      <p>관리자 페이지</p>
    </div>
    <div style="margin-top:10px; margin-left:100px; font-size:14px;">사원 직급, 팀 조정 등의 관리가 가능한 페이지 입니다.</div>
    <q-table
      title="Management"
      :rows="acceptmemeber"
      :columns="columns"
      row-key="name"
      v-model:pagination="pagination"
      hide-pagination
      class="table"
    >
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td
            v-for="col in props.cols"
            :key="col.name"
            :props="props"
          >
            <span v-if="col.name !='profileLocation'" >{{ col.value }}</span>
            <q-avatar v-if="col.name =='profileLocation'" size="50px" class="shadow-10">
              <img :src="props.row.profileLocation">
            </q-avatar>
          </q-td>
        </q-tr>
      </template>
    </q-table>
    <div class="page">
      <q-pagination
        v-model="pagination.page"
        color="grey-8"
        :max="pagesNumber"
        size="sm"
      />
    </div>
    <div class="rightside shadow-1">
      <q-list padding >
        <q-item>
          <q-item-section>
            <q-item-label>승인 대기</q-item-label>
            <q-item-label></q-item-label>
            <q-item-label caption>가입 승인 대기자 명단입니다.</q-item-label>
          </q-item-section>
          <q-item-section side top>
            <q-item-label caption>5 min ago</q-item-label>
          </q-item-section>
        </q-item>
        <q-separator spaced />
        <q-scroll-area style="height: 480px; max-width: 400px;">
          <div v-for="(userInfo, idx) in waitmemeber" :key="idx">
            <div v-if="waitmemeber.length > 0">
              <q-item>
                <q-item-section top avatar>
                  <q-avatar>
                    <img :src= "userInfo.profileLocation" >
                  </q-avatar>
                </q-item-section>
                <q-item-section>
                  <q-item-label>{{ userInfo.name }}</q-item-label>
                  <q-item-label caption>{{ userInfo.email }}</q-item-label>
                  <q-item-label caption style>{{ userInfo.department }}</q-item-label>
                </q-item-section>
                <q-item-section side top style="margin-top:-5px;">
                  <q-badge label="NEW" />
                </q-item-section>
                <q-item-section side @click="changeUser(userInfo.userId, userInfo.name, userInfo.email)">
                  <q-icon name="send" color="green" clickable style="cursor: pointer" />
                </q-item-section>
              </q-item>
            </div>
          </div>
          <div v-if="waitmemeber.length === 0" class="empty">
              승인 대기중인 사용자가 없습니다.
          </div>
        </q-scroll-area>
      </q-list>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const columns = [
  { name: 'profileLocation', align: 'left', label: '', field: 'profileLocation', style: 'width: 10px' },
  {
    name: 'name',
    required: true,
    label: '이름',
    align: 'left',
    field: row => row.name,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'userId', align: 'left', label: '아이디', field: 'userId', sortable: true },
  { name: 'department', align: 'left', label: '팀', field: 'department', sortable: true },
  { name: 'position', align: 'left', label: '직책', field: 'position', sortable: true },
  { name: 'email', align: 'left', label: '이메일', field: 'email' },
  { name: 'holiday', align: 'left', label: '휴가일수', field: 'holiday', sortable: true }
]

export default {
  setup () {
    const Swal = require('sweetalert2')
    const router = useRouter()
    const pagination = ref({
      sortBy: 'desc',
      descending: false,
      page: 1,
      rowsPerPage: 7
    })
    const store = useStore()
    const acceptmemeber = []
    const waitmemeber = []
    const memberList = computed(() => store.getters['module/getMemberList'])
    console.log(memberList, 'groupmemberlist')
    for (let i = 0; i < memberList.value.length; i++) {
      if (memberList.value[i].state === 3) {
        waitmemeber.push(memberList.value[i])
      } else {
        acceptmemeber.push(memberList.value[i])
      }
    }
    console.log(waitmemeber, '가입대기 멤버')
    const pagesNumber = computed(() => Math.ceil(memberList.value.length / pagination.value.rowsPerPage))
    // 유저의 state(권한)변경
    const changeUser = function (id, name, email) {
      console.log('유저권한변경', id)
      store.dispatch('module/chageUser', id)
        .then(function (res) {
          store.dispatch('module/sendEmail', { name, email })
            .then(function (res) {
              Swal.fire({
                title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">가입 승인 메일이 발송되었습니다.</span>',
                confirmButtonColor: '#19CE60',
                confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
              })
              store.dispatch('module/memberList')
                .then(function (result) {
                  console.log(result, '멤버리스트 디스패치보냈다')
                  for (let i = 0; i < result.data.length; i++) {
                    for (let j = 0; j < res.data.length; j++) {
                      if (result.data[i].department === res.data[j].id) {
                        result.data[i].department = res.data[j].departmentName
                        console.log(res)
                      }
                    }
                  }
                  console.log('그룹페이지에서 포문 끝났음')
                  store.commit('module/setMemberList', result.data)
                  console.log('커밋했음')
                  router.go(0)
                })
                .catch(function (err) {
                  console.log(err, '세번째 디스패치 에러')
                })
            })
            .catch(function (err) {
              console.log(err, '두번째 디스패치 에러')
            })
        })
      //     store.dispatch('module/memberList')
      //       .then(function (result) {
      //         console.log(res, 'bbbbbbbbbbbbbbbbb')
      //         for (let i = 0; i < result.data.length; i++) {
      //           for (let j = 0; j < res.data.length; j++) {
      //             if (result.data[i].department === res.data[j].id) {
      //               result.data[i].department = res.data[j].departmentName
      //               console.log(res, 'ccccccccccccccccc')
      //             }
      //           }
      //         }
      //         console.log('그룹리스트')
      //         store.commit('module/setMemberList', result.data)
      //         console.log(store.getters['module/getMemberList'], '체인지유저')
      //       })
      //     console.log('되는거야 마는거야')
      //     router.go(0)
      //   })
      //   .catch(function (err) {
      //     console.log(err)
      //   })
    }
    return {
      pagination,
      columns,
      memberList,
      pagesNumber,
      acceptmemeber,
      waitmemeber,
      changeUser
    }
  }
}
</script>

<style>
#mainContent{
  width:1858px;
  height:908px;
  float:right;
  position: absolute;
  top: 60px;
  left: 60px;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: rgb(242, 247, 244);
}
.table{
  margin-left: 100px;
  margin-top: 55px;
  width: 1200px;
  height: 600px;
  border-radius: 12px;
}
.my-table-details {
  font-size: 0.85em;
  font-style: italic;
  max-width: 200px;
  white-space: normal;
  color: #555;
  margin-top: 4px;
}
.rightside {
  background-color: white;
  width: 400px;
  height: 600px;
  float: right;
  position: absolute;
  bottom: 150px;
  margin-left: 1325px;
  border-radius: 12px;
  animation: rightFadeIn 0.9s ease-in-out;
}
.name{
  display:inline-block;
  margin-left: 38px;
  margin-top: 20px;
  font-size:20px;
}
.page{
  display: flex;
  justify-content: center;
  margin-top: 15px;
  margin-right: 500px;
}
@keyframes rightFadeIn {
  from {
    opacity: 0;
    transform: translateX(20px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
.empty{
  margin-left: 55px;
  color: rgb(145, 145, 145);
  font-size: 20px;
}
</style>
