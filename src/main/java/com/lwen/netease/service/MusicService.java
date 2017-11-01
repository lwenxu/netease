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

        //header info
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "appver=2.0.2");


        String url = "http://music.163.com/api/search/get/";
        //设置header cookie  data
        Connection con = Jsoup.connect(url);
        con.data(data);
        con.headers(header);

        //执行  post 请求
        Document document = con.post();
        System.out.println(document);

//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            con.da
//        }

        return "";
    }
}
