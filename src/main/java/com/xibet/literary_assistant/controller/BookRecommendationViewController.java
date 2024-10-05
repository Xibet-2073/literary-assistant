package com.xibet.literary_assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookRecommendationViewController {

    private final BookRecommendationController bookRecommendationController;

    public BookRecommendationViewController(BookRecommendationController bookRecommendationController) {
        this.bookRecommendationController = bookRecommendationController;
    }

    @GetMapping("/recommend")
    public String showRecommendations(@RequestParam(required = false) String genre, Model model) {
        if (genre != null && !genre.isEmpty()) {
            String recommendations = bookRecommendationController.getBookRecommendation(genre);
            model.addAttribute("recommendations", recommendations);
        }
        return "book-recommendations";
    }
}

