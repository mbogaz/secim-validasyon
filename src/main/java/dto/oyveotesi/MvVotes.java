package dto.oyveotesi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MvVotes {
    @JsonProperty("1")
    private int milletPartisi;
    @JsonProperty("2")
    private int hakVeOzgurluklerPartisi;
    @JsonProperty("3")
    private int tkp;
    @JsonProperty("4")
    private int turkiyeKoministHareketi;
    @JsonProperty("5")
    private int solParti;
    @JsonProperty("6")
    private int gencParti;
    @JsonProperty("7")
    private int memleketPartisi;
    @JsonProperty("8")
    private int bbp;
    @JsonProperty("9")
    private int akp;
    @JsonProperty("10")
    private int yenidenRefahPartisi;
    @JsonProperty("11")
    private int mhp;
    @JsonProperty("12")
    private int ysp;
    @JsonProperty("13")
    private int tip;
    @JsonProperty("14")
    private int adaletBirlikPartisi;
    @JsonProperty("15")
    private int anavatanPartisi;
    @JsonProperty("16")
    private int yenilikPartisi;
    @JsonProperty("17")
    private int halkinKurtulusuPartisi;
    @JsonProperty("18")
    private int milliYoLPartisi;
    @JsonProperty("19")
    private int vatanPartisi;
    @JsonProperty("20")
    private int gucBirligiPartisi;
    @JsonProperty("21")
    private int chp;
    @JsonProperty("22")
    private int iyip;
    @JsonProperty("23")
    private int adaletPartisi;
    @JsonProperty("24")
    private int zaferPartisi;
}
