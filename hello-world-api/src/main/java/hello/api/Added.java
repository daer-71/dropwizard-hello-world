package hello.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Added {

    private Integer result;

    public Added() {
        // Jackson deserialization
    }

    public Added(Integer result) {
        this.result = result;
    }

    @JsonProperty
    public Integer getResult() {
        return result;
    }
}
