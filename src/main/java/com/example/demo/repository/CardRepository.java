package com.example.demo.repository;

import com.example.demo.model.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CardRepository extends ReactiveMongoRepository<Card, Integer> {
    Mono<Card> findById(Integer number);

   // Mono<Card> findByType(String type);
}
