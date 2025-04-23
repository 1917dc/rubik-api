# Api de login template

## Proposta do projeto:
A ideia do repositório é armazenar código de uma api básica com login e registro usando spring-security e OAuth2, basicamente evitando repetição de código sempre que começar um projeto que tenha como objetivo utilizar segurança com essas duas tecnologias.

## Como utilizar o template:
Em geral tudo está com o nome do template portanto é importante passar nas classes e mudar o nome para o projeto que está sendo feito.
- DataSource
- OpenApiConfiguration (Caso queira manter o Swagger)
- O nome do pacote principal  

Como posso ter esquecido de alguma é sempre bom passar em todas dando uma olhada.

### Geração de chaves RSA:
Por motivos de segurança as chaves RSA não são salvas no repositório, portanto é necessário realizar a geração delas e colocar na pasta de resources.  

#### Docker:
Caso você esteja rodando seu projeto exclusivamente em **docker** não precisa se preocupar com isso pois o Dockerfile garante a geração de uma nova key sempre que é iniciado (Portanto tokens gerados antes de reiniciar o Docker se tornam inválidos).  

#### Linux:
Caso você esteja rodando localmente em **linux** basta usar os comandos
```
openssl genpkey -algorithm RSA -out ./app.key -pkeyopt rsa_keygen_bits:2048
```
Para a key privada e
```
openssl rsa -pubout -in ./app.key -out ./app.public
```

#### Windows:
No **windows** existem algumas formas mas acredito que dado os requisitos do projeto é muito mais simples gerar uma chave com o mesmo método do **linux** utilizando os comandos dentro de um container docker e depois extrair as chaves geradas. 

#### MacOS:
Compre um computador de gente.


## Docker
A aplicação utiliza de um banco MySQL que pode ser utilizado localmente ou pelo docker-compose do próprio projeto. O docker-compose foi criado para rodar o projeto no estado atual, existe a possibilidade  
de alterações realizadas no projeto quebrarem o mesmo.

Caso queira subir o projeto como um todo em Docker utilize o comando normal do compose  
```
docker-compose up --build -d
```

Caso queira subir apenas o banco utilize o mesmo compose porém adicione o nome do serviço que quer subir no fim (no caso "mysql")
```
docker-compose up --build -d mysql
```

É importante checar o application.properties em caso de erro de conexão com o banco. Provavelmente alterações na url disponível lá devem ser feitas dependendo da sua configuração de mysql.
