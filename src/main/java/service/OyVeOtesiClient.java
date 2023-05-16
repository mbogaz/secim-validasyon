package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.oyveotesi.*;

import java.io.IOException;
import java.util.List;

public class OyVeOtesiClient {

    public static HttpClient httpClient = new HttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public OyVeOtesiClient(){
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    public List<City> getCities() throws IOException {
        return objectMapper.readValue(httpClient.GetRequest("https://api-sonuc.oyveotesi.org/api/v1/cities"),
                new TypeReference<>() {
                });
    }

    public List<District> getDistricts(int cityId) throws IOException {
        return objectMapper.readValue(httpClient.GetRequest("https://api-sonuc.oyveotesi.org/api/v1/cities/"
                        + cityId + "/districts"),
                new TypeReference<>() {
                });
    }

    public List<Neighborhood> getNeighborhoods(int cityId, int districtId) throws IOException {
        return objectMapper.readValue(httpClient.GetRequest("https://api-sonuc.oyveotesi.org/api/v1/cities/" +
                        cityId + "/districts/" + districtId + "/neighborhoods"),
                new TypeReference<>() {
                });
    }

    public List<School> getSchools(int cityId, int districtId, int neighborhoodId) throws IOException {
        return objectMapper.readValue(httpClient.GetRequest("https://api-sonuc.oyveotesi.org/api/v1/cities/" +
                        cityId + "/districts/" + districtId + "/neighborhoods/" + neighborhoodId + "/schools"),
                new TypeReference<>() {
                });
    }

    public List<Submission> getSubmission(int schoolId) throws IOException {
        return objectMapper.readValue(httpClient.GetRequest("https://api-sonuc.oyveotesi.org/api/v1/submission/school/"
                + schoolId),
                new TypeReference<>() {
                });
    }
}
