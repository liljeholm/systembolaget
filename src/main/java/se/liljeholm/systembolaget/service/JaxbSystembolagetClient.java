package se.liljeholm.systembolaget.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.liljeholm.common.InitializationException;
import se.liljeholm.systembolaget.converter.XmlArticleToArticleConverter;
import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.xml.XmlArticles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author torbjorn
 */
@Service
public class JaxbSystembolagetClient implements SystembolagetClient {
    private static final Logger LOG = LoggerFactory.getLogger(JaxbSystembolagetClient.class);

    @Autowired
    private JAXBContext jaxbContext;

    @Autowired
    private XmlArticleToArticleConverter xmlArticleToArticleConverter;

    @Value("${systembolaget.api.url}")
    private String url;

    @Override
    public List<Article> getArticles() {
        Unmarshaller unmarshaller;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new InitializationException("Failed to create unmarshaller", e);
        }

        XmlArticles xmlArticles;
        long start = System.currentTimeMillis();
        try {
            xmlArticles = (XmlArticles) unmarshaller.unmarshal(new URL(url));
        } catch (MalformedURLException | JAXBException e) {
            throw new IllegalArgumentException("Failed to unmarshal using url: " + url, e);
        }
        long totalTime = System.currentTimeMillis() - start;
        LOG.info("Unmarshaled {} articles in {} ms.", xmlArticles.getList().size(), totalTime);

        return xmlArticles.getList()
                          .parallelStream()
                          .map(xmlArticleToArticleConverter::convert)
                          .collect(Collectors.toList());
    }
}
