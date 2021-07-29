<template>
    <Header></Header>
    <div id="content">
        <Side></Side>
        <div id="main">
            <div id="topLeft">
            근태관리 페이지
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
import { defineComponent } from 'vue'
import Header from '../components/header.vue'
import Side from '../components/side.vue'

export default defineComponent({
  name: 'management',
  components: {
    Header,
    Side
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
