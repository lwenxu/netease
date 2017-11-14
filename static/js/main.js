var app=new Vue({
   el:'#app',
    data:{
        musics:{},
        albums:{},
        singers:{},
        playLists:{},
        users:{},
        searchContent:"",
        searchType:1
    },
    methods:{
       searchMusic:function () {
           var reqUrl="/music/search/name/"+this.searchContent+"/limit/5/type/"+this.searchType+"/offset/0";
           // var reqUrl="/music/search/name/"+this.searchContent+"/limit/5/type/100/offset/0";
           this.$http.get(reqUrl).then(function(response){
               console.log(reqUrl)
               switch (this.searchType) {
                   case 1:
                       this.musics = response.body.data;
                       break;
                   case 10:
                       this.albums = response.body.data;
                       break;
                   case 100:
                       this.singers = response.body.data;
                       break;
                   case 1000:
                       this.playLists = response.body.data;
                       break;
                   case 1002:
                       this.users = response.body.data;
                       break;
               }

                console.log(this.users)
           },function (response) {
                console.log("请求失败！")
           });
       },
        searchSingle:function () {
            this.searchType = 1;
            this.searchMusic();
        },
        searchAlbum:function () {
            this.searchType = 10;
            this.searchMusic();
        },
        searchSinger:function () {
            this.searchType=100;
            this.searchMusic();
        },
        searchPlayList:function () {
            this.searchType = 1000;
            this.searchMusic();
        },
        searchUser:function () {
            this.searchType = 1002;
            this.searchMusic();
        }
    },
    filters:{
        formatDate:function (time) {
            return new Date(parseInt(time)).toLocaleString().replace(/(\d{4}\/\d{1,2}\/\d{1,2})/,'$1');
        }
    }
});


function formatDate (date, fmt) {
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    var o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    }
    for (var k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            var str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str))
        }
    }
    return fmt
}

function padLeftZero (str) {
    return ('00' + str).substr(str.length)
}