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
    <div class="rightside">
      <q-list bordered padding>
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
        <div v-for="(userInfo, idx) in waitmemeber" :key="idx">
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
            <q-item-section side>
              <q-icon name="send" color="green" />
            </q-item-section>
          </q-item>
        </div>
      </q-list>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'

const columns = [
  { name: 'profileLocation', align: 'left', label: '상태', field: 'profileLocation', style: 'width: 10px' },
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
    const pagination = ref({
      sortBy: 'desc',
      descending: false,
      page: 1,
      rowsPerPage: 5
      // rowsNumber: xx if getting data from a server
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
      console.log(waitmemeber, '멤버리스트')
    }
    const pagesNumber = computed(() => Math.ceil(memberList.value.length / pagination.value.rowsPerPage))
    return {
      pagination,
      columns,
      memberList,
      pagesNumber,
      acceptmemeber,
      waitmemeber
    }
  }
}
</script>

<style>
#mainContent{
  width:100%;
  height:940px;
  float:right;
  position: absolute;
  top: 60px;
  left: 70px;
  border-bottom:0.5px solid rgb(212, 212, 212);
  background-color: rgb(242, 247, 244);
}
.table{
  margin-left: 100px;
  margin-top: 55px;
  width: 1200px;
  height: 600px;
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
  height: 500px;
  float: right;
  position: absolute;
  bottom: 285px;
  margin-left: 1325px;
  box-shadow: 5px 5px 30px #a0a0a0;
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
</style>
