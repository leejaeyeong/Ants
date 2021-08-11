<template>
  <div class="content" align="center">
    <div class="content-title">
        <div class="row">
          <div class="col-3">
          </div>
          <div class="col-5">
            <q-input
            ref="input"
            outlined v-model="router.currentRoute.value.query.fileName"
            :dense="dense"
            v-on:keyup="keyupEvent"
            placeholder="파일명으로 자료 검색"
            />
          </div>
          <div class="col-1">
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
import { defineComponent, ref, computed, onMounted } from 'vue'
// import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'DonwloadsContent',
  components: {
  },
  setup () {
    const input = ref(null)
    onMounted(() => {
      input.value.focus()
    })
    console.log('다운로드 컨텐츠 셋업 호출')
    const store = useStore()
    const router = useRouter()
    console.log(router.query)
    const rows = computed(() => store.getters['module/getFileInfoList']).value
    const Swal = require('sweetalert2')
    loadFileData()
    console.log(rows)
    const pagination = ref({
      sortBy: 'date',
      descending: false,
      page: 1,
      rowsPerPage: 8
      // rowsNumber: xx if getting data from a server
    })
    const keyupEvent = function (event) {
      console.log(event.target.value)
      const keyword = event.target.value
      store.dispatch('module/loadFileDataByFileName', keyword)
        .then(function (result) {
          console.log(result.data + 'key up잘 받아오는지')
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].imageLocation = mountImageUrl(result.data[i].fileExtension)
          }
          store.commit('module/setFileinfoList', result.data)
          console.log(store.getters['module/getFileInfoList'], '파일 게터스')
          if (router.currentRoute._rawValue.path === '/downloads') {
            router.push({ name: 'Downloads2', query: { fileName: keyword } })
          } else {
            router.push({ name: 'Downloads', query: { fileName: keyword } })
          }
          console.log(router)
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.222</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
      // pageChange()
    }
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
    function loadFileData () {
      store.dispatch('module/loadFileData')
        .then(function (result) {
          console.log(result.data + '잘 받아오는지')
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].imageLocation = mountImageUrl(result.data[i].fileExtension)
          }
          // setFileinfoList
          store.commit('module/setFileinfoList', result.data)
          console.log(store.getters['module/getFileInfoList'], '파일 게터스')
          // console.log(router)
        })
        .catch(function () {
          Swal.fire({
            title: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size : 16px;">서버오류. 다시 시도해주세요.</span>',
            confirmButtonColor: '#ce1919',
            confirmButtonText: '<span style="font-family:NEXON Lv1 Gothic OTF; font-size:14px;">확인</span>'
          })
        })
    }
    function pageChange () {
      console.log(router.currentRoute._rawValue.path)
      if (router.currentRoute._rawValue.path === '/downloads') {
        router.push('/downloads2')
      } else {
        router.push('/downloads')
      }
    }
    function mountImageUrl (extension) {
      let imageUrl = ''
      switch (extension) {
        case '.ppt':
        case '.pptx':
          imageUrl = 'https://aux.iconspalace.com/uploads/freeform-powerpoint-icon-256.png'
          break
        case '.docx':
          imageUrl = 'https://i.pinimg.com/474x/ea/83/d6/ea83d672e55bdda2fa44e676eacad9ff.jpg'
          break
        case '.xlsx':
          imageUrl = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFEz9UnOx3StKSJUQs12DuWje3MwDOV6yAfufygK38zgZIuNsizJimqpCRI6ae2gbJuD0&usqp=CAU'
          break
        case '.pdf':
          imageUrl = 'https://blog.kakaocdn.net/dn/cj4Y3U/btqMPi7uAh8/sYik4nsvvqUmG36Hhbwwj1/img.png'
          break
        case '.jpg':
        case '.png':
        case '.JPG':
        case '.PNG':
          imageUrl = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQJ46V8yCbzB4RrjehKqyhVMB-maX5XI-Ysw5gFxdiLRLSX0R-KSFpHMcivZW2xaLUZbs&usqp=CAU'
          break
        default:
          imageUrl = 'https://i.pinimg.com/474x/78/6d/27/786d27105f7e9f02f92c31040169b2de.jpg'
      }
      return imageUrl
    }
    function clickTest () {
      store.dispatch('module/loadFileDataByExtension', '.js')
        .then(function (result) {
          console.log(result.data)
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].imageLocation = mountImageUrl(result.data[i].fileExtension)
          }
          // // setFileinfoList
          store.commit('module/setFileinfoList', result.data)
          console.log(store.getters['module/getFileInfoList'], '파일 게터스')
          if (router.currentRoute._rawValue.path === '/downloads') {
            router.push('/downloads2')
          } else {
            router.push('/downloads')
          }
        })
        .catch(function (err) {
          console.log(err)
        })
    }
    return {
      text: ref(''),
      dense: ref(false),
      pagination,
      columns,
      rows,
      pagesNumber: computed(() => Math.ceil(rows.length / pagination.value.rowsPerPage)),
      downloadFile,
      clickTest,
      keyupEvent,
      pageChange,
      router,
      input
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
  top: 35px;
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
  height: 55px;
}
</style>
