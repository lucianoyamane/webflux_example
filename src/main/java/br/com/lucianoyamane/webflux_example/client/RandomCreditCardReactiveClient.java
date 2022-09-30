package br.com.lucianoyamane.webflux_example.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class RandomCreditCardReactiveClient {
    private final WebClient client;

    public RandomCreditCardReactiveClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("https://random-data-api.com/api/v2/credit_cards").build();
    }

    public Mono<Map> getCreditCard() {
        return this.client.get().retrieve().bodyToMono(Map.class).onErrorReturn(new HashMap());
    }
}
