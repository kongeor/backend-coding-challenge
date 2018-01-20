package uk.co.engagetech.challenge.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigInteger;

public class MoneyDeserializer extends JsonDeserializer<BigInteger> {

    @Override
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        String text = p.getText();
        String[] split = text.split(".");
        if (split.length == 2) {
            throw new UnsupportedOperationException("TODO");
        } else {
            return new BigInteger(text).multiply(BigInteger.valueOf(100));
        }
    }
}
