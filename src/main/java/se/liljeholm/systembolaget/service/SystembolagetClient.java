package se.liljeholm.systembolaget.service;

import se.liljeholm.systembolaget.json.Article;

import java.util.List;

/**
 * @author torbjorn
 */
public interface SystembolagetClient {

    List<Article> getArticles();
}
