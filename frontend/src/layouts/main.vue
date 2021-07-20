<template>
    <div id="header">
        <div id="headerLeft">
            <q-img src="~assets/logo.png" style="width:150px; margin-top:-43px;"/>
        </div>
        <div id="headerRight">
            <q-img src="~assets/user.png" style="width:40px; margin-top:10px; margin-right:35px; float:right;"/>
            <q-img src="~assets/bell.png" style="width:30px; margin-top:15px; margin-right:5px; float:right;"/>
        </div>
    </div>
    <div id="content">
        <div id="side">
        </div>
        <div id="main">
            <div id="topLeft">

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

export default defineComponent({
  name: 'login'
})
</script>

<style scoped>
#header{
    width:100%;
    height:60px;
    border-bottom:0.5px solid rgb(212, 212, 212);
}
#headerLeft{
    width:16%;
    height:60px;
    float:left;
    text-align: center;
    font-size:35px;
    border-right:0.5px solid rgb(212, 212, 212);
}
#headerRight{
    width:84%;
    height:60px;
    float:right;
}
#content{
    width:100%;
    height: 680px;
}
#side{
    width:16%;
    height:692px;
    float:left;
    border-right:0.5px solid rgb(212, 212, 212);
    border-bottom:0.5px solid rgb(212, 212, 212);
}
#main{
    width:84%;
    height:692px;
    float:right;
    border-bottom:0.5px solid rgb(212, 212, 212);
    background-color:whitesmoke;
}
#topLeft{
    width:450px;
    height:200px;
    border: 1px solid rgb(212, 212, 212);
    display:inline-block;
    position:relative;
    top:30px;
    left:50px;
    background-color:rgb(250, 250, 110);
}
#topRight{
    width:700px;
    height:200px;
    border: 1px solid black;
    display:inline-block;
    position:relative;
    top:30px;
    left:100px;
}
#botLeft{
    width:580px;
    height:400px;
    border:1px solid black;
    display:inline-block;
    position:relative;
    top:60px;
    left:50px;
}
#botRight{
    width:580px;
    height:400px;
    border:1px solid black;
    display:inline-block;
    position:relative;
    top:60px;
    left:90px;
}
</style>
