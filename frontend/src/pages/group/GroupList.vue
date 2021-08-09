<template>
  <div id="mainContent">
    <div style="margin-top:25px; margin-left:100px; font-weight:bold; font-size:25px;">
      <p>관리자 페이지</p>
    </div>
    <div style="margin-top:10px; margin-left:100px; font-size:14px;">사원 직급, 팀 조정 등의 관리가 가능한 페이지 입니다.</div>
    <q-table
      title="Management"
      :rows="rows"
      :columns="columns"
      row-key="name"
      v-model:pagination="pagination"
      hide-pagination
      class="table"
    >
      <template v-slot:body-cell-status="props">
        <q-td :props="props">
          <div>
            <q-badge color="green" :label="props.value" />
          </div>
          <div class="my-table-details">
            {{ props.row.stauts }}
          </div>
        </q-td>
      </template>
    </q-table>
    <div class="row justify-center q-mt-md">
      <q-pagination
        v-model="pagination.page"
        color="grey-8"
        :max="pagesNumber"
        size="sm"
      />
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'

const columns = [
  {
    name: 'name',
    required: true,
    label: '이름',
    align: 'left',
    field: row => row.name,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'team', align: 'left', label: '팀', field: 'team', sortable: true },
  { name: 'position', align: 'left', label: '직책', field: 'position', sortable: true },
  { name: 'email', align: 'left', label: '이메일', field: 'email' },
  { name: 'enterday', align: 'left', label: '입사일', field: 'enterday', sortable: true },
  { name: 'status', align: 'left', label: '상태', field: 'status' }
]

const rows = [
  {
    name: 'Frozen Yogurt',
    team: 159,
    position: 6.0,
    email: 24,
    enterday: 4.0,
    status: '재직'
  },
  {
    name: 'Ice cream sandwich',
    team: 237,
    position: 9.0,
    email: 37,
    enterday: 4.3,
    status: '재직'
  },
  {
    name: 'Eclair',
    team: 262,
    position: 16.0,
    email: 23,
    enterday: 6.0,
    status: '재직'
  },
  {
    name: 'Cupcake',
    team: 305,
    position: 3.7,
    email: 67,
    enterday: 4.3,
    status: '재직'
  },
  {
    name: 'Gingerbread',
    team: 356,
    position: 16.0,
    email: 49,
    enterday: 3.9,
    status: '재직'
  },
  {
    name: 'Jelly bean',
    team: 375,
    position: 0.0,
    email: 94,
    enterday: 0.0,
    status: '재직'
  },
  {
    name: 'Lollipop',
    team: 392,
    position: 0.2,
    email: 98,
    enterday: 0,
    status: '재직'
  },
  {
    name: 'Honeycomb',
    team: 408,
    position: 3.2,
    email: 87,
    enterday: 6.5,
    status: '재직'
  },
  {
    name: 'Donut',
    team: 452,
    position: 25.0,
    email: 51,
    enterday: 4.9,
    status: '재직'
  },
  {
    name: 'KitKat',
    team: 518,
    position: 26.0,
    email: 65,
    enterday: 7,
    status: '재직'
  }
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

    return {
      pagination,
      columns,
      rows,
      pagesNumber: computed(() => Math.ceil(rows.length / pagination.value.rowsPerPage))
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
  margin-left: 200px;
  margin-top: 60px;
  width: 1500px;
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
</style>
