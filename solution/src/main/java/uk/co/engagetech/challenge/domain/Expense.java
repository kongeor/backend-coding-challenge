package uk.co.engagetech.challenge.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import uk.co.engagetech.challenge.util.DateDeserializer;
import uk.co.engagetech.challenge.util.MoneyDeserializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;

    @JsonDeserialize(using = MoneyDeserializer.class)
    private BigInteger amount;

    private String reason;

}
