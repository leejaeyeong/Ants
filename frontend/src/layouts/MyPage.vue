<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="topLeft">
            마이페이지
            <q-btn @click="getprofile"></q-btn>
            </div>
            <div id="topRight">
            </div>
            <div id="botLeft">
            </div>
            <div id="botRight">
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, defineComponent } from 'vue'
import { useStore } from 'vuex'
import Header from '../components/header.vue'
import Side from '../components/side.vue'

export default defineComponent({
  name: 'mypage',
  components: {
    Header,
    Side
  },
  setup () {
    const state = reactive({
      form: {
        id: '',
        password: '',
        name: '',
        position: ''
      }
    })
    const store = useStore()
    const getprofile = function () {
      store.dispatch('module/requestInfo')
        .then(response => {
          console.log(response, '리스폰스')
          const userInfo = {
            id: response.data.id,
            name: response.data.name
          }
          console.log(userInfo)
          console.log('겟멤버인포')
        }).catch(err => {
          console.log(err, '에러입니다')
        })
        .catch(function () {
          alert('아이디, 비밀번호를 확인해주세요 !')
        })
    }
    return { state, getprofile }
  }
})
</script>

<style scoped>
#content{
    width:100%;
    height: 680px;
}
#main{
    width:95%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:whitesmoke;
}
#topLeft{
    width:35%;
    height:200px;
    border: 1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:30px;
    left:50px;
    background-color:rgb(250, 250, 110);
}
#topRight{
    width:54%;
    height:200px;
    border: 1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:30px;
    left:100px;
}
#botLeft{
    width:45%;
    height:400px;
    border:1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:60px;
    left:50px;
}
#botRight{
    width:45%;
    height:400px;
    border:1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:60px;
    left:90px;
}
</style>
