package se.liljeholm.systembolaget.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.xml.XmlArticle;
import se.liljeholm.systembolaget.xml.XmlArticles;

@RestController
public class ApkController {

	private String url = "http://www.systembolaget.se/api/assortment/products/xml";
	private XmlArticles xmlArticles;

	@RequestMapping("/articles")
	public List<Article> hello() throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlArticles.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		if (xmlArticles == null) {
			xmlArticles = (XmlArticles) unmarshaller.unmarshal(new URL(url));
		}
		List<XmlArticle> list = xmlArticles.getList();
		Collections.sort(list);

		List<Article> listOfArticles = new ArrayList<>();
		list
			.stream()
			.filter(a -> listOfArticles.size() < 50)
			.forEach(a -> listOfArticles.add(new Article(a.getNumber(), a.getName(), a.getType(), a.getVolume(), a.calculateApk())));

		return listOfArticles;
	}
}
