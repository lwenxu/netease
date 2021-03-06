网易云音乐API分析
Bo Liu edited this page on 17 May · 4 revisions
 Pages 3
Home
NetEase cloud music analysis API [EN]
网易云音乐API分析
Clone this wiki locally


https://github.com/yanunon/NeteaseCloudMusic.wiki.git
 Clone in Desktop
1. 总览

本文档是通过对网易云音乐Android客户端(1.5.2)的部分功能进行分析得出的

在所有的交互中，均需要在cookie中添加这对键值appver=1.5.2;

(Update 2014-07-13) cookie中添加appver=2.0.2``` referer设置为http://music.163.com`
2. 搜索

POST http://music.163.com/api/search/get/

参数

s: 搜索词

limit: 返回数量

sub: 意义不明(非必须参数)；取值：false

type: 搜索类型；取值意义

1 单曲
10 专辑
100 歌手
1000 歌单
1002 用户
offset: 偏移数量，用于分页

MUSIC_U: 意义不明(非必须参数)

示例

curl -d "s=玫瑰色的你&limit=20&type=1&offset=0" -b "appver=1.5.2;" http://music.163.com/api/search/get/

结果：



3. 获取歌手专辑列表

GET `http://music.163.com/api/artist/albums/[artist_id]/`

其中artist_id用歌手id替换

参数

offset: 偏移数量，用于分页

limit: 返回数量

示例

curl -b "appver=1.5.2;" "http://music.163.com/api/artist/albums/10557?offset=0&limit=3"

结果：

{
    "hotAlbums": [
        {
            "status": 1,
            "blurPicUrl": "http://p2.music.126.net/mic-HzsY8tNY6DD1eHzdlg==/612427976714915.jpg",
            "commentThreadId": "R_AL_3_2263047",
            "description": "",
            "tags": "",
            "briefDesc": "",
            "artist": {
                "img1v1Url": "http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg",
                "name": "\u5f20\u60ac",
                "briefDesc": "",
                "albumSize": 8,
                "img1v1Id": 0,
                "alias": [
                    "\u7126\u5b89\u6ea5"
                ],
                "picId": 168225279065170,
                "picUrl": "http://p1.music.126.net/F7i_-eBbuhpBXodY5U2oTw==/168225279065170.jpg",
                "id": 10557
            },
            "companyId": 0,
            "publishTime": 1349366400007,
            "name": "\u795e\u7684\u6e38\u620f \u5de1\u6f14\u7cbe\u534e\u5b9e\u5f55",
            "alias": [],
            "picId": 612427976714915,
            "copyrightId": 0,
            "picUrl": "http://p1.music.126.net/mic-HzsY8tNY6DD1eHzdlg==/612427976714915.jpg",
            "company": "\u7d22\u5c3c\u97f3\u4e50",
            "songs": null,
            "type": null,
            "id": 2263047,
            "size": 7
        },
        ...
    ],
    "more": true,
    "code": 200,
    "artist": {
        "img1v1Url": "http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg",
        "name": "\u5f20\u60ac", ``````````````
        "briefDesc": "",
        "albumSize": 8,
        "img1v1Id": 0,
        "alias": [
            "\u7126\u5b89\u6ea5"
        ],
        "picId": 168225279065170,
        "picUrl": "http://p1.music.126.net/F7i_-eBbuhpBXodY5U2oTw==/168225279065170.jpg",
        "id": 10557
    }
}
4. 获取专辑音乐列表

GET http://music.163.com/api/album/[album_id]/
其中album_id用专辑id替换

示例

curl -b "appver=1.5.2;" "http://music.163.com/api/album/32311/"

结果：

