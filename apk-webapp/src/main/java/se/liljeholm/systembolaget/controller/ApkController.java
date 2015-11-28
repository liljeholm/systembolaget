package se.liljeholm.systembolaget.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.service.SystembolagetClient;

@RestController
public class ApkController {

	@Autowired
	private SystembolagetClient systembolagetClient;

	@RequestMapping("/articles")
	public List<Article> getArticles(@RequestParam(name = "apk", defaultValue = "2") String apk) throws Exception {
		List<Article> articles = systembolagetClient.getArticles();
		Collections.sort(articles);
		return articles
				.stream().
				filter(a -> a.getApk().compareTo(new BigDecimal(apk)) > 0)
				.collect(Collectors.toList());
	}
}
