package se.liljeholm.systembolaget.json;

import java.math.BigDecimal;

/**
 * @author torbjorn
 *
 */
public class Article implements Comparable<Article> {
    private final long id;
    private int placement;
    private final String url;
    private final String name;
    private final String type;
    private final String price;
    private final BigDecimal volume;
    private final String abv;
    private final BigDecimal apk;

    public Article(long id, String name, String type, String url, String price, BigDecimal volume, String abv, BigDecimal apk) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.price = price;
        this.volume = volume;
        this.abv = abv;
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

    public String getUrl() {
        return url;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public String getPrice() {
        return price;
    }

    public String getAbv() {
        return abv;
    }

    @Override
    public int compareTo(Article o) {
        return o.apk.compareTo(apk);
    }

    public Article setPlacement(int placement) {
        this.placement = placement;
        return this;
    }

    public int getPlacement() {
        return placement;
    }
}
