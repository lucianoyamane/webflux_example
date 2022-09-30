package br.com.lucianoyamane.webflux_example.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class RandomUserReactiveClient {
    private final WebClient client;

    public RandomUserReactiveClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("https://randomuser.me/api").build();
    }

    public Mono<Map> getUser() {
        return this.client.get().uri("/").retrieve().bodyToMono(Map.class).onErrorReturn(new HashMap());
    }
}
