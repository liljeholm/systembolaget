package se.liljeholm.systembolaget.xml;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author torbjorn
 *
 */
public class XmlArticle {

    @XmlElement(name = "nr")
    private long number;

    @XmlElement(name = "Namn")
    private String name;

    @XmlElement(name = "Varugrupp")
    private String type;

    @XmlElement(name = "Volymiml")
    private BigDecimal volume;

    @XmlElement(name = "Prisinklmoms")
    private BigDecimal price;

    @XmlElement(name = "PrisPerLiter")
    private BigDecimal pricePerLiter;

    @XmlJavaTypeAdapter(type = BigDecimal.class, value = PercentageAdapter.class)
    @XmlElement(name = "Alkoholhalt")
    private BigDecimal abv;

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal calculateApk() {
        return abv.multiply(volume).divide(price, 2, RoundingMode.HALF_UP);
    }
}
