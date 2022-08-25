package main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Data
public  class SearchResultDto {

    @JsonProperty("resultCount")
    public int count;

    private List<ResultDto> results;

}
