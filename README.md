# MagicFridge AI 🍳🤖
Um assistente de cozinha inteligente, construído com Java e Spring Boot, que se integra ao serviço do ChatGPT para sugerir receitas com base nos ingredientes que você tem em casa.

### Sobre o Projeto
Este projeto foi criado para solucionar o clássico dilema "o que cozinhar hoje?" de uma forma moderna e eficiente. Em vez de procurar receitas manualmente, esta aplicação permite que o usuário simplesmente liste os ingredientes disponíveis para receber sugestões de pratos criativos e práticos gerados por Inteligência Artificial.

O backend, desenvolvido com a robustez do framework Spring Boot, gerencia as requisições e se comunica diretamente com a API do ChatGPT. Ele envia a lista de ingredientes e recebe de volta receitas completas, transformando a experiência na cozinha e ajudando a reduzir o desperdício de alimentos.

### Funcionalidades:
💡 Sugestões Inteligentes: Utiliza o poder do ChatGPT para gerar receitas criativas e com instruções claras.

⚙️ API RESTful: Uma API bem estruturada, construída com Spring Boot, para receber a lista de ingredientes e retornar as receitas.

🚀 Arquitetura Robusta: Baseado em Java e no ecossistema Spring, garantindo performance e escalabilidade.

📝 Processamento Simples: O usuário informa os ingredientes via texto, e a IA cuida do resto.

## 🛠️ Tecnologias Utilizadas
### Backend:

- Java (JDK 17+)

- Spring Boot

- Spring Web (para a criação da API RESTful)

- Spring Data JPA (opcional, para persistência de dados)

- Inteligência Artificial:

  - OpenAI API (integração com o serviço do ChatGPT)

### Build & Gerenciamento de Dependências:

- Maven ou Gradle

### Banco de Dados (Opcional):

- H2 Database (para desenvolvimento)

- PostgreSQL ou MySQL (para produção)

### 🚀 Como Funciona
O fluxo da aplicação é direto e eficiente:

1. Requisição do Usuário: O usuário envia uma requisição POST para a API com a lista de ingredientes no corpo da mensagem (ex: {"ingredientes": ["frango", "tomate", "cebola"]}).

2. Controlador Spring: O Controller da API recebe a requisição.

3. Lógica de Serviço: A camada de Service constrói um prompt otimizado para o ChatGPT (ex: "Crie uma receita usando apenas frango, tomate e cebola.").

4. Comunicação com a IA: O serviço faz uma chamada HTTP para a API da OpenAI, enviando o prompt.

5. Geração da Receita: O ChatGPT processa o prompt e gera uma ou mais receitas em formato de texto.

6. Resposta ao Usuário: A API do Spring Boot recebe a resposta do ChatGPT e a retorna para o usuário no formato JSON.
