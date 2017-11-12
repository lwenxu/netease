package com.lwen.netease.service;


import com.lwen.netease.Enmu.ResultEnmu;
import com.lwen.netease.Expection.NetException;
import com.lwen.netease.dao.AlbumDao;
import com.lwen.netease.dao.ArtistDao;
import com.lwen.netease.dao.MusicDao;
import com.lwen.netease.entity.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MusicService {

    @Autowired
    private MusicDao musicDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ArtistDao artistDao;

    /**
     * service 层的搜索音乐
     * @param name
     * @param limit
     * @param type
     * @param offset
     * @return
     * @throws IOException
     */
    public List searchByMusicName(String name, String limit, String type, String offset) throws IOException {
        List<Music> musicList = new ArrayList<>();
        musicList = musicDao.findMusicByName(name);
        if (!musicList.isEmpty()) {
            return musicList;
        }
        //data info
        Map<String, String> data = new HashMap<>();
        data.put("s", name);
        data.put("limit", limit);
        data.put("type", type);
        data.put("offset", offset);
        String url = "http://music.163.com/api/search/get/";
        //设置header cookie  data
        Connection con = Jsoup.connect(url);
        con.data(data);
        // header info
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "appver=2.0.2");
        con.headers(header);

        //执行  post 请求
        Document document = con.post();
        String jsonStr = document == null ? "" : document.body().html();
        JSONObject O = (JSONObject) JSONValue.parse(jsonStr);

        //json对象解析   然后存放在数据库  并返回所查询的 music list
        switch (type) {
            case "1":  //单曲
                return parseJsonToMusicAndSave(O);
            case "10":  //专辑
                return parseJsonToAlbumAndSave(O);
            case "100":  //歌手
                return parseJsonToArtistAndSave(O, name);
            case "1000":  //歌单
                return parseJsonToPlayListAndSave(O);
            case "1002":  //用户
                return parseJsonToUserAndSave(O);
            default:
                throw new NetException(ResultEnmu.SEARCH_ERROR.getCode(), ResultEnmu.SEARCH_ERROR.getMsg(), null);
        }

    }

    /**
     * 把 json 转成 music 对象  并且存储
     * @param O
     * @return
     */
    @Transactional
    public List<Music> parseJsonToMusicAndSave(JSONObject O) throws UnsupportedEncodingException {
        List<Music> musicList = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) ((JSONObject) (O.get("result"))).get("songs");
        for (Object tempObject : jsonArray) {
            // //正文的 JSONObject 对象
            JSONObject jsonObjectContext = (JSONObject) tempObject;
            //album Json
            JSONObject albumJsonObject = (JSONObject) jsonObjectContext.get("album");
            //artist Json
            String artistStr = jsonObjectContext.get("artists").toString();
            JSONObject artistJsonObject = (JSONObject) JSONValue.parse(artistStr.substring(1, artistStr.length() - 1));

            //album 实体的属性
            Long albumId = Long.parseLong(albumJsonObject.get("id").toString());
            String albumName = albumJsonObject.get("name").toString();
            String albumPublishedTime = albumJsonObject.get("publishTime").toString();
            int albumSize = Integer.parseInt(albumJsonObject.get("size").toString());
            //artist 的实体属性
            Long artistId = Long.parseLong(artistJsonObject.get("id").toString());
            String artistName = artistJsonObject.get("name").toString();
            String artistUrl = artistJsonObject.get("img1v1Url").toString();

            //music 实体的属性
            Long musicId = Long.parseLong(jsonObjectContext.get("id").toString());
            String dfsId=getDfs(musicId+"",albumId+"");
            String musicName = jsonObjectContext.get("name").toString();
            String musicUrl = getSongUrl(dfsId);


            Artist artist = new Artist(artistId, artistName, artistUrl);
            Album album = new Album(albumId, albumName, artist, albumPublishedTime, albumSize);
            Music music = new Music(musicId, musicName, artist, album, dfsId, musicUrl, 0L);

            // System.out.println(artist);
            // System.out.println(album);
            // System.out.println(music);
            artistDao.saveArtist(artist);
            albumDao.saveAlbum(album);
            musicList.add(musicDao.saveMusic(music));
        }
        if (musicList.isEmpty()) {
            throw new NetException(ResultEnmu.MUSIC_CACHE_INDEX_ERROR.getCode(), ResultEnmu.MUSIC_CACHE_INDEX_ERROR.getMsg(), null);
        }
        return musicList;
    }

    /**
     * 专辑
     * @param O
     * @return
     */
    @Transactional
    public List<Album> parseJsonToAlbumAndSave(JSONObject O){
        List<Album> result = new ArrayList<>();
        return result;
    }

    /**
     * 歌手
     * @param O
     * @return
     */
    @Transactional
    public List<Artist> parseJsonToArtistAndSave(JSONObject O,String name){
        List<Artist> result = new ArrayList<>();
        result = artistDao.findByName(name);
        if (!result.isEmpty()) {
            return result;
        }

        int resultCount = Integer.parseInt(((JSONObject) O.get("result")).get("artistCount").toString());
        JSONArray artistJSON = (JSONArray) ((JSONObject) O.get("result")).get("artists");

        for (Object temp : artistJSON) {
            Long artistId = Long.parseLong(((JSONObject)temp).get("id").toString());
            String artistName = ((JSONObject)temp).get("name").toString();
            String imgUrl = ((JSONObject)temp).get("img1v1Url").toString();
            Artist artist = new Artist(artistId, artistName, imgUrl);
            artistDao.saveArtist(artist);
            result.add(artist);
        }
        if (!result.isEmpty()) {
            return result;
        }
        return result;
    }

    public String parseJson() throws IOException {
        //data info
        Map<String, String> data = new HashMap<>();
        data.put("s", "陈奕迅");
        data.put("limit", "10");
        data.put("type", "1000");
        data.put("offset", "0");
        String url = "http://music.163.com/api/search/get/";
        //设置header cookie  data
        Connection con = Jsoup.connect(url);
        con.data(data);
        // header info
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "appver=2.0.2");
        con.headers(header);

        //执行  post 请求
        Document document = con.post();
        String jsonStr = document == null ? "" : document.body().html();
        JSONObject O = (JSONObject) JSONValue.parse(jsonStr);




        List<PlayList> result = new ArrayList<>();
        // result = albumDao.findByName(name);
        // if (!result.isEmpty()) {
        //     return result;
        // }

        JSONArray listJSON = (JSONArray) ((JSONObject) O.get("result")).get("playlists");
        for (Object temp : listJSON) {
            Long listId = Long.parseLong(((JSONObject)temp).get("id").toString());
            String listName = ((JSONObject)temp).get("name").toString();
            String username = ((JSONObject)((JSONObject)temp).get("creator")).get("nickname").toString();
            String coverImgUrl = ((JSONObject)temp).get("coverImgUrl").toString();
            Long playCount=Long.parseLong(((JSONObject)temp).get("playCount").toString());
            Long trackCount=Long.parseLong(((JSONObject)temp).get("trackCount").toString());
            Long bookCount=Long.parseLong(((JSONObject)temp).get("bookCount").toString());
            Long userId=Long.parseLong(((JSONObject)temp).get("userId").toString());

            PlayList playList = new PlayList(listId, listName, username, coverImgUrl, playCount, trackCount, bookCount, userId);

            result.add(playList);
        }
        // if (!result.isEmpty()) {
        //     return result;
        // }
        return O.toString();


    }

    /**
     * 歌单
     * @param O
     * @return
     */
    @Transactional
    public List<PlayList> parseJsonToPlayListAndSave(JSONObject O){
        List<PlayList> result = new ArrayList<>();
        return result;
    }

    /**
     * 用户列表
     * @param O
     * @return
     */
    @Transactional
    public List<User> parseJsonToUserAndSave(JSONObject O){
        List<User> result = new ArrayList<>();
        return result;
    }










    /**
     * 获取歌曲的 url
     * @param dfsId
     * @return
     */
    public String getSongUrl(String dfsId) throws UnsupportedEncodingException {
        return "http://p2.music.126.net/" + encryptId(dfsId) + "/" + dfsId + ".mp3";
    }

    /**
     * id 加密算法
     * @param id
     * @return
     */
    private String encryptId(String id) throws UnsupportedEncodingException {
        byte[] codes = "3go8&$8*3*3h0k(2)2".getBytes("utf-8");
        byte[] songId = id.getBytes();
        for (int i = 0; i < songId.length; i++) {
            songId[i] = (byte) (songId[i] ^ codes[i % codes.length]);
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sun.misc.BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder();
        String result =base64Encoder.encode (digest.digest(songId));
        result=result.replace('/', '_');
        result = result.replace('+', '-');
        return result;
    }


    private String parseMusic(String result) {
        return "";
    }

    /**
     * 非法请求    我也不知道怎么了
     *
     * @param artist_id
     * @param offset
     * @param limit
     * @return
     */
    public String listBySinger(long artist_id, int offset, int limit) {
        String url = "http://music.163.com/api/artist/albums/" + artist_id + "?offset=" + offset + "&limit=" + limit;
        System.out.println(url);
        //设置header cookie  data
        Connection con = Jsoup.connect(url);
        // header info
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "appver=2.0.2");
        con.headers(header);


        //执行  get 请求
        Document document = null;
        try {
            document = con.get();
        } catch (IOException e) {
            System.out.println("get 请求失败！");
        }
        return document != null ? document.body().html() : null;
    }


    /**
     * 根据 album 获取所有的歌曲的 json 字符串
     * @param id
     * @return
     */
    public String getAlbumById(String id) {
        String url = "http://music.163.com/api/album/" + id;
        Connection connection = Jsoup.connect(url);
        connection.cookie("appver", "2.0.2");
        connection.header("Referer", "http://music.163.com");
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            System.out.println("获取专辑失败，GET 请求失败！");
        }
        return document != null ? document.body().html() : null;
    }

    /**
     * 根据 album 的 json 字符串获取音乐的 dfsId
     * @param musicId
     * @param jsonObject
     * @return
     */
    public String getDfsId(String musicId,JSONObject jsonObject) {
        String dfsId = "";
        if (jsonObject == null) {
            return "";
        }
        JSONArray songsJson=(JSONArray) ((JSONObject)jsonObject.get("album")).get("songs");
        for (Object tempO : songsJson) {
            JSONObject object = (JSONObject) tempO;
            if (object.get("id").toString().equals(musicId)) {
                if (object.get("hMusic")!=null) {
                    dfsId = ((JSONObject) object.get("hMusic")).get("dfsId").toString();
                    continue;
                }
                if (object.get("mMusic") != null) {
                    dfsId = ((JSONObject) object.get("mMusic")).get("dfsId").toString();
                    continue;
                }
                if (object.get("lMusic") != null) {
                    dfsId = ((JSONObject) object.get("lMusic")).get("dfsId").toString();
                    continue;
                }
            }
        }
        return dfsId;
    }

    public String getDfs(String musicId,String albumId) {
        JSONObject object = (JSONObject) JSONValue.parse(getAlbumById(albumId));
        return getDfsId(musicId, object);
    }


}
