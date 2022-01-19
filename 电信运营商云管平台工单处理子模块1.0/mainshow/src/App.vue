<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    window.onbeforeunload = e => {
      let message = 'some word';
      e = e || window.event;
      if (e) {
        e.returnValue = message;
        if (sessionStorage.getItem("type")==="admin"){
          this.$axios.get(this.$store.state.url+"/login/adminLogout?work_num=root").then((res)=>{
            if (res.data){

            }

          })
        }
        else {
          this.$axios.get(this.$store.state.url+"/login/userLogout?work_num="+sessionStorage.getItem("work_num")).then((res)=>{
            if (res.data){

            }

          })
        }
      }
      return message;

    };
  },
}
</script>

<style>
#app {
  /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
  /*-webkit-font-smoothing: antialiased;*/
  /*-moz-osx-font-smoothing: grayscale;*/
  /*text-align: center;*/
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
}
</style>
