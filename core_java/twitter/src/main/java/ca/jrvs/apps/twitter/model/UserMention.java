package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "indices",
    "screen_name",
    "id",
    "id_str"
})
public class UserMention {
    @JsonProperty("name")
    private String name;
    @JsonProperty("indices")
    private Integer[] indices;
    @JsonProperty("screen_name")
    private String screen_name;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("id_str")
    private String id_str;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("indices")
    public Integer[] getIndices() {
        return indices;
    }

    @JsonProperty("screen_name")
    public String getScreenName() {
        return screen_name;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id_str")
    public String getIdStr() {
        return id_str;
    }
}
