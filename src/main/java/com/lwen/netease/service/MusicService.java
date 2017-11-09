package com.lwen.netease.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MusicService {

    public String searchByMusicName(String name,String limit,String type,String offset) throws IOException {
        //data info
        Map<String, String> data = new HashMap<>();
        data.put("s", name);
        data.put("limit", limit);
        data.put("type", type);
        data.put("offset", offset);

//        System.out.println(new String(name.getBytes("ISO-8859-1"),"UTF-8"));
//        System.out.println(data);

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
        return document.body().html();
    }

    /**
     * 非法请求    我也不知道怎么了
     * @param artist_id
     * @param offset
     * @param limit
     * @return
     */
    public String listBySinger(long artist_id,int offset,int limit) {
        String url = "http://music.163.com/api/artist/albums/"+artist_id+"?offset="+offset+"&limit="+limit;
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
        assert document != null;
        return document.body().html();
    }


}
