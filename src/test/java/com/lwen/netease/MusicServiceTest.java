package com.lwen.netease;

import com.lwen.netease.service.MusicService;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class MusicServiceTest {
    private  MusicService service = new MusicService();

    @Test
    public void dfsIdTest() {
        MusicService service = new MusicService();
        //album json
        // System.out.println(JSONValue.parse(service.getAlbumById("34744528")));
        Object o = JSONValue.parse(service.getAlbumById("34744528"));
        // JSONObject albumJson = (JSONObject) o;
        // service.getDfsId("65533", albumJson);
    }

    @Test
    public void urlTest() throws UnsupportedEncodingException {
        System.out.println(service.getSongUrl("6020925674045702","6020925674045702"));;
    }
}
