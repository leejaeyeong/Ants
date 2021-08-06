<template>
  <div id="mainSide">
      <div style="margin-top:30px; width:70%; height:37px; margin-left:37px; border-radius:5px; font-weight:bold; text-align:center; padding-top:7px; font-size:18px; color: white; background-color: #18C75E;">SSAFY 사무국</div>
      <div id="listTop">
          <div class="list1">
              <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #18C75E;" name="list"/>
              <span class="list2">그룹원 목록</span>
          </div>
          <div class="list1">
              <q-icon style="margin-bottom:5px; margin-left:15px; font-size: 2em; color: #18C75E;" name="send"/>
              <span class="list2">그룹원 초대</span>
          </div>
      </div>
  </div>
  <div id="mainContent">
      <div>
          <q-input filled color="teal" style="height:40px; display:inline-block; width:15%; margin-top:20px; margin-left:40px;" label="이름" />
          <q-btn round style="margin-top:-20px; margin-left:10px; background-color: #18C75E; color:white; height:50px; width:50px;" icon="search" />
      </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'downloads',
  components: {
  },
  setup () {
    const options = [
      'test', 'test', 'tst', 'sss', '부aaaa장'
    ]
    return {
      options
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
    background-color:rgb(223, 241, 231);
    cursor: pointer;
}
.list2{
    margin-left:15px;
}
#mainContent{
    width: 1130px;
    height: 690px;
    position: absolute;
    top:60px;
    left:325px;
}
#mainList{
    width:970px;
    height:490px;
    position:absolute;
    top:120px;
    left:80px;
    border:1px solid #18C75E;
}
#top{
    width:100%;
    height:50px;
    border-bottom:1px solid #18C75E;
    background-color: #18C75E;
    line-height:50px;
}
#bot{
    width:100%;
    height:440px;
    overflow-y: scroll;
}
.detail{
    width:100%;
    height:80px;
    line-height:80px;
    font-size:22px;
    border-bottom:1px solid #18C75E;
}
.profile{
    margin-left:35px;
    font-size:2.8em;
    color: #18C75E;
}
.id{
    font-weight:bold;
    margin-left:40px;
}
.span{
    margin-left:40px;
    display:inline-block;
    width:140px;
}
.phone{
    display:inline-block;
    margin-left:30px;
    width:180px;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
div{
  font-family: 'NEXON Lv1 Gothic OTF';
}
span{
  font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
