<template>
  <div class="content" align="center">
    <div class="content-title">
        <div class="row">
          <div class="col-2">
          </div>
          <div class="col-6">
            <q-input outlined v-model="text" :dense="dense" />
          </div>
          <div class="col-2">
            <q-btn class="search-btn" @click="clickTest()">자료검색</q-btn>
          </div>
        </div>
    </div>
    <div class="content-body">
      <div class="q-pa-md">
    <q-table
      title="전체 파일 목록"
      :rows="rows"
      :columns="columns"
      row-key="fileName"
      style="cursor:pointer;"
      v-model:pagination="pagination"
      hide-pagination
    >
      <template v-slot:body="props">
        <q-tr :props="props" @click="downloadFile(props.row.id)">
          <q-td
            v-for="col in props.cols"
            :key="col.name"
            :props="props"
          >
            <span v-if="col.name !='imageLocation'" >{{ col.value }}</span>
            <q-avatar v-if="col.name =='imageLocation'" size="50px" class="shadow-10">
              <img :src="props.row.imageLocation">
            </q-avatar>
          </q-td>
        </q-tr>
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
    </div>
  </div>
</template>

<script>
import { defineComponent, ref, computed, onUpdated } from 'vue'
// import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const columns = [
  { name: 'imageLocation', label: '파일 분류', field: 'imageLocation', sortable: true, style: 'width: 10px' },
  // { name: 'desc', label: '파일명', field: 'desc', sortable: true },
  {
    name: 'fileName',
    required: true,
    label: '파일명',
    align: 'center',
    field: 'fileName',
    // format: val => `${val}`,
    sortable: true
  },
  { name: 'size', align: 'center', label: '파일 크기', field: 'size', sortable: true },
  { name: 'date', label: '업로드 날짜', field: 'date', sortable: true },
  // { name: 'carbs', label: 'Carbs (g)', field: 'carbs' },
  { name: 'uploader', label: '업로더', field: 'uploader' }
]

// const rows = [
//   {
//     name: 'Frozen Yogurt',
//     calories: 159,
//     fat: 6.0,
//     carbs: 24,
//     protein: 4.0,
//     image: 'https://i.pinimg.com/474x/ea/83/d6/ea83d672e55bdda2fa44e676eacad9ff.jpg'
//   }
// ]

export default defineComponent({
  name: 'DonwloadsContent',
  components: {
  },
  setup () {
    console.log('다운로드 컨텐츠 셋업 호출')
    const store = useStore()
    const router = useRouter()
    const rows = computed(() => store.getters['module/getFileInfoList']).value
    console.log(rows)
    const pagination = ref({
      sortBy: 'date',
      descending: false,
      page: 1,
      rowsPerPage: 10
      // rowsNumber: xx if getting data from a server
    })
    function downloadFile (id) {
      console.log(id)
      store.dispatch('module/downloadFile', id)
        .then(function (result) {
          const diposition = result.headers['content-disposition']
          const fileName = diposition.split("''")[1]
          console.log(result.headers['content-type'])
          console.log(diposition)
          const url = window.URL.createObjectURL(new Blob([result.data]))
          const anchor = document.createElement('a')
          anchor.href = url
          anchor.setAttribute('download', fileName)
          document.body.appendChild(anchor)
          anchor.click()
          anchor.remove()
          window.URL.revokeObjectURL(url)
        })
        .catch(function (err) {
          console.log(err)
        })
    }
    function clickTest () {
      alert('test')
      store.dispatch('module/loadFileDataByExtension', '.js')
        .then(function (result) {
          console.log(result.data)
          for (let i = 0; i < result.data.length; i++) {
            // result.data[i].imageLocation = mountImageUrl(result.data[i].fileExtension)
          }
          // // setFileinfoList
          store.commit('module/setFileinfoList', result.data)
          console.log(store.getters['module/getFileInfoList'], '파일 게터스')
          router.push('/downloads2')
        })
        .catch(function (err) {
          console.log(err)
        })
    }
    onUpdated(() => {
      // rows = computed(() => store.getters['module/getFileInfoList']).value
      console.log('updated!')
    })
    return {
      text: ref(''),
      dense: ref(false),
      pagination,
      columns,
      rows,
      pagesNumber: computed(() => Math.ceil(rows.length / pagination.value.rowsPerPage)),
      downloadFile,
      clickTest
    }
  }
})
</script>

<style scoped>
.content {
  width: 1400px;
  height: 800px;
  position: absolute;
  left: 450px;
  top: 85px;
  /* background: orange; */
}
.content-title {
  /* background: yellowgreen; */
  width: 100%;
  height: 80px;
  margin-top: 50px;
}
.content-body {
  width: 80%;
}
.margin-auto {
  margin-left: auto;
  margin-right: auto;
}
.search-btn {
  background-color: #00BF6F;
  margin-left: 20px;
  height: 50px;
}
</style>
