package br.com.lucianoyamane.webflux_example.service;

import br.com.lucianoyamane.webflux_example.store.StoreSingleton;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class DelayListService {

    public Flux execute() {
        return Flux.fromIterable(StoreSingleton.getInstance().getMessages()).delaySequence(Duration.ofMillis(3000)).map(index -> {
            Mono<String> messageMono = Mono.just(index);
            return ResponseEntity.ok(messageMono.block());
        });
    }

    public void executeBackground() {
        StoreSingleton.getInstance().startSubscribe("teste");
    }

    public Boolean executeAddBackGround(String message) {
        StoreSingleton.getInstance().addMessage(message);
        return Boolean.TRUE;
    }

}