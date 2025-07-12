# Municípios Brasileiros 🇧🇷

Projeto acadêmico desenvolvido com base nos dados do IBGE (2022) para explorar funcionalidades de consulta, filtro e agregação de informações sobre os municípios brasileiros.

## 📚 Tecnologias Utilizadas

- **Java 11+**
- **MySQL**
- **JDBC**
- **Swing (interface gráfica)**
- **MVC + DAO + Repository Pattern**
- **CSV (dados de entrada)**

---

## 🎯 Objetivo

Criar uma aplicação desktop (interface gráfica e console) estruturada em **MVC**, com persistência via **MySQL**, usando os padrões de projeto **DAO** e **Repository**. O sistema permite consultas sobre os municípios brasileiros com base no arquivo:

📄 `2022_IBGE -Municipios.csv`

---

## ⚙️ Instalação e Execução

### 1. Configurar o banco MySQL

Execute o script `cidades.sql` no seu MySQL:

```sql
CREATE DATABASE IF NOT EXISTS cidades;

USE cidades;

CREATE TABLE IF NOT EXISTS municipios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    estado VARCHAR(100),
    codigo_estado INT,
    codigo_municipio INT,
    nome_municipio VARCHAR(200),
    capital BOOLEAN,
    populacao INT
);
2. Importar os Dados do CSV
Compile e execute o Main.java, que lê o CSV e popula o banco de dados:

javac -cp "lib/mysql-connector-j-9.3.0.jar" src/Main.java -d bin
java -cp "bin;lib/mysql-connector-j-9.3.0.jar" Main
💡 Ajuste as credenciais e caminhos no código se necessário:

Usuário/senha do MySQL: USER e PASSWORD

Caminho do arquivo: CSV_FILE

🧠 Funcionalidades da Aplicação
A aplicação pode ser executada por linha de comando ou por interface gráfica.

✔️ Funcionalidades Disponíveis
🔍 Buscar município pelo nome

📊 Ver população total de um estado

🏛️ Listar todas as capitais

📈 Listar municípios com população acima de um valor informado

📉 Listar municípios com população entre dois valores

🏙️ Para estados cuja capital não é a mais populosa, exibir a cidade mais populosa

🔟 Mostrar o Top 10 municípios mais populosos que não são capitais

💻 Modo Console
Execute o TelaPrincipal.java:

javac -cp "lib/mysql-connector-j-9.3.0.jar" -d bin src/view/TelaPrincipal.java
java -cp "bin;lib/mysql-connector-j-9.3.0.jar" view.TelaPrincipal
🖥️ Modo GUI
Execute o TelaMunicipiosGUI.java:

javac -cp "lib/mysql-connector-j-9.3.0.jar" -d bin src/view/TelaMunicipiosGUI.java
java -cp "bin;lib/mysql-connector-j-9.3.0.jar" view.TelaMunicipiosGUI
🏗️ Arquitetura
📌 Padrões de Projeto Utilizados
MVC (Model-View-Controller)
Estrutura modular entre visualização, lógica e dados.

DAO (Data Access Object)
MunicipioDAO.java gerencia a comunicação direta com o banco.

Repository
MunicipioRepository.java atua como camada intermediária entre Controller e DAO.

🔐 Configuração de Acesso ao Banco
Você pode alterar as configurações de conexão diretamente nos arquivos:

MunicipioDAO.java

Main.java

Exemplo:


private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cidades?useSSL=false&serverTimezone=America/Sao_Paulo";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";