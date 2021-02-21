package com.dheeraj.likesService.serviceImpl;

import com.dheeraj.likesService.model.Like;
import com.dheeraj.likesService.model.LikeRequest;
import com.dheeraj.likesService.model.LikeResponse;
import com.dheeraj.likesService.service.LikesService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LikesServiceImpl implements LikesService {

    @Value("${repo.uri}")
    String uri;

    @Override
    public Mono<Like> addLike(Like like) {
        Mono<Like> rep = getWebClientBuilder().build()
                            .post()
                            .uri("/like")
                            .bodyValue(like)
                            .retrieve()
                            .bodyToMono(Like.class);
        return rep;
    }

    @Override
    public Flux<Like> getLikesByParentId(String parentId) {
        Flux<Like> rep = getWebClientBuilder().build()
                            .get()
                            .uri("/like/{parentId}", parentId)
                            .retrieve()
                            .bodyToFlux(Like.class);
        return rep;
    }

    @Override
    public Mono<LikeResponse> getPageableLikes(String parentId, Integer size) {
        Mono<LikeResponse> rep = getWebClientBuilder().build()
                                    .get()
                                    .uri("/like/{parentId}/pageState/{size}", parentId, size)
                                    .retrieve()
                                    .bodyToMono(LikeResponse.class);
        return rep;
    }

    @Override
    public Mono<LikeResponse> getPageableLikes(String parentId, LikeRequest req) {
        Mono<LikeResponse> rep = getWebClientBuilder().build()
                                    .post()
                                    .uri("/like/{parentId}/pageState", parentId)
                                    .bodyValue(req)
                                    .retrieve()
                                    .bodyToMono(LikeResponse.class);
        return rep;
    }
    
    private WebClient.Builder getWebClientBuilder(){
        return WebClient.builder()
            .baseUrl(uri);
    }
    
}
