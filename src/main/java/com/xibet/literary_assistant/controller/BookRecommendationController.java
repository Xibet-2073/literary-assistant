package com.xibet.literary_assistant.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/books")
public class BookRecommendationController {

    private final ChatClient aiChatClient;

    public BookRecommendationController(ChatClient aiChatClient) {
        this.aiChatClient = aiChatClient;
    }

    @GetMapping("/recommend")
    public String getBookRecommendation(@RequestParam String genre) {
        String systemInstructions = "You are a knowledgeable librarian with expertise in various literary genres. " +
                "Provide thoughtful book recommendations based on the given genre.";

        String userRequest = "Recommend three books in the " + genre + " genre, including the author and a brief description for each.";

        SystemMessage systemMessage = new SystemMessage(systemInstructions);
        UserMessage userMessage = new UserMessage(userRequest);

        Prompt fullPrompt = new Prompt(Arrays.asList(systemMessage, userMessage));

        return aiChatClient.call(fullPrompt)
                .getResult()
                .getOutput()
                .getContent();
    }
}