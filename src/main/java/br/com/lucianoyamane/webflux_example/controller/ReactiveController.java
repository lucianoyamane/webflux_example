package br.com.lucianoyamane.webflux_example.controller;

import br.com.lucianoyamane.webflux_example.service.AggregateService;
import br.com.lucianoyamane.webflux_example.service.FirstWithValueService;
import br.com.lucianoyamane.webflux_example.valueobject.AggregateValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController()
public class ReactiveController {

    @Autowired
    AggregateService aggregateService;

    @Autowired
    FirstWithValueService firstWithValueService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AggregateValueObject>> mono() {
        return this.aggregateService
                .execute()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/first", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Map>> monoFirstWithValue() {
        return this.firstWithValueService
                .execute()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AggregateValueObject> monoBlock() {
        AggregateValueObject aggregateValueObject = this.aggregateService.execute().block();
        return ResponseEntity.ok(aggregateValueObject);
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseEntity> flux() {
        return Flux.range(1,5).map(index -> {
            Mono<AggregateValueObject> aggregateValueObject = this.aggregateService.execute();
            return ResponseEntity.ok(aggregateValueObject.block());
        });
    }
}
