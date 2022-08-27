package tests.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import main.dto.ResultDto;
import main.dto.SearchResultDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.put;

public class BaseApiTest {


    @Test
    public void getResultsTest() throws JsonProcessingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("term", "Ariana Grande");
        map.put("country", "US");
        map.put("media", "audiobook");
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(map)
                .get("/search")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();

        SearchResultDto searchResultDto = new ObjectMapper().readValue(jsonPath.prettyPrint(), SearchResultDto.class);
        Assert.assertTrue(searchResultDto.getResults().stream().allMatch(result -> result.getMedia().equals("audiobook")));


    }

    @Test
    public void jjMusicVideos() throws JsonProcessingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("term", "Jack Johnson");
        map.put("entity", "musicVideo");
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(map)
                .get("/search")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();


        SearchResultDto searchResultDto = new ObjectMapper().readValue(jsonPath.prettyPrint(), SearchResultDto.class);
        Assert.assertTrue(searchResultDto.getResults().stream().allMatch(result -> result.getSoftware().equals("music-video")));


    }

    @Test
    public void iTunesCanadaSoftwareResponse() throws JsonProcessingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("term", "yelp");
        map.put("country", "CA");
        map.put("entity", "software");
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(map)
                .get("/search")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();


        SearchResultDto searchResultDto = new ObjectMapper().readValue(jsonPath.prettyPrint(), SearchResultDto.class);
        Assert.assertTrue(searchResultDto.getResults().stream().allMatch(result -> result.getSoftware().equals("software")));


    }

    @Test
    public void checkingItemsCounter() throws JsonProcessingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("term", "Ariana Grande");
        map.put("country", "US");
        map.put("entity", "software");
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(map)
                .get("/search")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();


        SearchResultDto searchResultDto = new ObjectMapper().readValue(jsonPath.prettyPrint(), SearchResultDto.class);
        Assert.assertTrue(searchResultDto.getResults().size() == searchResultDto.count);

    }
}