<template>
    <div id="header">
        <div id="headerLeft">
            <q-img src="~assets/logo.png" style="width:150px; margin-top:-43px;"/>
        </div>
        <div id="headerRight">
            <q-img src="~assets/user.png" style="width:40px; margin-top:10px; margin-right:45px; float:right;"/>
            <q-img src="~assets/bell.png" style="width:30px; margin-top:15px; margin-right:5px; float:right;"/>
            <span style="font-weight:bold; float:right; margin-right:8px; line-height:60px; font-size:15px;">{{ state.id }} 님</span>
        </div>
    </div>
</template>

<script>
import { reactive, defineComponent } from 'vue'

export default defineComponent({
  setup () {
    const state = reactive({
      id: localStorage.getItem('id')
    })
    return {
      state
    }
  }
})
</script>

<style scoped>
#header{
    width:100%;
    height:60px;
    border-bottom:0.5px solid rgb(212, 212, 212);
}
#headerLeft{
    width:12%;
    height:60px;
    float:left;
    text-align: center;
    font-size:35px;
    /* border-right:0.5px solid rgb(212, 212, 212); */
}
#headerRight{
    width:88%;
    height:60px;
    float:right;
}
</style>
