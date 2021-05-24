package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.model.Type;
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
    @CsvSource({"44,Tarjeta,2021-07,033,03"})

    void post(String id, String title, String date, Integer number, String code) {

        when(repository.save(any(Card.class))).thenReturn(Mono.just(new Card(title,date,number,code, id)));
        var request = Mono.just(new Card(title,date,number,code, id));
        webTestClient.post()
                .uri("/card/crear")
                .body(request, Card.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

        verify(cardService).insert(argumentCaptor.capture());

        var card = argumentCaptor.getValue().block();

        Assertions.assertEquals(number, card.getNumber());
        Assertions.assertEquals(title, card.getTitle());
        //Assertions.assertEquals(type, card.getType());

    }

    @Test
    void list() {
        var list = Flux.just(
                new Card("Tarjeta3","2022-09",6666,"06", "1"),
                new Card("Tarjeta4","2022-10",6666,"06", "2")
        );

        when(repository.findAll()).thenReturn(list);
        webTestClient.get()
                .uri("/card/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].title").isEqualTo("Tarjeta3")
                .jsonPath("$[1].date").isEqualTo("2022-10");

        verify(cardService).listAll();
        verify(repository).findAll();

    }

    @Test
    void get() {
        var list = Mono.just(
                new Card("Tarjeta3","2022-09",16,"12", "1")
                //new Card("Tarjeta4","2022-10",06,Type.VISA,"221", "2")
        );

        when(repository.findById("1")).thenReturn(list);
        webTestClient.delete()
                .uri("/card/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }


    @Test
    void update() {
            when(repository.save(any(Card.class))).thenReturn(Mono.just(new Card("Tarjeta","2021-07",04343,"12","4")));
        var request = Mono.just(new Card("Tarjeta2","2022-07",04346,"12","5"));
        webTestClient.put()
                .uri("/card/up")
                .body(request, Card.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }


    @Test
    void delete() {
        var list = Mono.just(
                new Card("Tarjeta3","2022-09",04456,"06", "1")
                //new Card("Tarjeta4","2022-10",06,Type.VISA,"221", "2")
        );

        when(repository.findById("1")).thenReturn(list);
        webTestClient.delete()
                .uri("/card/03")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void getType() {
        var list = Flux.just(
                new Card("Tarjeta3","2022-09",46576889,"03", "1"),
                new Card("Tarjeta4","2022-10",46576889,"03", "2")
        );

        when(repository.findByType("VISA")).thenReturn(list);
        webTestClient.get()
                .uri("/card/VISA/type")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].title").isEqualTo("Tarjeta3")
                .jsonPath("$[1].date").isEqualTo("2022-10");

        verify(cardService).findByType("VISA");
        verify(repository).findByType("VISA");
    }

}