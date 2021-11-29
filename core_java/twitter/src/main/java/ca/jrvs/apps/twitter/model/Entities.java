package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})
public class Entities {
    @JsonProperty("hashtags")
    private Hashtag[] hashtags;
    @JsonProperty("user_mentions")
    private UserMention[] user_mentions;

    @JsonProperty("hashtags")
    public Hashtag[] getHashtags() {
        return hashtags;
    }

    @JsonProperty("user_mentions")
    public UserMention[] getUserMentions() {
        return user_mentions;
    }
}
