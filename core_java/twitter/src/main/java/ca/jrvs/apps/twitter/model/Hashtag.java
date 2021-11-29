package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "indices"
})
public class Hashtag {
    @JsonProperty("text")
    private String text;
    @JsonProperty("indices")
    private Integer[] indices; 
    
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("indices")
    public Integer[] getIndices() {
        return indices;
    }
}
