# EchoBrief AI

Manda um áudio falando o que precisa fazer. O sistema transcreve, entende, cria as tarefas automaticamente e ainda responde por voz.

---

## O problema que resolve

Todo mundo já mandou um áudio assim:

> "Cara, lembra de ligar pro João amanhã, marcar o dentista, comprar whey e ver aquele boleto do cartão"

Essas informações se perdem. Ficam enterradas no histórico de conversa, no bloco de notas ou na memória.

O EchoBrief transcreve esse áudio, extrai cada tarefa, categoriza, salva no banco e ainda devolve uma confirmação por voz.

---

## Como funciona

```
Usuário grava um áudio
        ↓
Backend recebe o arquivo
        ↓
Whisper transcreve o áudio em texto
        ↓
LLM analisa o texto e identifica tarefas
        ↓
Tool Calling cria cada tarefa no banco
        ↓
ElevenLabs sintetiza a resposta em áudio
        ↓
Usuário recebe texto + áudio de confirmação
```

---

## Stack

**Backend**
- Java 21
- Spring Boot 3.5
- Spring AI 1.1 (ChatClient, Tool Calling)
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Docker

**IA**
- Groq (LLM: llama-3.3-70b-versatile)
- Groq Whisper (transcrição de áudio)
- ElevenLabs (text-to-speech)

**Deploy(Em breve)**
- Azure App Service (backend)
- Vercel (frontend)
- Neon DB / Azure PostgreSQL (banco)

---

## Endpoints

### Autenticação
```
POST /api/auth/register   → cria conta
POST /api/auth/login      → retorna JWT
```

### Tarefas
```
GET    /api/tasks              → lista todas
GET    /api/tasks/{id}         → busca por id
GET    /api/tasks?status=      → filtra por status
GET    /api/tasks?category=    → filtra por categoria
POST   /api/tasks              → cria manualmente
PATCH  /api/tasks/{id}         → atualiza
DELETE /api/tasks/{id}         → remove
```

### IA
```
POST /api/ai/process    → processa texto e cria tarefas
POST /api/ai/audio      → transcreve áudio e cria tarefas
GET  /api/tts?text=     → converte texto em áudio
```

---

## Categorias e status disponíveis

**Categorias:** `HEALTH` `FINANCE` `WORK` `PERSONAL` `SHOPPING` `OTHER`

**Status:** `PENDING` `IN_PROGRESS` `DONE` `CANCELLED`

---

## Como rodar localmente

### Pré-requisitos
- Java 21
- Docker

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/echobrief-ai.git
cd echobrief-ai
```

### 2. Suba o banco de dados
```bash
docker compose up -d
```

### 3. Configure as variáveis de ambiente

Crie as seguintes variáveis no seu sistema ou na configuração da IDE:

```
GROQ_API_KEY=sua_chave_groq
ELEVENLABS_API_KEY=sua_chave_elevenlabs
```

- **Groq:** [console.groq.com](https://console.groq.com) — gratuito
- **ElevenLabs:** [elevenlabs.io](https://elevenlabs.io) — free tier disponível

### 4. Configure o voice ID no application.yml

```yaml
elevenlabs:
  voice-id: SEU_VOICE_ID
```

O voice ID fica em **Voices → My Voices** na sua conta do ElevenLabs.

### 5. Rode a aplicação
```bash
./gradlew bootRun
```

A API sobe em `http://localhost:8080`.

---

## Exemplo de uso

### Registrar e logar
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email": "voce@email.com", "password": "123456"}'
```

### Processar um texto
```bash
curl -X POST http://localhost:8080/api/ai/process \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN" \
  -d '{"text": "preciso comprar whey, marcar dentista e pagar o boleto"}'
```

Resposta:
```json
{
  "reply": "Criei 3 tarefas: comprar whey (SHOPPING), marcar dentista (HEALTH), pagar boleto (FINANCE)."
}
```

### Enviar áudio
```bash
curl -X POST http://localhost:8080/api/ai/audio \
  -H "Authorization: Bearer SEU_TOKEN" \
  -F "file=@seu-audio.ogg"
```

---

## Estrutura do projeto

```
src/main/java/com/artur/echobriefai/
├── ai/
│   ├── AudioAiService.java       # orquestração do ChatClient
│   ├── TaskTools.java            # tool calling para criar tarefas
│   └── TranscriptionService.java # transcrição via Whisper
├── config/
│   └── AiConfig.java             # configuração do ChatClient
├── controller/
│   ├── AiController.java
│   ├── AuthController.java
│   ├── TaskController.java
│   └── TtsController.java
├── dto/
├── entity/
│   ├── Task.java
│   ├── User.java
│   └── enums/
├── exception/
├── repository/
├── security/
│   ├── JwtAuthFilter.java
│   ├── JwtService.java
│   ├── SecurityConfig.java
│   └── UserDetailsServiceImpl.java
└── service/
    ├── AuthService.java
    ├── TaskService.java
    └── TtsService.java
```

---

## Variáveis de ambiente

| Variável | Descrição |
|---|---|
| `GROQ_API_KEY` | Chave da API do Groq |
| `ELEVENLABS_API_KEY` | Chave da API do ElevenLabs |

O `jwt.secret` e as configs do banco ficam no `application.yml`.

---

## Desenvolvido por

Artur — projeto desenvolvido durante o bootcamp de Spring AI da DIO / NTT Data.
