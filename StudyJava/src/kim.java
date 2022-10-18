import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;


public class kim {


    public static void main(String[] args) throws IOException {
        String URL = "https://news.sbs.co.kr/news/newsflash.do?plink=SNB&cooper&pageIdx=";

        Document doc = null;


        for (int i = 1; i <= 10; i++) {
            doc = Jsoup.connect(URL + i).get();

            //변수 설정
            String title;
            String content;
            String date;
            String author;

            //가져올 태그 입력 (w.news_list 안에 있는 ul안에 있는 li를 불러옴)
            List<Element> elements = doc.select("div.w_news_list ul > li");

            //
            for (Element element : elements) {
                System.out.println("page = " + i);
                title = "^title : " + element.getElementsByClass("sub").text();
                content = "^content : " + element.getElementsByClass("read").text();
                date = "^date : " + element.getElementsByClass("date").text();
                author = "^author : " + element.getElementsByClass("name").text();

                System.out.println(title + "\n" + content + "\n" + date + "\n" + author + "\n");
                System.out.println("--------------------------------------------------------------");
            }

        }
    }




}
