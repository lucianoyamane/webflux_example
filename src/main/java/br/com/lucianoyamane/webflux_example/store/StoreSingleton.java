package br.com.lucianoyamane.webflux_example.store;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StoreSingleton {

    private static StoreSingleton instance;

    private List<String> messages;

    private StoreSingleton() {
        this.messages = new ArrayList<>();
    }

    public static StoreSingleton getInstance() {
        if (instance == null) {
            instance = new StoreSingleton();
        }
        return instance;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
        this.startSubscribe(message);
    }

    public void startSubscribe(String message) {
        Mono.just(message).delayElement(Duration.ofMillis(10000)).subscribe(messageItem -> System.out.println(messageItem));
    }
}
