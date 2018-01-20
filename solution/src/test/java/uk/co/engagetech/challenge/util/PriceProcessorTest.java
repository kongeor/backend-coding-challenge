package uk.co.engagetech.challenge.util;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class PriceProcessorTest {

    private PriceProcessor priceProcessor = new PriceProcessor();

    @Test
    public void calc_vat_test() {
        assertEquals(167, priceProcessor.calcVat(1000));
    }

    @Test
    public void calc_net_test() {
        assertEquals(833, priceProcessor.calcNet(1000));
    }
}