# 🚗 API Oficina Mecânica

Projeto desenvolvido em **Java + Spring Boot**, com objetivo de criar um **CRUD de Serviços da Oficina Mecânica**, aplicando boas práticas com uso de **DTOs**.

---

## 📌 Tecnologias utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **JUnit 5**

---

## 📁 Estrutura de Pacotes
```
src/main/java/io/github/usuario/api_oficina/
├── controller # Endpoints REST
├── dto # DTOs (Request e Response)
├── model # Entidades (JPA)
├── repository # Interfaces Repository
└── service # Regras de negócio
```
---
📦 DTOs

- ServicoRequestDTO → usado para entrada de dados (POST, PUT)

- ServicoResponseDTO → usado para retorno (GET)

---
## ⚙️ Service (Regras de Negócio)

- Criar serviço

- Listar todos

- Buscar por ID

- Atualizar serviço

- Excluir serviço
---
## 🌐 Controller (Endpoints REST)
- Método	Endpoint	Descrição
- POST	/servicos	Criar serviço
- GET	/servicos	Listar todos
- GET	/servicos/{id}	Buscar por ID
- PUT	/servicos/{id}	Atualizar serviço
- DELETE	/servicos/{id}	Excluir serviço
---
## 🛢️ Banco de Dados H2

Configuração no application.yml:
```
spring:
  datasource:
    url: jdbc:h2:mem:oficina
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
```
Acesse o console em:
👉 http://localhost:8080/h2-console

---
## 🧪 Testes com JUnit

Exemplo de teste unitário para o ServicoService:
```
@SpringBootTest
class ServicoServiceTest {

    @Autowired
    private ServicoService servicoService;

    @Test
    void deveCriarServicoComSucesso() {
        ServicoRequestDTO dto = new ServicoRequestDTO();
        dto.setDescricao("Troca de óleo");
        dto.setValor(BigDecimal.valueOf(120.0));
        dto.setStatus("ABERTO");

        ServicoResponseDTO response = servicoService.criar(dto);

        assertNotNull(response.getId());
        assertEquals("Troca de óleo", response.getDescricao());
        assertEquals("ABERTO", response.getStatus());
    }
}
```
---
## 📌 Testes manuais

- Utilize Postman ou Insomnia para validar os endpoints.

- Exemplo de payload para criação de serviço:
```
{
  "descricao": "Troca de pastilha de freio",
  "valor": 250.0,
  "status": "ABERTO"
}
```
---
## 🚀 Como executar

1- Clone o repositório:
```
git clone https://github.com/usuario/api_oficina.git
```
2- Acesse a pasta do projeto:
```
cd api_oficina
```
3- Execute a aplicação:
```
./mvnw spring-boot:run
```
4- Acesse a API em:
```
http://localhost:8080/servicos
```
---
