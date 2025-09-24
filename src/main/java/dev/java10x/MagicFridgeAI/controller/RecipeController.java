package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.Fooditem;
import dev.java10x.MagicFridgeAI.service.ChatGptService;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private FoodItemService service;
    private ChatGptService chatGptService;

    public RecipeController(FoodItemService service, ChatGptService chatGptService) {
        this.service = service;
        this.chatGptService = chatGptService;
    }

    //endpoint - retornar as informacoes
    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<Fooditem> fooditems = service.listar();
        return chatGptService.generateRecipe(fooditems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }



}
