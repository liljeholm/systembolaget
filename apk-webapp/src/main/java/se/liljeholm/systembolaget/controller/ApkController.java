package se.liljeholm.systembolaget.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.service.SystembolagetClient;

@RestController
public class ApkController {

    @Qualifier("cacheableSystembolagetClient")
    @Autowired
    private SystembolagetClient systembolagetClient;

    @RequestMapping("/articles")
    public List<Article> getArticles(@RequestParam(name = "apk", defaultValue = "2") String apk) throws Exception {
        return systembolagetClient.getArticles()
                                  .stream()
                                  .filter(a -> a.getApk().compareTo(new BigDecimal(apk)) > 0)
                                  .sorted()
                                  .collect(Collectors.toList());
    }
}
