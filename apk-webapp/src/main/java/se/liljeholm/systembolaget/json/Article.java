package se.liljeholm.systembolaget.json;

import java.math.BigDecimal;

/**
 * @author torbjorn
 *
 */
public class Article implements Comparable<Article> {
    private final long id;
    private final String name;
    private final String type;
    private final BigDecimal price;
    private final BigDecimal volume;
    private final BigDecimal apk;

    public Article(long id, String name, String type, BigDecimal price, BigDecimal volume, BigDecimal apk) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.volume = volume;
        this.apk = apk;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getApk() {
        return apk;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int compareTo(Article o) {
        return o.apk.compareTo(apk);
    }
}
