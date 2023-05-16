package dto.oyveotesi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CmVotes {
    @JsonProperty("1")
    private int tayyip;
    @JsonProperty("2")
    private int muharrem;
    @JsonProperty("3")
    private int kk;
    @JsonProperty("4")
    private int sogan;
}
