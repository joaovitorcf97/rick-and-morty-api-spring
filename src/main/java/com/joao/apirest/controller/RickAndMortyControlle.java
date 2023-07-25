package com.joao.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.apirest.client.RickAndMortyClient;
import com.joao.apirest.response.CharacterResponse;
import com.joao.apirest.response.EpisodeResponse;
import com.joao.apirest.response.ListOfEpisodes;
import com.joao.apirest.response.LocationResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RickAndMortyControlle {
    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findAndCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodes> getAllEpisodes() {
        return rickAndMortyClient.getAllEpisodes();
    }
}
