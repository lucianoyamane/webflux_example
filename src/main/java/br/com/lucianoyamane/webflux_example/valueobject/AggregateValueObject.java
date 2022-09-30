package br.com.lucianoyamane.webflux_example.valueobject;

import java.util.Map;

public class AggregateValueObject {

    private Map<String, Object> user;
    private Map<String, Object> creditCard;
    private Map<String, Object> beer;

    public Map<String, Object> getUser() {
        return user;
    }

    public void setUser(Map<String, Object> user) {
        this.user = user;
    }

    public Map<String, Object> getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Map<String, Object> creditCard) {
        this.creditCard = creditCard;
    }

    public Map<String, Object> getBeer() {
        return beer;
    }

    public void setBeer(Map<String, Object> beer) {
        this.beer = beer;
    }
}
