package main.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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

    @JsonProperty("wrapperType")
    private String media;


    @JsonProperty("kind")
    private String software;


}

