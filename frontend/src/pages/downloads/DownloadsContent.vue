<template>
  <div class="content" align="center">
    <div class="sub-side shadow-1">
      <div align="right">
        <span style="padding-right:15px;"></span>
      </div>
      <div align="center">
        <!-- <div><span class="highlight-orange" style="font-size: 20px;">확장자 별 검색</span></div> -->
        <div class="row" align="left" style="margin-left: 150px;">
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.zip')">
              <img :src="present.image.zip">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">알집 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.pdf')">
              <img :src="present.image.pdf">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">pdf 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.xlsx')">
              <img :src="present.image.xlsx">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">엑셀 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.hwp')">
              <img :src="present.image.hwp">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">한글 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.docx')">
              <img :src="present.image.docx">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">워드 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.jpg')">
              <img :src="present.image.jpg">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">이미지 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
        </div>
      </div>
    </div>
    <div class="content-body">
      <div class="q-pa-md">
        <q-table
          title="검색된 파일 목록"
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
      <div class="content-title">
          <div class="row">
            <div class="col-3">
            </div>
            <div class="col-6">
              <input id='searchInput'
              style="padding: 15px; width: 500px; IME-MODE: inactive"
              ref="input"
              outlined v-model="router.currentRoute.value.query.fileName"
              :dense="dense"
              v-on:keyup="keyupEvent"
              placeholder="파일명으로 자료 검색"
              />
            </div>
            <div class="col-1">
              <q-btn class="search-btn" @click="searchBarReset()">초기화</q-btn>
            </div>
          </div>
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
  { name: 'userName', label: '업로더', field: 'userName' }
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
import { defineComponent, ref, computed, onMounted, reactive } from 'vue'
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
    const fileImage = computed(() => store.getters['module/getFileImage']).value
    const present = reactive({
      image: fileImage
    })
    const Swal = require('sweetalert2')
    loadFileData()
    console.log(rows)
    const pagination = ref({
      sortBy: 'date',
      descending: false,
      page: 1,
      rowsPerPage: 6
      // rowsNumber: xx if getting data from a server
    })
    const keyupEvent = function (event) {
      console.log(event.target.value)
      const keyword = event.target.value
      store.dispatch('module/loadFileDataByFileName', keyword)
        .then(function (result) {
          console.log(result.data + 'key up잘 받아오는지')
          for (let i = 0; i < result.data.length; i++) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
          }
          store.commit('module/setFileinfoList', result.data)
          if (router.currentRoute._rawValue.path === '/downloads') {
            router.push({ name: 'Downloads2', query: { fileName: keyword } })
          } else {
            router.push({ name: 'Downloads', query: { fileName: keyword } })
          }
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
          for (let i = 0; i < result.data.length; i++) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
          }
          store.commit('module/setFileinfoList', result.data)
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
    function searchBarReset () {
      const input = document.getElementById('searchInput')
      input.value = null
      pageChange()
    }
    function clickExtension (extension) {
      store.dispatch('module/loadFileDataByExtension', extension)
        .then(function (result) {
          console.log(result.data)
          for (let i = 0; i < result.data.length; i++) {
            const extension = result.data[i].fileExtension.substring(1, result.data[i].fileExtension.length)
            result.data[i].imageLocation = (fileImage[extension] === undefined) ? fileImage.other : fileImage[extension]
          }
          // // setFileinfoList
          store.commit('module/setFileinfoList', result.data)
          pageChange()
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
      clickExtension,
      keyupEvent,
      pageChange,
      router,
      input,
      searchBarReset,
      present
    }
  }
})
</script>

<style scoped>
.content {
  width: 1520px;
  height: 907px;
  position: absolute;
  left: 400px;
  top: 60px;
  background-color: rgb(242, 247, 244);
}
.sub-side {
  width: 1090px;
  height: 110px;
  position: absolute;
  top: 40px;
  left: 225px;
  border-radius: 10px;
  background: white;
  border: 1px solid rgb(212, 212, 212);
}
.content-body {
  width: 80%;
  margin-top: 180px;
}
.content-title {
  width: 100%;
  height: 80px;
  margin-top: 50px;
  margin-left: -80px;
}
.margin-auto {
  margin-left: auto;
  margin-right: auto;
}
.search-btn {
  background-color: #249752;
  height: 55px;
  margin-left: -40px;
}
.highlight-orange {
  background: linear-gradient(to top, orange 40%, transparent 40%);
}

.col-2 {
  cursor: pointer;
}
</style>
