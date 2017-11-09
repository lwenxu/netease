package com.lwen.netease.service;


import com.lwen.netease.dao.MusicDao;
import com.lwen.netease.entity.Music;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MusicService {

    @Autowired
    private MusicDao musicDao;

    public String searchByMusicName(String name, String limit, String type, String offset) throws IOException {
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
        JSONObject O= (JSONObject) JSONValue.parse(jsonStr);
        // JSONArray A=(JSONArray) JSONValue.parse(jsonStr);
        // JSONArray.
        // System.out.println(A);
        //把json 转成 music 对象
        Music music=parseJsonToMusic(O);



        return jsonStr;
    }


    /**
     * 把 json 转成 music 对象
     * @param O
     * @return
     */
    private Music parseJsonToMusic(JSONObject O){
        Music music = new Music();
        Long id = Long.parseLong(((((JSONObject)((JSONObject)O.get("result")).get("songs")).get("id")).toString()));
        //TODO::重要  最后从 album 取出来的
        // String dfsId="";
        // String name = getJsonValue("name", getJsonValue("songs", getJsonValue("result", O))).toString();
        // String url = getSongUrl(id + "", dfsId);
        // String singer="";
        //
        //
        // music.setmId(id);
        // music.setName(name);
        // music.setDfsId(dfsId);
        // music.setUrl(url);
        return music;
    }

    /**
     * 获取歌曲的 url
     * @param id
     * @param dfsId
     * @return
     */
    private String getSongUrl(String id,String dfsId) {
        return "http://m1.music.126.net/" + encryptId(id) + "/" + dfsId + ".mp3";
    }

    /**
     * id 加密算法
     * @param id
     * @return
     */
    private String encryptId(String id) {
        byte[] codes = "3go8&$8*3*3h0k(2)2".getBytes();
        byte[] songId = id.getBytes();
        for (int i = 0; i < songId.length; i++) {
            songId[i] = (byte) (songId[i] ^ codes[i % codes.length]);
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(songId);
        String result = digest.digest().toString();
        result.replace('/', '_');
        result.replace('+', '-');
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


    public String getSongDetailById(String id) {
        String url = "http://music.163.com/api/song/detail/?id=" + id + "&ids=%5B" + id + "%5D";
        Connection connection = Jsoup.connect(url);
        connection.cookie("appver", "2.0.2");
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            System.out.println("获取歌曲信息失败，GET 请求失败！");
        }
        return document != null ? document.body().html() : null;
    }

    public String getAlbumById(String id) {
        String url = "http://music.163.com/api/album/" + id;
        Connection connection = Jsoup.connect(url);
        connection.cookie("appver", "2.0.2");
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            System.out.println("获取专辑失败，GET 请求失败！");
        }
        return document != null ? document.toString() : null;
    }
}
