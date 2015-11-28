package se.liljeholm.systembolaget.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.xml.XmlArticles;

/**
 * @author torbjorn
 *
 */
@Service
public class SystembolagetDefaultClient implements SystembolagetClient {
	private static final Logger LOG = LoggerFactory.getLogger(SystembolagetDefaultClient.class);


	@Autowired
	private JAXBContext jaxbContext;

	private String url = "http://www.systembolaget.se/api/assortment/products/xml";

	private XmlArticles xmlArticles;

	@Override
	public List<Article> getArticles() {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

		if (xmlArticles == null) {
			long time = System.currentTimeMillis();
			try {
				xmlArticles = (XmlArticles) unmarshaller.unmarshal(new URL(url));
			} catch (MalformedURLException | JAXBException e) {
				throw new IllegalArgumentException(e);
			}
			LOG.debug("XML reading took: {}ms", (System.currentTimeMillis() - time));
		}

		List<Article> articles = new ArrayList<>();
		xmlArticles.getList().stream().forEach(a -> articles.add(new Article(a.getNumber(),
																			 a.getName(),
																			 a.getType(),
																			 a.getPrice(),
																			 a.getVolume(),
																			 a.calculateApk())));
		return articles;
	}
}
