package dto.oyveotesi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Submission {
    @JsonProperty("school_name")
    private String schoolName;
    @JsonProperty("ballot_box_number")
    private int ballotBoxNumber;
    @JsonProperty("cm_result")
    private CmResult cmResult;
    @JsonProperty("mv_result")
    private MvResult mvResult;
}
