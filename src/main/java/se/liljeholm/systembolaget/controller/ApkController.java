package se.liljeholm.systembolaget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.service.SystembolagetClient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ApkController {

    @Qualifier("cacheableSystembolagetClient")
    @Autowired
    private SystembolagetClient systembolagetClient;

    @Cacheable("articles")
    @RequestMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resources<Article> getArticles(@RequestParam(name = "fromIndex", defaultValue = "0") int fromIndex,
                                          @RequestParam(name = "toIndex", defaultValue = "100") int toIndex) {
        final AtomicInteger placement = new AtomicInteger(0);
        List<Article> articles = systembolagetClient.getArticles()
                                                    .stream()
                                                    .sorted((a1, a2) -> a2.getApk().compareTo(a1.getApk()))
                                                    .peek(a -> a.setPlacement(placement.incrementAndGet()))
                                                    .collect(Collectors.toList());
        Resources<Article> resources = new Resources<>(articles.subList(fromIndex, toIndex));

        int nextFromIndex = Math.min(toIndex, articles.size());
        int nextToIndex = Math.min(toIndex + 100, articles.size());
        if (nextFromIndex != nextToIndex) {
            resources.add(linkTo(methodOn(getClass()).getArticles(nextFromIndex, nextToIndex)).withRel("next"));
        }

        int prevFromIndex = Math.max(fromIndex - 100, 0);
        int prevToIndex = Math.min(prevFromIndex + 100, articles.size());
        if (prevFromIndex != prevToIndex) {
            resources.add(linkTo(methodOn(getClass()).getArticles(prevFromIndex, prevToIndex)).withRel("prev"));
        }

        return resources;
    }
}
