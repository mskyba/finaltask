package main.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
@Data
public class ResultDto {

    private long artistId;
    private long collectionId;

@JsonProperty("kind")
private String musicVideo;
    @JsonProperty("wrapperType")
    private String media;


}

