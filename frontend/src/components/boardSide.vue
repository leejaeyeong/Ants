<template>
   <div id="mainSide">
        <q-btn @click="mvWrite" style="margin-top:30px; width:70%; margin-left:37px; color: white; background-color: #18C75E;" icon="mail" label="글쓰기" />
        <div id="listTop">
            <div @click="viewAll" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #18C75E;" name="apps"/>
                <span class="list2">전체 글</span>
            </div>
            <div @click="viewMark" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #18C75E;" name="turned_in_not"/>
                <span class="list2">북마크한 글</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #18C75E;" name="edit"/>
                <span class="list2">내가 쓴 글</span>
            </div>
        </div>
        <div id="listBot">
            <span style="font-weight:bold; font-size:18px; margin-top:0px; margin-left:30px; display:inline-block;">사내 게시판 목록</span>
            <div @click="viewType(list.id)" v-for="list in boardList" :key="list.id" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #18C75E;" name="description"/>
                <span class="list2">{{ list.type }}</span>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { outlinedAllInbox } from '@quasar/extras/material-icons-outlined'

export default defineComponent({
  name: 'boardSide',
  methods: {
  },
  setup () {
    const store = useStore()
    const router = useRouter()
    const boardList = computed(() => store.getters['module/getBoardList'])
    const mvWrite = function () {
      router.push('/write')
    }
    let rows = []
    const viewAll = function () {
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          rows = []
          const pn = Math.ceil(rows.length / 8)
          store.commit('module/setPageNumber', pn)
          router.push('/board')
        })
        .catch(function () {
          alert('오류발생')
        })
    }
    const viewMark = function () {
      store.commit('module/setRows', rows)
      router.push('/boardMark')
    }
    const viewType = function (id) {
      store.dispatch('module/boardType', id)
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          rows = []
          const pn = Math.ceil(rows.length / 8)
          store.commit('module/setPageNumber', pn)
          router.push('/boardType')
        })
    }
    return {
      outlinedAllInbox,
      boardList,
      mvWrite,
      viewAll,
      viewMark,
      viewType
    }
  }
})
</script>

<style scoped>
#mainSide{
    width: 250px;
    height: 690px;
    border-right:1px solid rgb(212, 212, 212);
    position: absolute;
    top:60px;
    left:75px;
}
#listTop{
    width:80%;
    height:115px;
    margin:12px auto;
    border-bottom:1px solid rgb(216, 210, 210);
}
.list1{
    width:85%;
    margin-top:5px;
    margin-left:10px;
}
.list1:hover{
    background-color:rgb(223, 241, 231);
    cursor: pointer;
}
.list2{
    margin-left:15px;
}
#listBot{
    width:80%;
    height:300px;
    margin:10px auto;
}
</style>
