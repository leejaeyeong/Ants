<template>
  <div id="mainSide">
    <div id="listTop" class="shadow-1">
      <div class="name">나의 활동</div>
      <div id="mid1">
        <img :src="userInfo.profileLocation" style="width:100px; height:130px;">
      </div>
      <div class="userInfo">
        {{ userInfo.name }}
        <br>
        {{ userInfo.position }}님
        <br>
        {{ userInfo.department }}
      </div>
      <div>
        <div class="my">
          <div @click="viewMark" class="mylist1">
            <q-icon style="font-size: 2.0em; color: #249752; margin-bottom:7px;" name="turned_in_not"/>
            <span class="mylist2">북마크한 글</span>
          </div>
          <div @click="viewMe" class="mylist1">
            <q-icon style="font-size: 2.0em; color: #249752; margin-bottom:7px;" name="subject"/>
            <span class="mylist2">내가 쓴 글</span>
          </div>
        </div>
      <q-btn @click="mvWrite" style="margin-top:20px; width:170px; margin-left:45px; color: white; background-color: rgb(219, 130, 14); font-size:14px" icon="edit" label="게시판 글쓰기" />
    </div>
  </div>
        <!-- <div id="listTop">
            <div @click="viewAll" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 2.5em; color: #249752;" name="apps"/>
                <span class="list2">전체 글</span>
            </div>
            <div @click="viewMark" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 2.5em; color: #249752;" name="turned_in_not"/>
                <span class="list2">북마크한 글</span>
            </div>
            <div @click="viewMe" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 2.5em; color: #249752;" name="edit"/>
                <span class="list2">내가 쓴 글</span>
            </div>
        </div> -->
        <div id="listBot">
            <span style="font-weight:bold; font-size:20px; margin-top:15px; margin-bottom:15px; margin-left:27px; display:inline-block;">사내 게시판 목록</span>
            <div @click="viewAll" class="list1">
              <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 2.0em; color: #249752;" name="apps"/>
              <span class="list2">전체 글 보기</span>
            </div>
            <div @click="viewType(list.id)" v-for="list in boardList" :key="list.id" class="list1">
                <q-icon style="margin-bottom:5px; margin-left:30px; font-size: 1.8em; color: #249752;" name="description"/>
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
  name: 'BoardSide',
  methods: {
  },
  setup () {
    const store = useStore()
    const router = useRouter()
    const userInfo = computed(() => store.getters['module/getLoginUser'])
    const boardList = computed(() => store.getters['module/getBoardList'])
    const mvWrite = function () {
      router.push('/write')
    }
    let rows = []
    const viewAll = function () {
      store.dispatch('module/board', { })
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].registrationTime = result.data[i].registrationTime.substr(0, 16)
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
      store.commit('module/setRows', store.getters['module/getMarkList'])
      router.push('/boardMark')
    }
    const viewType = function (id) {
      store.dispatch('module/boardType', id)
        .then(function (result) {
          console.log(result.data)
          for (let i = 0; i < result.data.length; i++) {
            if (id === 2) {
              result.data[i].writer = '익명'
            }
            result.data[i].registrationTime = result.data[i].registrationTime.substr(0, 16)
            rows.push(result.data[i])
          }
          store.commit('module/setRows', rows)
          rows = []

          for (let j = 0; j < boardList.value.length; j++) {
            if (id === boardList.value[j].id) {
              if (id === 0 || id === 1) {
                store.commit('module/setBoardType', { name: boardList.value[j].type, intro: boardList.value[j].type + '입니다.' })
                break
              } else if (id === 2 || id === 3 || id === 4 || id === 5) {
                store.commit('module/setBoardType', { name: boardList.value[j].type, intro: boardList.value[j].type + ' 게시판입니다.' })
                break
              }
            }
          }
          const pn = Math.ceil(rows.length / 8)
          store.commit('module/setPageNumber', pn)
          router.push('/boardType')
        })
    }
    const viewMe = function () {
      store.dispatch('module/boardMe', store.getters['module/getLoginUser'].id)
        .then(function (result) {
          for (let i = 0; i < result.data.length; i++) {
            result.data[i].registrationTime = result.data[i].registrationTime.substr(0, 16)
          }
          store.commit('module/setRows', result.data)
          router.push('/boardMe')
        })
    }
    return {
      outlinedAllInbox,
      boardList,
      mvWrite,
      viewAll,
      viewMark,
      viewType,
      viewMe,
      userInfo
    }
  }
})
</script>

<style scoped>
#mainSide{
  width: 280px;
  height: 908px;
  border-right:1px solid rgb(212, 212, 212);
  position: absolute;
  top:60px;
  left:60px;
}
#listTop{
    width:260px;
    height:360px;
    margin:10px auto;
    border-bottom:1px solid rgb(216, 210, 210);
    border-radius: 5px;
}
.list1{
    width:250px;
    margin-top:5px;
    margin-left:0px;
}
.list1:hover{
    background-color:rgb(223, 241, 231);
    cursor: pointer;
}
.list2{
    margin-left:17px;
    font-size:18px;
}
#listBot{
    width:80%;
    height:300px;
    margin:10px auto;
}
.userInfo {
  width: 120px;
  height: 130px;
  text-align: center;
  line-height: 30px;
  font-size: 14px;
  margin-top: 25px;
  float: left;
}
#mid1{
  width: 120px;;
  height:150px;
  margin-left: 20px;
  margin-top: 5px;
  float: left;
}
.name{
  display:inline-block;
  font-weight:bold;
  margin-left: 20px;
  margin-top: 20px;
  font-size:18px;
  width: 150px;
  height: 30px;
}
.mylist2 {
  font-size: 18px;
  margin-left: 10px;
  display:inline-block;
  padding-top:8px;
}
.mylist1 {
  width: 200px;
  height: 40px;
  float: left;
  margin-left: 50px;
  padding-left: 10px;
}
.mylist1:hover{
  background-color:rgb(223, 241, 231);
  cursor: pointer;
}
</style>
