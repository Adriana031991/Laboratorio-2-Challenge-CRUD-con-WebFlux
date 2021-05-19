package com.example.demo.service;

import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CardService {


    @Autowired
    private CardRepository repository;

    public Flux<Card> listAll() {
        return repository.findAll();
    }

    public Mono<Card> get(String id){
        return repository.findById(id);
    }

/*
    public Mono<Card> get(Integer number) {
        return repository.findById(number);
    }

 */

    public Flux<Card> findByType(String type) {
        return repository.findByType(type);
    }

    public Mono<Void> insert(Mono<Card> cardMono){
        return cardMono
                .flatMap(repository::save)
                .then();
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
