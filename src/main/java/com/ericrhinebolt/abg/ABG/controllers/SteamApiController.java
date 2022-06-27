package com.ericrhinebolt.abg.ABG.controllers;

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

//    Mapping to get Player Ids from Steam API. Was used for testing, not used in application. Requires steamkey that has been removed from app for security.
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

//    Mapping to get game info from Steam API
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

//    Mapping to get game news from Steam API
    @RequestMapping(value = "/steamapi/news/{appId}", method = RequestMethod.GET)
    @ResponseBody
    public String gameNews(@PathVariable String appId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.steampowered.com")
                .addPathSegment("ISteamNews")
                .addPathSegment("GetNewsForApp")
                .addPathSegment("v0002")
                .addQueryParameter("appid", appId)
                .addQueryParameter("count", "1")
                .addQueryParameter("maxlength", "300")
                .addQueryParameter("format", "json")
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
