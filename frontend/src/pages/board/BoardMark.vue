<template>
  <div id="board">
    <div>
        <q-input filled color="teal" style="height:40px; display:inline-block; width:20%; margin-top:35px; margin-left:40px;" label="제목, 내용" />
        <q-btn round style="margin-top:-20px; margin-left:10px; background-color: #18C75E; color:white; height:50px; width:50px;" icon="search" />
    </div>
    <div class="q-pa-md" style="width:120%; margin-top:30px; margin-left:25px;">
      <q-table
        title="공지사항"
        :rows="rows"
        :columns="columns"
        row-key="name"
        v-model:pagination="pagination"
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
import { ref, computed, defineComponent } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'board',
  methods: { },
  setup () {
    const store = useStore()

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
    return {
      pagination,
      columns,
      rows,
      pagesNumber
    }
  }
})
</script>

<style scoped>

#board{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:75px;
    left:390px;
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
