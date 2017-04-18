/**
 *
 */
package se.liljeholm.systembolaget.converter;

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

    @Override
    public Article convert(XmlArticle xmlArticle) {
        return new Article(xmlArticle.getNumber(),
                           xmlArticle.getName(),
                           xmlArticle.getType(),
                           xmlArticle.getPrice(),
                           xmlArticle.getVolume(),
                           xmlArticle.calculateApk());
    }
}
