package uk.co.engagetech.challenge.expenses;


import uk.co.engagetech.challenge.validation.Amount;
import uk.co.engagetech.challenge.validation.ClientDate;

import javax.validation.constraints.NotNull;

public class ExpenseResource {

    @NotNull
    @ClientDate
    private String date;

    @NotNull
    @Amount
    private String amount;
    private String reason;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
