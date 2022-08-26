package tests.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import main.dto.ResultDto;
import main.dto.SearchResultDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.stream.Collectors;

public class BaseApiTest {

    @Test
    public void getResultsTest() throws JsonProcessingException {
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(Map.of("term", "Ariana Grande",
                        "country", "US",
                        "media", "audiobook"))
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
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(Map.of("term", "Jack Johnson",
                        "entity", "musicVideo"))
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
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(Map.of("term", "yelp",
                        "country", "CA",
                        "entity", "software"))
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
        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .baseUri("https://itunes.apple.com")
                .params(Map.of("term", "Nirvana",
                        "country", "US",
                        "media", "audiobook"))
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