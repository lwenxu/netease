var app=new Vue({
   el:'#app',
    data:{
       musics:{}
    },
    methods:{
       searchMusicByName:function () {
           this.$http.get("/music/search/name/红玫瑰/limit/5/type/1/offset/0").then(function(response){
               this.musics=response.body.data;
                console.log(this.musics)
           },function (response) {
                console.log("请求失败！")
           });
       }
    }
});