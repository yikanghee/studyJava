package No_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class jsoup3 {
    public static void main(String[] args) throws IOException {

        jsoup1 jsoup1 = new jsoup1();

        List<String> codeList = jsoup1.getcode();
        codeList.remove(0);

        System.out.println("codeList = " + codeList);

        for (String i : codeList) {

            String a = i.substring(30);
            System.out.println("a = " + a);

            Document doc = Jsoup.connect("https://movie.naver.com/movie/bi/mi/basic.naver?code=" + a).get();

            String title = doc.select("#content > div.article > div.mv_info_area > div.mv_info > h3 > a:nth-child(1)").text();
            String contents = doc.select("#content > div.article > div.section_group.section_group_frst > div:nth-child(1) > div > div.story_area > p").text();
            String imgUrl = doc.select("#content > div.article > div.mv_info_area > div.poster > a > img").attr("src");

            System.out.println("title = " + title);
            System.out.println("imgUrl = " + imgUrl);
            System.out.println(contents);

        }



    }
}
