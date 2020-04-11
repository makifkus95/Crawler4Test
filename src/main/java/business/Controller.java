package business;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    public void controller() throws Exception {
        CrawlConfig config = new CrawlConfig();

        // Ara tarama verilerinin depolandığı klasörü ayarlayın
        config.setCrawlStorageFolder("data/crawler");

        //saniyede 1'den fazla istek göndermediğimizden emin olun. Sunucularda hızlı istek atmamalı
        //config.setPolitenessDelay(800);

        //Maksimum tarama derinliğini buradan ayarlayabilirsiniz. Sınırsız derinlik için varsayılan değer -1'dir.
        config.setMaxDepthOfCrawling(-1);

        // Tarama için maksimum sayfa sayısını ayarlayabilirsiniz. Varsayılan değer, sınırsız sayıda sayfa için -1'dir.
        config.setMaxPagesToFetch(100);

        // İkili veriler de taranmalı mı? örnek: pdf içeriği veya görüntülerin meta verileri vb
        //config.setIncludeBinaryContentInCrawling(true);

        // Bir proxy ayarlamanız gerekiyor mu? Eğer öyleyse, kullanabilirsiniz:
        // config.setProxyHost("proxyserver.example.com");
        // config.setProxyPort(8080);

        // Proxy de kimlik doğrulaması gerekiyorsa:
        // config.setProxyUsername(username); config.getProxyPassword(password);

        // Bu yapılandırma parametresi, taramayı devam ettirilebilir olarak ayarlamak için kullanılabilir
        // (taramayı daha önce kesintiye uğramış/çökmüş bir taramadan devam edebileceğiniz anlamına gelir).
        // Not: devam özelliğini etkinleştirirseniz ve yeni bir gezinme başlatmak istiyorsanız,
        // rootfolder'ın içeriğini el ile silmeniz gerekir.
        //config.setResumableCrawling(false);

        // Beklenmeyen bir hata oluştuğunda taramayı durdurmak istiyorsanız, bunu true olarak ayarlayın.
        // Paletinizi test etmeye ilk başladığınızda muhtemelen bu ayarın true olmasını ve
        // paletin uzun süre çalışmasına izin vermeye hazır olduğunuzda false olarak ayarlanmasını isteyeceksiniz.
        //config.setHaltOnError(true);


        // Bu gezinme için denetleyiciyi başlatın.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // Her tarama için bazı tohum URL'leri eklemeniz gerekir.
        // Bunlar getirilen ilk Url'lerdir ve daha sonra tarayıcı bu sayfalarda bulunan bağlantıları takip etmeye başlar
        controller.addSeed("http://www.xxxx.com/");
        //controller.addSeed("https://www.ics.uci.edu/~lopes/");
        //controller.addSeed("https://www.ics.uci.edu/~welling/");

        // Tarama sırasında kullanılacak iş parçacığı sayısı. Bunu arttırmak genellikle taramayı daha hızlı hale getirir.
        // Ancak tarama hızı diğer birçok faktöre de bağlıdır.
        // Hangi sayıda iş parçacığının sizin için en uygun olduğunu bulmak için bununla deney yapabilirsiniz.
        int numberOfCrawlers = 8;

        // Taramayı başlatın. Bu bir engelleme işlemidir,
        // yani kodunuz bundan sonra satıra yalnızca tarama tamamlandığında ulaşacaktır.
        controller.start(BasicCrawler.class, numberOfCrawlers);

        BasicCrawler basicCrawler = new BasicCrawler();
        System.out.println("BİTTİ");
        System.out.println("should visit : "+ basicCrawler.shouldvisit);
        System.out.println("visit : "+ basicCrawler.visitt);
    }
}
