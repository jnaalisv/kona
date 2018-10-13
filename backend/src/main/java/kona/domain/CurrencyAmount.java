package kona.domain;

import javax.persistence.Embeddable;

@Embeddable
public class CurrencyAmount {

    private int amount;
    private CurrencyCode currency;

    public CurrencyAmount(int amount, CurrencyCode currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public CurrencyAmount() {}

    public int getAmount() {
        return amount;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }
}
