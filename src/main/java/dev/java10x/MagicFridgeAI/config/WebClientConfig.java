package dev.java10x.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
/*
    //url
    @Value("${chat.gpt.api.url:https://api.openai.com/v1/chat/completions}")
    private String chatGptApiUrl;

    //ler documentacao do webclient
    @Bean
    public WebClient webClient( WebClient.Builder builder ) {
        return builder.baseUrl(chatGptApiUrl).build();
    }
}
*/
// DEFINA A URL BASE APENAS COM O DOMÍNIO.
// Desta forma, o WebClient se torna reutilizável para qualquer endpoint da API.
private static final String apiUrl = "https://api.openai.com";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        // Agora o builder usa a base correta.
        return builder.baseUrl(apiUrl).build();
    }
}


