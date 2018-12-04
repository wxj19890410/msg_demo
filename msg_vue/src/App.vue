<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
name: 'App',
data () {
	return {
		routerName:'',
	}
},
mounted(){
	$("#app").width($(window).width())
	$("#app").height($(window).height())
},

watch:{
	$route(to,from){
	  console.log("bianle");
	}
},
}
</script>

<style>
body{padding:0;margin:0;overflow: hidden;font-size: 14px;background-color: #D8DBE3;}
div{box-sizing: border-box;}

body .el-table th.gutter{
    display: table-cell!important;
}
.component-page{height:300px;width:400px;box-shadow: 0px 0px 10px rgba(0,0,0,0.5); position:absolute;top:80px;left:150px;border-radius: 10px;background-color: #fff;opacity: 0;padding:50px;overflow-y: scroll;}
.component-page::-webkit-scrollbar {
        display: none;
    }


</style>
