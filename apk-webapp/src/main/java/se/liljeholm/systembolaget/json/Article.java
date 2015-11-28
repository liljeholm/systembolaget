package se.liljeholm.systembolaget.json;

import java.math.BigDecimal;

/**
 * @author torbjorn
 *
 */
public class Article {

	private long id;
	private String name;
	private String type;
	private BigDecimal volume;
	private BigDecimal apk;

	public Article(long id, String name, String type, BigDecimal volume, BigDecimal apk) {
		this.id = id;
		this.name = name;
		this.type = type;
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
}
