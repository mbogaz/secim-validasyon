import dto.oyveotesi.*;
import service.HttpClient;
import service.OyVeOtesiClient;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class MainApplication {
    public static OyVeOtesiClient oyVeOtesiClient = new OyVeOtesiClient();
    public static HttpClient httpClient = new HttpClient();

    public static void main(String[] args) throws IOException {
        //httpClient.PostRequest("https://sts.chp.org.tr/Default.aspx", 2, 34, 964, 3822965);
        //mainFlow();
        secondFlow();
    }

    public static void secondFlow() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<City> cities = oyVeOtesiClient.getCities();
        cities.forEach(System.out::println);
        System.out.print("il seçiniz (id) :");
        int cityId = scanner.nextInt();

        AtomicInteger tayyipTotal = new AtomicInteger();
        AtomicInteger kkTotal = new AtomicInteger();
        AtomicInteger inceTotal = new AtomicInteger();
        AtomicInteger soganTotal = new AtomicInteger();
        AtomicInteger totalSayilanSandik = new AtomicInteger();
        List<District> districts = oyVeOtesiClient.getDistricts(cityId);
        districts.forEach(district -> {
            List<Neighborhood> neighborhoods = oyVeOtesiClient.getNeighborhoods(cityId, district.getId());
            if(neighborhoods != null) {
                neighborhoods.forEach(neighborhood -> {
                    List<School> schools = oyVeOtesiClient.getSchools(cityId, district.getId(), neighborhood.getId());
                    if(schools != null) {
                        schools.forEach(school -> {
                            List<Submission> submissions = oyVeOtesiClient.getSubmission(school.getId());
                            if(submissions != null) {
                                submissions.forEach(submission -> {
                                    if(submission.getCmResult() != null) {
                                        tayyipTotal.addAndGet(submission.getCmResult().getVotes().getTayyip());
                                        kkTotal.addAndGet(submission.getCmResult().getVotes().getKk());
                                        inceTotal.addAndGet(submission.getCmResult().getVotes().getMuharrem());
                                        soganTotal.addAndGet(submission.getCmResult().getVotes().getSogan());
                                        totalSayilanSandik.addAndGet(1);
                                    }
                                });
                                System.out.println(school.getName() + " okulu sayımı tamam");
                            } else {
                                System.out.println(school.getName() + " okulu bilgileri gelmedi");
                            }
                        });
                    } else {
                        System.out.println(district.getName() + "," + neighborhood.getName() + " mahallesindeki okulların bilgileri gelmedi");
                    }
                    System.out.println(neighborhood.getName() + " mahallesi sayımı tamam");
                });
            } else {
                System.out.println(district.getName() + " ilçesinin mahalleleri gelmedi");
            }

            System.out.println(district.getName() + " ilçesi sayımı tamam");
        });

        System.out.println("total sayılan sandık : " + totalSayilanSandik.get());
        System.out.println("kk : " + kkTotal.get());
        System.out.println("rte : " + tayyipTotal.get());
        System.out.println("sogan : " + soganTotal.get());
        System.out.println("ince : " + inceTotal.get());
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
        int kkTotal = submissions.stream().mapToInt(submission -> {
            if(submission.getCmResult() == null)
                return 0;
            else
                return submission.getCmResult().getVotes().getKk();
        }).sum();
        int tayyipTotal = submissions.stream().mapToInt(submission -> {
            if(submission.getCmResult() == null)
                return 0;
            else
                return submission.getCmResult().getVotes().getTayyip();
        }).sum();
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
