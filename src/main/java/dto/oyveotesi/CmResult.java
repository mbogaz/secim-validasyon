package dto.oyveotesi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CmResult {
    @JsonProperty("image_url")
    private String imgUrl;
    @JsonProperty("submission_id")
    private int submissionId;
    @JsonProperty("total_vote")
    private int totalVote;
    @JsonProperty("votes")
    private CmVotes votes;
}
