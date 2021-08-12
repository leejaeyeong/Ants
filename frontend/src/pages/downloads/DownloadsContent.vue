<template>
  <div class="content" align="center">
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
            <!-- <input ref="input" /> -->
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
    <div class="sub-side shadow-1">
      <div align="right">
        <span style="padding-right:15px;"></span>
      </div>
      <div align="center">
        <!-- <div><span class="highlight-orange" style="font-size: 20px;">확장자 별 검색</span></div> -->
        <div class="row" align="left" style="margin-top: 0px; margin-left: 150px;">
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.zip')">
              <img src="https://blog.kakaocdn.net/dn/dK3cLI/btqGteLVjDW/ddq5d2gBFO0kxK6eMkreW1/img.png">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">알집 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.pdf')">
              <img src="https://blog.kakaocdn.net/dn/cj4Y3U/btqMPi7uAh8/sYik4nsvvqUmG36Hhbwwj1/img.png">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">pdf 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.xlsx')">
              <img src="data:image/png;base64,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">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">엑셀 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.hwp')">
              <img src="https://lh3.googleusercontent.com/proxy/LN8tmUbRlarEWaIjpx8myVgVguxNxWiokw7jhU5qyK8XQK6A_KiZF8UsNzSW6U2KQNXiErnVsTPnDYCHZ8nj7_UhgCmJITu7dviYP3fg7oEp">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">한글 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10" @click="clickExtension('.docx')">
              <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7Gc35vru52VOvdoidjxRXOHZjLdFOSJND7fh-ADfqKBqz3MMYLbW2II5thFpukmye2nY&usqp=CAU">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">워드 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
          <div class="col-2">
            <q-avatar size="60px" class="shadow-10">
              <img src="https://us.123rf.com/450wm/dxinerz/dxinerz1509/dxinerz150901426/45612323-jpg-%ED%8C%8C%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?ver=6">
              <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
                  <strong style="font-size: 12px;">이미지 자료 검색</strong>
              </q-tooltip>
            </q-avatar>
          </div>
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
        case '.hwp':
          imageUrl = 'https://lh3.googleusercontent.com/proxy/LN8tmUbRlarEWaIjpx8myVgVguxNxWiokw7jhU5qyK8XQK6A_KiZF8UsNzSW6U2KQNXiErnVsTPnDYCHZ8nj7_UhgCmJITu7dviYP3fg7oEp'
          break
        case '.docx':
          imageUrl = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7Gc35vru52VOvdoidjxRXOHZjLdFOSJND7fh-ADfqKBqz3MMYLbW2II5thFpukmye2nY&usqp=CAU'
          break
        case '.xlsx':
          imageUrl = 'data:image/png;base64,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'
          break
        case '.pdf':
          imageUrl = 'https://blog.kakaocdn.net/dn/cj4Y3U/btqMPi7uAh8/sYik4nsvvqUmG36Hhbwwj1/img.png'
          break
        case '.jpg':
        case '.png':
        case '.JPG':
        case '.PNG':
          imageUrl = 'https://us.123rf.com/450wm/dxinerz/dxinerz1509/dxinerz150901426/45612323-jpg-%ED%8C%8C%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?ver=6'
          break
        default:
          imageUrl = 'https://i.pinimg.com/474x/78/6d/27/786d27105f7e9f02f92c31040169b2de.jpg'
      }
      return imageUrl
    }
    function searchBarReset () {
      const input = document.getElementById('searchInput')
      console.log(input)
      input.value = null
      pageChange()
    }
    function clickExtension (extension) {
      store.dispatch('module/loadFileDataByExtension', extension)
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
      clickExtension,
      keyupEvent,
      pageChange,
      router,
      input,
      searchBarReset
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
  top: 220px;
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
.highlight-orange {
  background: linear-gradient(to top, orange 40%, transparent 40%);
}
.sub-side {
  width: 1090px;
  height: 115px;
  position: absolute;
  top: -140px;
  left: 155px;
  border-radius: 10px;
  background: white;
  border: 1px solid rgb(212, 212, 212);
}
.col-2 {
  cursor: pointer;
}
</style>
