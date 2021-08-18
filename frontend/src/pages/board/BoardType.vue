<template>
  <div id="allBoard">
    <div id="board">
      <div style="margin-top:25px; margin-left:50px; font-size:23px; display:inline-block;">
        <p style="font-weight:bold;">{{ boardType.name }}</p>
        <div style="margin-top:5px;font-size:14px; display:inline-block;">{{ boardType.intro }}</div>
      </div>
      <div id="search">
        <q-input color="teal" v-model="form.key" style="height:30px; width:60%; display:inline-block; margin-top:35px; margin-left:100px;" label="제목 또는 내용으로 검색" />
        <q-btn @click="search" round style="margin-top:-20px; margin-left:10px; background-color: #249752; color:white; height:30px; width:30px;" icon="search" />
      </div>
      <div class="q-pa-md" style="width:100%; margin-top:30px; margin-left:25px;">
        <q-table
          title=""
          :rows="rows"
          :columns="columns"
          row-key="name"
          @row-click="detail"
          v-model:pagination="pagination"
          hide-pagination
        />
        <div class="row justify-center q-mt-md page">
          <q-pagination
            v-model="pagination.page"
            color="grey-8"
            :max="pagesNumber"
            size="md"
          />
        </div>
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
    const router= useRouter()
    const store = useStore()
    const columns = computed(() => store.getters['module/getColumns'])
    const rows = computed(() => store.getters['module/getRows'])
    const boardType = computed(() => store.getters['module/getBoardType'])
    const pagination = ref({
      sortBy: 'desc',
      descending: false,
      page: 1,
      rowsPerPage: 8
      // rowsNumber: xx if getting data from a server
    })
    const pagesNumber = computed(() => Math.ceil(rows.value.length / pagination.value.rowsPerPage))
    // const pagesNumber = computed(() => store.getters['module/getPageNumber'])
    
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
      store.dispatch('module/countView', row.id)
        .then(function (res) {
          console.log('조횟수액션 실행됨', res)
          store.dispatch('module/boardDetail', row.id)
            .then(function (result) {
              store.commit('module/setDetail', result.data)
              var tmp = []
              for (let i = 0; i<result.data.comments.length; i++) {
                result.data.comments[i].registrationTime = result.data.comments[i].registrationTime.substr(0, 16)
                tmp.push(result.data.comments[i])
              }
              store.commit('module/setComments',tmp)
              const markList = store.getters['module/getMarkList']
              for (let i = 0; i < markList.length; i++) {
                if (row.id == markList[i].id) {
                  store.commit('module/setMark', true)
                  break
                }
                if (i == markList.length - 1) {
                  store.commit('module/setMark', false)
                }
              }
              router.push('/boardDetail')
            })
        })
    }
    return {
      pagination,
      columns,
      rows,
      pagesNumber,
      form,
      search,
      detail,
      boardType
    }
  }
})
</script>

<style scoped>
#board{
  width: 1350px;
  height: 750px;
  position: absolute;
  top:30px;
  left:100px;
}
#search { 
  width: 450px;
  float:right;
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
.page{
  margin-top: 15px;
  font-size: 15px;
}
</style>
