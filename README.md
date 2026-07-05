## API REST teste técnico Compass UOL

## Pré-requisitos

- JDK 17
- Git

## Clonar e rodar

```powershell
git clone https://github.com/jadsonti/digital-bank-api.git
cd digital-bank-api
.\mvnw.cmd spring-boot:run
```

Com `JAVA_HOME` explícito (Windows):

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
.\mvnw.cmd spring-boot:run
```

| Recurso | URL |
|---------|-----|
| API | http://localhost:8080 |
| Swagger | http://localhost:8080/swagger-ui.html |
| H2 Console | http://localhost:8080/h2-console |

H2: JDBC `jdbc:h2:mem:bankdb`, usuário `sa`, senha vazia.

## Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/api/contas` | Cria conta |
| GET | `/api/contas` | Lista contas |
| GET | `/api/contas/{id}` | Busca conta |
| GET | `/api/contas/{id}/movimentacoes` | Movimentações |
| GET | `/api/contas/{id}/notificacoes` | Notificações |
| POST | `/api/transferencias` | Transferência |

Na subida, o projeto insere 3 contas de exemplo (ids 1, 2 e 3).

## Testes

```powershell,
.\mvnw.cmd test
```

## Decisões de arquitetura

**Camadas** — Controller (HTTP + `@Valid`), Service (negócio + transação), Repository (JPA), Domain (regras em `Conta`), DTOs em records separados do domínio.

**Transferência** — `@Transactional`; lock pessimista nas contas; ordem fixa de IDs (menor primeiro) para evitar deadlock; `@Version` em `Conta` para concorrência otimista.

**Movimentações** — cada transferência gera débito e crédito com o mesmo `idTransferencia` (UUID), formando um ledger consultável.

**Notificações** — interface `NotificacaoService` (DIP); implementação persiste e registra log, permitindo trocar o canal depois sem alterar `TransferenciaService`.

**Banco** — H2 em memória para esse teste, em produção, eu usaria PostgreSQL com Flyway/Liquibase.
