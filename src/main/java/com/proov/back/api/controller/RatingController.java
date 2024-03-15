package com.proov.back.api.controller;

import com.proov.back.api.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class RatingController {
    private final RatingService ratingService;
    @GetMapping("/rating")
    public void getRating() {
        ratingService.assignRating();
    }
}
