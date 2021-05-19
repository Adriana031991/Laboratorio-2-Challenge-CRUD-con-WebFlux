package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;
import com.example.demo.service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CardController.class)
class CardControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private CardService cardService;

    @Captor
    private ArgumentCaptor<Mono<Card>> argumentCaptor;

    @MockBean
    private CardRepository repository;

    @ParameterizedTest
    @CsvSource({"Tarjeta,2021-07,03,MasterCard,0"})
    /*
    Se debe validar la tarjeta, es decir, tenemos tres tipos de tarjetas
    MasterCard, VISA, PRIME, para el tipo MasterCard se debe iniciar con el codigo 03,
    para VISA 06 y para PRIME 12,
    si un código de estos no corresponde al tipo entonces debe generar un error de registro
     */
    void post(String title, String date, Integer number, String type, Integer code) {

        when(repository.save(any(Card.class))).thenReturn(Mono.just(new Card(title,date,number,type,code)));
        var request = Mono.just(new Card(title,date,number,type,code));
        webTestClient.post()
                .uri("/card/crear")
                .body(request, Card.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        verify(cardService).insert(argumentCaptor.capture());

        var card = argumentCaptor.getValue().block();

        Assertions.assertEquals(number, card.getNumber());
        Assertions.assertEquals(title, card.getTitle());
        Assertions.assertEquals(type, card.getType());

    }

    @Test
    void get() {
        webTestClient.get()
                .uri("/card/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(cardEntityExchangeResult -> {
                    var card = cardEntityExchangeResult.getResponseBody();
                    assert card != null;
                });
    }





}