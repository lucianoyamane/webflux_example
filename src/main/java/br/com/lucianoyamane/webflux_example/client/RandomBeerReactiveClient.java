package br.com.lucianoyamane.webflux_example.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class RandomBeerReactiveClient {

    private final WebClient client;

    public RandomBeerReactiveClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("https://random-data-api.com/api/v2/beers").build();
    }

    public Mono<Map> getBeer() {
        return this.client.get().retrieve().bodyToMono(Map.class).onErrorReturn(new HashMap());
    }
}
