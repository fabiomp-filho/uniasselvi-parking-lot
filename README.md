# Parking Lot Application - Docker Setup

Este projeto utiliza o Docker Compose para criar e executar dois contêineres: um contêiner MySQL para o banco de dados e um contêiner para a aplicação. Siga os passos abaixo para configurar e executar o ambiente.

## Pré-requisitos

Certifique-se de que você tem as seguintes ferramentas instaladas em sua máquina:

- [Docker](https://www.docker.com/get-started) (Versão 20.10 ou superior)
- [Docker Compose](https://docs.docker.com/compose/install/) (Versão 1.29 ou superior)

## Como executar o ambiente

1. **Clone o repositório** (ou tenha o arquivo `compose.yaml` em sua máquina local):

   ```bash
   git clone https://github.com/fabiomp-filho/uniasselvi-parking-lot.git
   cd /parking-lot
   ```

2. **Certifique-se de que o `compose.yaml` está configurado corretamente**:

   O arquivo `compose.yaml` deve estar configurado corretamente na raiz do projeto. Ele contém os serviços necessários:

   - **MySQL**: Banco de dados MySQL 8.0
   - **App**: O serviço da aplicação rodando na porta `8080`.

3. **Inicialize o ambiente com Docker Compose**:

   Execute o seguinte comando no diretório raiz do projeto:

   ```bash
   docker-compose up --build
   ```

   Isso fará o seguinte:
   - Baixará a imagem MySQL 8.0.
   - Criará volumes persistentes para os dados do banco de dados.
   - Executará a aplicação e o banco de dados na rede definida.

4. **Acessar a aplicação**:

   - A aplicação estará disponível em [http://localhost:8080](http://localhost:8080) após a inicialização.
   
   - O banco de dados MySQL estará acessível na porta `3307` da sua máquina host, com as seguintes credenciais:
     - **Usuário**: `root`
     - **Senha**: `root`
     - **Banco de dados**: `db_parking_lot`

5. **Parar o ambiente**:

   Para parar os contêineres, execute:

   ```bash
   docker-compose down
   ```

   Isso irá parar e remover os contêineres, mas os volumes persistentes ainda manterão os dados do banco de dados.

6. **Reiniciar o ambiente**:

   Para reiniciar o ambiente, você pode executar novamente o comando:

   ```bash
   docker-compose up
   ```

## Solução de Problemas

- **Erro de porta em uso**: Se a porta `8080` ou `3307` já estiver em uso, você pode modificar as portas no arquivo `compose.yaml`:
  
  ```yaml
  ports:
    - "8081:8080"  # Modifique para uma porta disponível
  ```

- **Problemas com volumes**: Caso o banco de dados não inicie corretamente, tente remover o volume de dados existente e reiniciar o ambiente:

  ```bash
  docker-compose down -v
  docker-compose up --build
  ```

Isso removerá o volume existente e criará um novo na próxima execução.

---
