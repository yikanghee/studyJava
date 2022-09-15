package No_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class jsoup2 {

    public static void main(String[] args) throws IOException {


        Integer[] arr = {147615, 155115, 142869};

        for (int i : arr) {
            String url = "https://movie.daum.net/moviedb/main?movieId=" + i;

            Document doc = Jsoup.connect(url).get();

            Elements image2 = doc.select("#mainContent > div > div.box_basic > div.info_poster > div > span");
//            String image = image2.attr("style").substring(21);
//            image = image.substring(0, image.length() - 1);
            String image = image2.attr("style");

            System.out.println("image = " + image);
        }
    }

}
