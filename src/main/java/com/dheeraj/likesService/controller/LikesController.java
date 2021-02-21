package com.dheeraj.likesService.controller;

import com.dheeraj.likesService.model.Like;
import com.dheeraj.likesService.model.LikeRequest;
import com.dheeraj.likesService.model.LikeResponse;
import com.dheeraj.likesService.service.LikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/like")
public class LikesController {

    @Autowired
    LikesService service;

    @PostMapping("")
    public Mono<Like> addLike(@RequestBody Like like) {
        return this.service.addLike(like);
    }

    @GetMapping("/{parentId}")
    public Flux<Like> getLikes(@PathVariable String parentId){
        return this.service.getLikesByParentId(parentId);
    }

    @GetMapping("/{parentId}/pageState/{size}")
    public Mono<LikeResponse> getLikes(@PathVariable String parentId, @PathVariable Integer size) {
        return this.service.getPageableLikes(parentId, size);
    }

    @PostMapping("/{parentId}/pageState")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<LikeResponse> getLikes(@PathVariable String parentId, @RequestBody LikeRequest likeRequest) {
        return this.service.getPageableLikes(parentId, likeRequest);
    }

    @GetMapping("/count/{parentId}")
    public Mono<Long> getCountByParentId(@PathVariable String parentId){
        return this.service.countByParentId(parentId);
    }
    
}
