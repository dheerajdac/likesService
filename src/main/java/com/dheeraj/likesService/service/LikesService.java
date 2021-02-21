package com.dheeraj.likesService.service;

import com.dheeraj.likesService.model.Like;
import com.dheeraj.likesService.model.LikeRequest;
import com.dheeraj.likesService.model.LikeResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LikesService {

    public Mono<Like> addLike(Like like);
    
    public Flux<Like> getLikesByParentId(String parentId);
    
    public Mono<LikeResponse>  getPageableLikes(String parentId, Integer size);

    public Mono<LikeResponse>  getPageableLikes(String parentId, LikeRequest req);

	public Mono<Long> countByParentId(String parentId);

}
