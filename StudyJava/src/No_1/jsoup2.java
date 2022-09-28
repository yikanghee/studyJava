package No_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class jsoup2 {

    public static void main(String[] args) throws IOException {

        jsoup1 jsoup1 = new jsoup1();

        List<String> codeList = jsoup1.getcode();
        codeList.remove(0);
        
        System.out.println("codeList = " + codeList);

        for (String i : codeList) {

            String a = i;
            System.out.println("https://movie.naver.com/movie/bi/mi/basic.naver?code=" + a.substring(30));

            Document doc = Jsoup.connect("https://movie.naver.com/movie/bi/mi/basic.naver?code=201641").get();

            System.out.println("doc = " + doc);
            Elements elem = doc.select("#content > div.article");

        }


    }

}
