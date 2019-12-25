package business;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class BasicCrawler extends WebCrawler {

    //Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|org|swf|wma|zip|rar|gz))$");
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov" +
                                                                "|mpeg|ram|m4v|pdf|rm|smil|wmv|org|swf|wma|zip|rar|gz))$");
    //private final AtomicInteger numSeenImages;
public static int shouldvisit =0 , visitt = 0;

    /*public BasicCrawler(AtomicInteger numSeenImages) {
        this.numSeenImages = numSeenImages;
    }*/

    /*
    * Burası iki parametra alıyor. refferingPage parametresini kullanmayız.
    * WebUrl parametresi ziyaret etmek istediğimiz url adresi(http)(yani tarama yapmak istediğimiz link)
    * bu fonskiyonu şunu yapar;
    * 1)//fonsiyona gelen url linkini küçük harf  yapar.
    * 2)bu oluşturulan küçük harfli link adreslerinde 'css|js|gif|jpg|png|mp3|mp4|zip|gz' olmayan ve
    * bu link adresin 'https://www.ics.uci.edu' bu string ile başlıyorsa dönderir.
    * SONUÇ: Bu işlev, verilen URL'nin taranıp taranmayacağına karar verir.
    * */
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        shouldvisit ++;
        System.out.println("Should Visit URL: " + url); //https://www.karabuk.edu.tr/f
        return !FILTERS.matcher(href).matches() && href.contains("ilkerturker.com/");
    }
    /*
    * link işlenmeye hazır olduğunda bu fonksiyon çağrılır.
    * SONUÇ:Bu işlev, bir URL'nin içeriği başarıyla indirildikten sonra çağrılır.
    * */
    public void visit(Page page) {
        visitt++;
        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
       /* System.out.println("get weburl "+page.getWebURL()); //sayfanın adresini yazar
        System.out.println("content charset "+page.getContentCharset()); //sayfaya ait charset bilgisi ex:UTF-8
        System.out.println("parse data \n"+page.getParseData()); // sayfaya ait body etiketinde ki text içeriği yazar
        System.out.println("***************************************");
        System.out.println();
        System.out.println();
        System.out.println("html parsa content charset "+htmlParseData.getContentCharset());
        System.out.println("htmlpase html "+htmlParseData.getHtml());
        System.out.println("htmlpase metatags "+htmlParseData.getMetaTags());
        System.out.println("htmlpase description "+htmlParseData.getMetaTagValue("description")); //name =description olan meta etiketinin content içeriği
            System.out.println("htmlpase text "+htmlParseData.getText()); //body etiketinde ki text içerik
        System.out.println("htmlpase title "+htmlParseData.getTitle()); //title etiketinin içeriği
        */
        //System.out.println(htmlParseData.ge);
        String url = page.getWebURL().getURL();
        System.out.println("Visit URL: " + url);
    }
}
