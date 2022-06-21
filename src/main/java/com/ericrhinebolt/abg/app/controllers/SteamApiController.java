package com.ericrhinebolt.abg.app.controllers;

import okhttp3.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SteamApiController {
    public final String steamKey = "";

    @RequestMapping(value ="/steamapi/Player", method = RequestMethod.GET)
    @ResponseBody
    public String steamapi() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.steampowered.com")
                .addPathSegment("ISteamUser")
                .addPathSegment("GetPlayerSummaries")
                .addPathSegment("v0002")
                .addQueryParameter("key", steamKey)
                .addQueryParameter("steamids", "76561198413261295")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("steamKey", "")
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    @RequestMapping(value ="/steamapi/{appId}", method = RequestMethod.GET)
    @ResponseBody
    public String gameInfo(@PathVariable String appId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("store.steampowered.com")
                .addPathSegment("api")
                .addPathSegment("appdetails")
                .addQueryParameter("appids", appId)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return response.body().string();
    }

    public SteamApiController() throws IOException {
    }
}
