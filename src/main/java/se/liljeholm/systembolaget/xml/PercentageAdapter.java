package se.liljeholm.systembolaget.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

/**
 * @author torbjorn
 */
public class PercentageAdapter extends XmlAdapter<String, BigDecimal> {
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        String cleaned = v.replaceAll("%", "");
        return new BigDecimal(cleaned).divide(ONE_HUNDRED, MathContext.DECIMAL32);
    }

    @Override
    public String marshal(BigDecimal v) throws Exception {
        return NumberFormat.getPercentInstance().format(v.doubleValue());
    }
}
