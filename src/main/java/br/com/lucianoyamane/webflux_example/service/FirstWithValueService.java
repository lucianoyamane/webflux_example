package br.com.lucianoyamane.webflux_example.service;

import br.com.lucianoyamane.webflux_example.client.RandomBeerReactiveClient;
import br.com.lucianoyamane.webflux_example.client.RandomCreditCardReactiveClient;
import br.com.lucianoyamane.webflux_example.client.RandomUserReactiveClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class FirstWithValueService {

    @Autowired
    RandomBeerReactiveClient randomBeerReactiveClient;

    @Autowired
    RandomCreditCardReactiveClient randomCreditCardReactiveClient;

    @Autowired
    RandomUserReactiveClient randomUserReactiveClient;

    public Mono<Map> execute() {
        return Mono.firstWithValue(
                this.randomUserReactiveClient.getUser(),
                this.randomCreditCardReactiveClient.getCreditCard(),
                this.randomBeerReactiveClient.getBeer()
        );
    }
}
