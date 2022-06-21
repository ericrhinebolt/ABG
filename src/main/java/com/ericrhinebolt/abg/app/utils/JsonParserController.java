package com.ericrhinebolt.abg.app.utils;

import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.service.GamesService;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class JsonParserController {

    @Autowired
    private GamesService gamesService;

    @PostMapping (value = "/create")
    public void createGames() throws IOException, JsonException {
        //Read locally saved JSON file from steamAPI call, get all games
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\erhin\\OneDrive\\Documents\\TEK Systems\\Case Study\\app\\src\\main\\resources\\static\\files\\steamgames.json"));

        //Parse into JSON object
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);

        //Get apps array from parent object
        JsonArray apps = (JsonArray) parser.get("apps");

        //Iterate through apps array
        apps.forEach(entry -> {
            //Get each object in the array
            JsonObject app = (JsonObject) entry;
            //Get each value per key
            BigDecimal appId = (BigDecimal) app.get("appid");
            String appName = app.get("name").toString();
            //Remove non-ASCII characters from titles (MariaDB doesn't like foreign characters)
            String formName = appName.replaceAll("[^\\x00-\\x7F]", "");
            //Change ID from BigDecimal to int
            int appid = appId.intValue();
            //Instantiate games object
            Games game = new Games();
            //Use setters to assign values
            game.setAppId(appid);
            game.setTitle(formName);
            //Save game to database
            gamesService.addGames(game);
        });

        reader.close();
    }

}