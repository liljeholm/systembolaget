package se.liljeholm.systembolaget.service;

import java.util.List;

import se.liljeholm.systembolaget.json.Article;

/**
 * @author torbjorn
 *
 */
public interface SystembolagetClient {

	List<Article> getArticles();
}
