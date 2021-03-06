package se.liljeholm.systembolaget.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author torbjorn
 */
@XmlRootElement(name = "artiklar")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlArticles {

    @XmlElement(name = "skapad-tid")
    private String created;

    @XmlElement(name = "artikel")
    private List<XmlArticle> list;

    public String getCreated() {
        return created;
    }

    public List<XmlArticle> getList() {
        return list;
    }
}
