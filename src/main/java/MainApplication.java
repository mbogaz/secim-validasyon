import dto.oyveotesi.*;
import service.HttpClient;
import service.OyVeOtesiClient;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class MainApplication {
    public static OyVeOtesiClient oyVeOtesiClient = new OyVeOtesiClient();
    public static HttpClient httpClient = new HttpClient();

    public static void main(String[] args) throws IOException {
        //httpClient.PostRequest("https://sts.chp.org.tr/Default.aspx", 2, 34, 964, 3822965);
        mainFlow();
    }

    public static void mainFlow() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<City> cities = oyVeOtesiClient.getCities();
        cities.forEach(System.out::println);
        System.out.print("il seçiniz (id) :");
        int cityId = scanner.nextInt();

        List<District> districts = oyVeOtesiClient.getDistricts(cityId);
        districts.forEach(System.out::println);
        System.out.print("ilçe seçiniz (id) :");
        int districtId = scanner.nextInt();

        List<Neighborhood> neighborhoods = oyVeOtesiClient.getNeighborhoods(cityId, districtId);
        neighborhoods.forEach(System.out::println);
        System.out.print("mahalle seçiniz (id) :");
        int neighborhoodId = scanner.nextInt();

        List<School> schools = oyVeOtesiClient.getSchools(cityId, districtId, neighborhoodId);
        schools.forEach(System.out::println);
        System.out.print("okul seçiniz (id) :");
        int schoolId = scanner.nextInt();

        List<Submission> submissions = oyVeOtesiClient.getSubmission(schoolId);
        submissions.forEach(System.out::println);

        System.out.println("---- ÖZET -----");
        int kkTotal = submissions.stream().mapToInt(submission -> submission.getCmResult().getVotes().getKk()).sum();
        int tayyipTotal = submissions.stream().mapToInt(submission -> submission.getCmResult().getVotes().getTayyip()).sum();
        System.out.println("okuldaki toplam kk oyu : " + kkTotal + " , toplam rte oyu : " + tayyipTotal);

        int chpTotal = submissions.stream().mapToInt(submission -> {
            if(submission.getMvResult() == null)
                return 0;
            else
                return submission.getMvResult().getVotes().getChp();
        }).sum();
        int akpTotal = submissions.stream().mapToInt(submission -> {
            if(submission.getMvResult() == null)
                return 0;
            else
                return submission.getMvResult().getVotes().getAkp();
        }).sum();
        int iyipTotal = submissions.stream().mapToInt(submission -> {
            if(submission.getMvResult() == null)
                return 0;
            else
                return submission.getMvResult().getVotes().getIyip();
        }).sum();
        System.out.println("okuldaki toplam akp oyu : " + akpTotal + " , toplam chp oyu : " + chpTotal + " , toplam iyip oyu : " + iyipTotal);


    }
}
