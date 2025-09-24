package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.Fooditem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;

    @Value("${api.key}")
    private String apiKey;

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    // prompt fixo para gerar receitas
    public Mono<String> generateRecipe(List<Fooditem> fooditems) {
        // var alimentos recebe todoas as informacoes do BD
        String alimentos = fooditems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getNome(), item.getCategoria(), item. getQuantidade(), item.getValidade() ))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado no meu banco de dados, faca uma receita com os seguintes itens: " + alimentos;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role","system", "content", "Voce e um assistente que cria receitas." ),
                        Map.of("role","user", "content", prompt)
                ),
                "temperature", 0.7 // Adiciona um pouco de criatividade

        );

        return webClient.post()
                .uri("/v1/chat/completions") // O caminho completo do endpoint é especificado aqui.
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhuma receita foi gerada.";
                })
                .doOnError(error -> System.err.println("Erro ao chamar a API do OpenAI: " + error.getMessage())); // Adiciona log de erro;
    }
}
