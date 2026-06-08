# EchoBrief AI

Transforme áudios em tarefas organizadas usando Inteligência Artificial.

O EchoBrief AI é uma aplicação full stack que permite ao usuário enviar um áudio descrevendo atividades e compromissos. A aplicação transcreve o conteúdo utilizando IA, identifica automaticamente as tarefas mencionadas, categoriza cada uma delas e as salva no sistema para acompanhamento posterior.

Além da resposta textual, o sistema também gera uma confirmação em áudio utilizando síntese de voz.

## Demonstração

Frontend:
https://echobrief-ai.vercel.app

Backend:
[URL Render]

---

## Problema Resolvido

É comum receber ou gravar áudios contendo várias informações importantes:

> "Preciso marcar o dentista, pagar o boleto do cartão e comprar whey."

Essas tarefas frequentemente ficam perdidas em conversas, blocos de notas ou simplesmente são esquecidas.

O EchoBrief automatiza esse processo transformando linguagem natural em tarefas estruturadas.

---

## Fluxo da Aplicação

1. Usuário envia um áudio.
2. O Whisper realiza a transcrição.
3. O modelo LLM interpreta o conteúdo.
4. O sistema identifica tarefas e categorias.
5. As tarefas são persistidas no banco de dados.
6. Uma resposta é gerada em texto e áudio.
7. O usuário acompanha tudo pelo dashboard.

---

## Tecnologias Utilizadas

### Backend

* Java 21
* Spring Boot 3.5
* Spring Security
* JWT Authentication
* Spring Data JPA
* PostgreSQL
* Docker

### Inteligência Artificial

* Spring AI
* Groq Llama 3.3 70B
* Groq Whisper
* ElevenLabs

### Frontend

* Next.js
* TypeScript
* Tailwind CSS

### Deploy

* Render
* Vercel

---

## Principais Funcionalidades

* Cadastro e autenticação de usuários
* Controle de acesso com JWT
* Upload de áudio
* Transcrição automática
* Extração inteligente de tarefas
* Classificação automática por categoria
* Dashboard de gerenciamento
* Histórico de processamentos
* Geração de respostas por voz

---

## Arquitetura

```text
Usuário
   ↓
Next.js Frontend
   ↓
Spring Boot API
   ↓
Whisper (Transcrição)
   ↓
LLM (Interpretação)
   ↓
Tool Calling
   ↓
PostgreSQL
   ↓
ElevenLabs (Resposta em áudio)
```

---

## Estrutura do Projeto

```text
src/main/java/com/artur/echobriefai
├── ai
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
└── service
```

---

## Como Executar

### Pré-requisitos

* Java 21
* Docker

### Clonar o projeto

```bash
git clone URL_DO_REPOSITORIO
```

### Subir banco

```bash
docker compose up -d
```

### Configurar variáveis

```env
GROQ_API_KEY=
ELEVENLABS_API_KEY=
```

### Executar

```bash
./gradlew bootRun
```

---

## Autor

Artur Sales

Estudante de Ciência da Computação com foco em Backend, Cloud Computing e DevOps.
