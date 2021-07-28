<template>
   <div id="mainSide">
        <q-btn @click="mvWrite" style="margin-top:30px; width:70%; margin-left:37px; color: white; background-color: #6581A6;" icon="mail" label="글쓰기" />
        <div id="listTop">
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #6581A6;" name="turned_in_not"/>
                <span class="list2">북마크한 글</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #6581A6;" name="edit"/>
                <span class="list2">내가 쓴 글</span>
            </div>
        </div>
        <div id="listBot">
            <span style="font-weight:bold; font-size:18px; margin-top:10px; margin-left:30px; display:inline-block;">사내 게시판 목록</span>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #6581A6;" name="description"/>
                <span class="list2">공지사항</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #6581A6;" name="description"/>
                <span class="list2">공통 전달 사항</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #6581A6;" name="description"/>
                <span class="list2">익명게시판</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #6581A6;" name="description"/>
                <span class="list2">개발 1팀</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #6581A6;" name="description"/>
                <span class="list2">개발 2팀</span>
            </div>
            <div class="list1">
                <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 1.8em; color: #6581A6;" name="description"/>
                <span class="list2">개발 3팀</span>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'
import { outlinedAllInbox } from '@quasar/extras/material-icons-outlined'

export default defineComponent({
  name: 'boardSide',
  methods: {
  },
  setup () {
    const router = useRouter()
    const mvWrite = function () {
      router.push('/write')
    }
    return {
      outlinedAllInbox,
      mvWrite
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
    height:85px;
    margin:10px auto;
    border-bottom:1px solid rgb(216, 210, 210);
}
.list1{
    width:80%;
    margin-top:5px;
    margin-left:20px;
}
.list1:hover{
    background-color:rgb(216, 210, 210);
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
