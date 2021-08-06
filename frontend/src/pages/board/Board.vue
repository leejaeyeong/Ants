<template>
  <div id="board">
    <div>
        <q-input filled color="teal" v-model="form.key" style="height:40px; display:inline-block; width:15%; margin-top:20px; margin-left:40px;" label="제목, 내용" />
        <q-btn @click="search" round style="margin-top:-20px; margin-left:10px; background-color: #18C75E; color:white; height:50px; width:50px;" icon="search" />
    </div>
    <div class="q-pa-md" style="width:95%; margin-left:25px;">
      <q-table
        title="공지사항"
        :rows="rows"
        :columns="columns"
        row-key="id"
        v-model:pagination="pagination"
        @row-click="detail"
        style="cursor:pointer;"
        hide-pagination
      />
      <div class="row justify-center q-mt-md">
        <q-pagination
          v-model="pagination.page"
          color="grey-8"
          :max="pagesNumber"
          size="lg"
        />
      </div>
    </div>
  </div>

</template>

<script>
import { reactive, ref, computed, defineComponent } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'board',
  methods: { },
  setup () {
    const store = useStore()
    const router = useRouter()
    const columns = computed(() => store.getters['module/getColumns'])
    const rows = computed(() => store.getters['module/getRows'])
    const pagination = ref({
      sortBy: 'desc',
      descending: false,
      page: 1,
      rowsPerPage: 8
      // rowsNumber: xx if getting data from a server
    })
    const pagesNumber = computed(() => store.getters['module/getPageNumber'])
    
    const form = reactive({
      key: ''
    })
    const search = function () {
      var tempRows = []
      var temp = store.getters['module/getRows']
      for(let i = 0; i < temp.length; i++) {
        if(temp[i].title.indexOf(form.key) != -1){
          tempRows.push(temp[i])
        }
      }
      store.commit('module/setRows', tempRows)
      tempRows = []
      form.key = ''
    }

    const detail = function (evt, row) {
      console.log(evt, row)
      store.dispatch('module/boardDetail', row.id)
        .then(function (result) {
          console.log('------------------------')
          console.log(result.data)
          store.commit('module/setDetail', result.data)
          const tmp = store.getters['module/getDetail']
          console.log(tmp)
          console.log('------------------------')
          router.push('/boardDetail')
        })
    }
    return {
      pagination,
      columns,
      rows,
      pagesNumber,
      form,
      search,
      detail
    }
  }
})
</script>

<style scoped>

#board{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:60px;
    left:325px;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
/* div{
  font-family: 'NEXON Lv1 Gothic OTF';
}
span{
  font-family: 'NEXON Lv1 Gothic OTF';
} */
</style>
