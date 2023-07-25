package com.joao.apirest.client;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.joao.apirest.response.CharacterResponse;
import com.joao.apirest.response.EpisodeResponse;
import com.joao.apirest.response.ListOfEpisodes;
import com.joao.apirest.response.LocationResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {
    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id) {
        log.info("Buncando por ID [{}]", id);

        return webClient
                .get()
                .uri("/character/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os ID para informados")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findLocationById(String id) {
        log.info("Buncando localização ID [{}]", id);

        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os ID para informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findEpisodeById(String id) {
        log.info("Buncando episodeo ID [{}]", id);

        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os ID para informados")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodes> getAllEpisodes() {
        log.info("Buncando episodes");

        return webClient
                .get()
                .uri("/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os ID para informados")))
                .bodyToFlux(ListOfEpisodes.class);
    }
}