{
    "album": {
        "alias": [
            "Games We Play"
        ],
        "artist": {
            "albumSize": 8,
            "alias": [
                "\u7126\u5b89\u6ea5"
            ],
            "briefDesc": "",
            "id": 10557,
            "img1v1Id": 0,
            "img1v1Url": "http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg",
            "name": "\u5f20\u60ac",
            "picId": 168225279065170,
            "picUrl": "http://p1.music.126.net/F7i_-eBbuhpBXodY5U2oTw==/168225279065170.jpg"
        },
        "blurPicUrl": "http://p1.music.126.net/cdoUJ2yO5LRuJhGeIsRdjQ==/46179488380332.jpg",
        "briefDesc": "",
        "commentThreadId": "R_AL_3_32311",
        "company": "\u7d22\u5c3c\u97f3\u4e50\u5a31\u4e50",
        "companyId": 0,
        "copyrightId": 0,
        "description": "2012...\n\n",
        "id": 32311,
        "info": {
            "commentCount": 1,
            "comments": null,
            "latestLikedUsers": null,
            "liked": false,
            "likedCount": 0,
            "shareCount": 2,
            "threadId": "R_AL_3_32311"
        },
        "name": "\u795e\u7684\u6e38\u620f",
        "picId": 46179488380332,
        "picUrl": "http://p1.music.126.net/cdoUJ2yO5LRuJhGeIsRdjQ==/46179488380332.jpg",
        "publishTime": 1344528000000,
        "size": 10,
        "songs": [
            {
                "album": {
                    "alias": [
                        "Games We Play"
                    ],
                    "artist": {
                        "albumSize": 0,
                        "alias": [],
                        "briefDesc": "",
                        "id": 0,
                        "img1v1Id": 0,
                        "img1v1Url": "http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg",
                        "name": "",
                        "picId": 0,
                        "picUrl": ""
                    },
                    "blurPicUrl": "http://p1.music.126.net/cdoUJ2yO5LRuJhGeIsRdjQ==/46179488380332.jpg",
                    "briefDesc": "",
                    "commentThreadId": "R_AL_3_32311",
                    "company": "\u7d22\u5c3c\u97f3\u4e50\u5a31\u4e50",
                    "companyId": 0,
                    "copyrightId": 0,
                    "description": "",
                    "id": 32311,
                    "name": "\u795e\u7684\u6e38\u620f",
                    "picId": 46179488380332,
                    "picUrl": "http://p1.music.126.net/cdoUJ2yO5LRuJhGeIsRdjQ==/46179488380332.jpg",
                    "publishTime": 1344528000000,
                    "size": 10,
                    "songs": null,
                    "status": 1,
                    "tags": "",
                    "type": ""
                },
                "alias": [],
                "artists": [
                    {
                        "albumSize": 0,
                        "alias": [],
                        "briefDesc": "",
                        "id": 10557,
                        "img1v1Id": 0,
                        "img1v1Url": "http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg",
                        "name": "\u5f20\u60ac",
                        "picId": 0,
                        "picUrl": ""
                    }
                ],
                "bMusic": {
                    "bitrate": 320000,
                    "dfsId": 1012650209189891,
                    "id": 10334056,
                    "name": "\u73ab\u7470\u8272\u7684\u4f60",
                    "playTime": 297927,
                    "size": 11930240
                },
                "commentThreadId": "R_SO_4_326695",
                "copyrightId": 0,
                "dayPlays": 0,
                "duration": 297927,
                "hMusic": {
                    "bitrate": 320000,
                    "dfsId": 1012650209189888,
                    "id": 10334053,
                    "name": "\u73ab\u7470\u8272\u7684\u4f60",
                    "playTime": 297927,
                    "size": 11930240
                },
                "hearTime": 0,
                "id": 326695,
                "lMusic": {
                    "bitrate": 96000,
                    "dfsId": 1012650209189890,
                    "id": 10334055,
                    "name": "\u73ab\u7470\u8272\u7684\u4f60",
                    "playTime": 297927,
                    "size": 3595194
                },
                "mMusic": {
                    "bitrate": 160000,
                    "dfsId": 1012650209189889,
                    "id": 10334054,
                    "name": "\u73ab\u7470\u8272\u7684\u4f60",
                    "playTime": 297927,
                    "size": 5976935
                },
                "mp3Url": "http://m1.music.126.net/uCNvR9xHLoQIj1kIRyzadQ==/1012650209189889.mp3",
                "mvid": 5102,
                "name": "\u73ab\u7470\u8272\u7684\u4f60",
                "playedNum": 0,
                "popularity": 95.0,
                "position": 1,
                "score": 95,
                "starred": false,
                "starredNum": 0,
                "status": 1
            },
            ...
        ],
        "status": 1,
        "tags": "",
        "type": ""
    },
    "code": 200
}
5. 下载音乐文件

GET http://m1.music.126.net/[encrypted_song_id]/[song_dfsId].mp3

其中song_dfsId为歌曲id，同一歌曲不同比特率有不同的id，见上结果。encrypted_song_id为song_dfsId加密后的字符串。

歌曲id加密代码

import md5

def encrypted_id(id):
    byte1 = bytearray('3go8&$8*3*3h0k(2)2')
    byte2 = bytearray(id)
    byte1_len = len(byte1)
    for i in xrange(len(byte2)):
        byte2[i] = byte2[i]^byte1[i%byte1_len]
    m = md5.new()
    m.update(byte2)
    result = m.digest().encode('base64')[:-1]
    result = result.replace('/', '_')
    result = result.replace('+', '-')
    return result