package dto.oyveotesi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MvResult {
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("submission_id")
    private int submissionId;
    @JsonProperty("total_vote")
    private int totalVote;
    @JsonProperty("votes")
    private MvVotes votes;
}
