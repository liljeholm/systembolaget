package se.liljeholm.systembolaget.converter;

import java.text.NumberFormat;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import se.liljeholm.systembolaget.json.Article;
import se.liljeholm.systembolaget.xml.XmlArticle;

/**
 * @author torbjorn
 *
 */
@Component
public class XmlArticleToArticleConverter implements Converter<XmlArticle, Article> {
    private static final String URL = "https://www.systembolaget.se/sok-dryck/?searchquery=%s&fullassortment=1";

    @Override
    public Article convert(XmlArticle xmlArticle) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        return new Article(xmlArticle.getNumber(),
                           xmlArticle.getName(),
                           xmlArticle.getType(),
                           getLink(xmlArticle.getNumber()),
                           NumberFormat.getCurrencyInstance().format(xmlArticle.getPrice()),
                           xmlArticle.getVolume(),
                           percentInstance.format(xmlArticle.getAbv()),
                           xmlArticle.calculateApk());
    }

    private String getLink(long number) {
        return String.format(URL, number);
    }
}
