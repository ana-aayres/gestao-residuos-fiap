# Gestão de Resíduos
Aplicação para gerenciamento eficiente de resíduos.

## Pré-requisitos
- Maven
- Java 21
- Git
- Docker compose

## Build e execução
Para compilar e iniciar o projeto, execute os seguintes comandos:    
# Clonar o repositório
git clone https://github.com/ana-aayres/gestao-residuos-fiap.git
cd gestao-residuos-fiap

# Construir o projeto com Maven
mvn clean install

# Iniciar com Docker Compose
docker compose up --build

# Rodando testes
Para executar testes unitários e de integração, use:
mvn test
