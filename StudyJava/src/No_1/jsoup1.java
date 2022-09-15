package No_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class jsoup1 {

    public static void main(String[] args) throws IOException {
        String url = "https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=영화";

        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("#morColl > div.coll_cont > div > ol").select("li");

        for (Element elem : elements) {

                String title = elem.select("div[class=info_tit] a.tit_main").text();
                String rating = elem.select("em[class=rate]").text();
                String grade = elem.select("div[class=wrap_thumb] a.thumb").text();
                String imageUrl = elem.select("div[class=wrap_thumb] img.thumb_img").attr("src");
                String code = elem.select("dd[class=review] a").attr("href").substring(44);

                System.out.println(title);
                System.out.println(rating);
                System.out.println(grade);
                System.out.println("imageUrl = " + imageUrl);
                System.out.println("code = " + code);

            String kk = elem.select("div[class=wrap_thumb] a.thumb").text();
            kk = kk.substring(0, kk.length() - 1);
            int a = Integer.parseInt(kk);

            if (a == 15) {
                break;
            }
        }

    }




}
