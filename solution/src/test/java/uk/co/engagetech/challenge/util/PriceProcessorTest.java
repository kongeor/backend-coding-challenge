package uk.co.engagetech.challenge.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceProcessorTest {

    private PriceProcessor priceProcessor = new PriceProcessor();
    private @Mock ConversionFetcher conversionFetcher;

    @Before
    public void setup() {
        priceProcessor.setConversionFetcher(conversionFetcher);
        when(conversionFetcher.getEurRate()).thenReturn(1.2f);
    }

    @Test
    public void convert_no_decimal() {
        assertEquals(1000, priceProcessor.fuzzyConvert("10"));
        assertEquals(1000, priceProcessor.fuzzyConvert(" 10 "));
    }

    @Test
    public void convert_decimal() {
        assertEquals(1000, priceProcessor.fuzzyConvert("10.00"));
        assertEquals(1012, priceProcessor.fuzzyConvert(" 10.12"));
        assertEquals(1012, priceProcessor.fuzzyConvert(" 10.12"));
    }

    @Test
    public void convert_eur() {
        assertEquals(1000, priceProcessor.fuzzyConvert("12 EUR"));
        assertEquals(833, priceProcessor.fuzzyConvert("10 EUR"));
    }

    @Test
    public void calc_vat_test() {
        assertEquals(167, priceProcessor.calcVat(1000));
    }

    @Test
    public void calc_net_test() {
        assertEquals(833, priceProcessor.calcNet(1000));
    }
}