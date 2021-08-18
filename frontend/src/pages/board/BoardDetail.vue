<template>
  <div id="allBoard">
    <div id="board">
      <div id="detail">
        <q-input v-model="detail.title" style="width:50%; font-size:20px; color:black; display:inline-block;" filled readonly/>
        <q-icon v-if="!mark" @click="markOn" class="mark" style="font-size: 4.0em; color: #249752;" name="bookmark_border"/>
        <q-icon v-if="mark" @click="markOff" class="mark" style="font-size: 4.0em; color: #249752;" name="bookmark"/>
        <div class="q-pa-md" style="max-width: 1200px; margin-left:-17px;">
          <q-input
            filled
            type="textarea"
            readonly
            v-model="detail.content"
            rows="13"
            style="font-size:20px;"
          />
        </div>
        <q-input v-model="form.comment" style="font-size:20px; width:86%; display:inline-block;" label="댓글입력란" filled/>
        <q-btn @click="regist" style="color: white; background-color: #18C75E; font-size:20px; margin-top:-20px; margin-left:10px; padding:10px;" label="등록" />
        <q-btn @click="back" style="font-size:20px; margin-top:-20px; margin-left:10px; padding:10px;" color="amber" glossy label="뒤로" />
        <div v-for="(comment, index) in comments" :key="index">
          <img :src="comment.profileLocation" style="display:inline-block;width:50px; height:50px; border-radius:25px; position:relative; top:10px;">
          <div class="comment" style="display:inline-block; line-height:50px;">
          {{ comment.userName }}님 : {{ comment.comment }} <span style="float:right; color:grey;">{{ comment.registrationTime }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed, defineComponent } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'board',
  methods: { },
  setup () {
    const store = useStore()
    const router = useRouter()
    const detail = computed(() => store.getters['module/getDetail'])
    const comments = computed(() => store.getters['module/getComments'])
    const mark = computed(() => store.getters['module/getMark'])
    const form = reactive({
      comment: '',
    })
    const back = function () {
      router.back()
    }
    const regist = function () {
      const tmp = store.getters['module/getDetail']
      const loginUser = store.getters['module/getLoginUser']
      const id = tmp.id
      const writer = loginUser.id
      store.dispatch('module/registComment', { id: id, comment: form.comment, writer: writer })
        .then(function (res) {
          console.log(res)
          store.dispatch('module/boardDetail', tmp.id)
            .then(function (result) {
              store.commit('module/setDetail', result.data)
              var tmp = []
              for (let i = 0; i<result.data.comments.length; i++) {
                result.data.comments[i].registrationTime = result.data.comments[i].registrationTime.substr(0, 16)
                tmp.push(result.data.comments[i])
              }
              store.commit('module/setComments',tmp)
              router.push('/boardDetail')
            })  
          form.comment = ''
        })
    }
    const markOn = function () {
      const tmp = store.getters['module/getDetail']
      const userId = store.getters['module/getLoginUser'].id
      store.dispatch('module/mark', tmp.id)
        .then(function () {
          store.commit('module/setMark', true)
          store.dispatch('module/markList', userId)
            .then(function (result) {
              store.commit('module/setMarkList', result.data)
              console.log(result.data,'12312312321321')
            })
        })
    }
    const markOff = function () {
      const tmp = store.getters['module/getDetail']
      const userId = store.getters['module/getLoginUser'].id
      store.dispatch('module/mark', tmp.id)
        .then(function () {
          store.commit('module/setMark', false)
          store.dispatch('module/markList', userId)
            .then(function (result) {
              store.commit('module/setMarkList', result.data)
            })
        })
    }
    return {
      detail,
      comments,
      form,
      back,
      regist,
      mark,
      markOn,
      markOff
    }
  }
})
</script>

<style scoped>
#allBoard{
  width:1580px;
  height:909px;
  background-color: rgb(242, 247, 244);
  position: absolute;
  top:60px;
}
#board{
  width: 1350px;
  height: 750px;
  position: absolute;
  top:30px;
  left:100px;
}
#detail{
    margin-top:22px;
    margin-left:40px;
}
.comment{
    border-bottom:1px solid rgb(212, 212, 212);
    width:1075px;
    height:50px;
    margin-top:10px;
    line-height:50px;
    font-size:20px;
    padding-left:10px;
}
.mark{
    cursor: pointer;
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
