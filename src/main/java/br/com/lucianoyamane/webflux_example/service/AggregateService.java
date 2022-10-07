package br.com.lucianoyamane.webflux_example.service;

import br.com.lucianoyamane.webflux_example.client.RandomBeerReactiveClient;
import br.com.lucianoyamane.webflux_example.client.RandomCreditCardReactiveClient;
import br.com.lucianoyamane.webflux_example.client.RandomUserReactiveClient;
import br.com.lucianoyamane.webflux_example.valueobject.AggregateValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.Map;

@Service
public class AggregateService {
    @Autowired
    RandomBeerReactiveClient randomBeerReactiveClient;

    @Autowired
    RandomCreditCardReactiveClient randomCreditCardReactiveClient;

    @Autowired
    RandomUserReactiveClient randomUserReactiveClient;

    public Mono<AggregateValueObject> execute() {
        return Mono.zip(
                this.randomUserReactiveClient.getUser(),
                this.randomCreditCardReactiveClient.getCreditCard(),
                this.randomBeerReactiveClient.getBeer()
        ).map(this::combine);
    }

    private AggregateValueObject combine(Tuple3<Map, Map, Map> tuple) {
        AggregateValueObject aggregateValueObject = new AggregateValueObject();
        aggregateValueObject.setUser(tuple.getT1());
        aggregateValueObject.setCreditCard(tuple.getT2());
        aggregateValueObject.setBeer(tuple.getT3());
        return aggregateValueObject;
    }
}
