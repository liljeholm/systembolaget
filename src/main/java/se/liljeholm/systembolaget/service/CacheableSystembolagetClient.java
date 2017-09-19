package se.liljeholm.systembolaget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import se.liljeholm.systembolaget.json.Article;

import java.util.List;

/**
 * @author torbjorn
 */
@Service
public class CacheableSystembolagetClient implements SystembolagetClient {

    @Autowired
    @Qualifier("jaxbSystembolagetClient")
    private SystembolagetClient systembolagetClient;

    @Cacheable("articles")
    @Override
    public List<Article> getArticles() {
        return systembolagetClient.getArticles();
    }
}
