package No_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class jsoup1 {

    public List<String> getcode() throws IOException {

        String url = "https://movie.naver.com/movie/sdb/rank/rmovie.naver";

        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("#old_content > table > tbody > tr");

        int rank = -1;

        List<String> rList = new ArrayList<>();

        for (Element elem : elements) {

            rank++;

            String code = elem.select("div[class=tit3] a").attr("href");

            rList.add(code);

            if (rank == 10) {
                break;
            }
        }
        return rList;
    }

    public List<String> getTitle() throws IOException {

        String url = "https://movie.naver.com/movie/sdb/rank/rmovie.naver";

        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("#old_content > table > tbody > tr");

        int rank = -1;

        List<String> rList = new ArrayList<>();

        for (Element elem : elements) {

            rank++;

            String title = elem.select("div[class=tit3] a").text();

            rList.add(title);

            if (rank == 10) {
                break;
            }
        }
        return rList;
    }

}
