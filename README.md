## Projeto Módulo 10 - Tópicos Avançados - Micro Serviço

### Requisitos funcionais

 - Criação de produtos;
 - Listagem dos produtos cadastrados;
 - Informar, na rota acima, dados do produto conforme filtro pelo código;
 - Compra do(s) produto(s);
 - Listagem das compras realizadas;
 - Informar, na rota acima, dados da compra conforme filtro pelo cpf;
 - Criação de usuário;
 - Proteção das rotas possibilitando acesso apenas por usuários cadastrados.

#### Requisitos não funcionais

 - Padrão de arquitetura REST;
 - Arquitetura de micro serviço;
 - Serviço de mensageria; 
 - Autenticação de rotas;
 - Banco de dados relacional.
 

### Tecnologias
- Java
- Kafka
- Banco de dados PostgreSQL
- Maven


### Produtos

#### Criar produtos

##### Criação do produto/JSON
 - (POST) `localhost:8081/produto`
```json
{
	"nome": "Smart Watch",
	"preco": 1500.00,
	"qtdeDisponivel": 22
}
```
##### Retorno da aplicação/JSON
```json
{
	"codigo": "V905",
	"nome": "Smart Watch",
	"preco": 1500.0,
	"qtde_disponivel": 22
}
```

#### Listar os produtos cadastrados

##### Obter a listagem total dos produtos (paginada)
 - (GET) `localhost:8081/produto`

##### Obter um produto especificando seu código
 - (GET) `localhost:8081/produto?codigo={codigoDoProduto}`

##### Retorno da aplicação/JSON
```json
{
	"content": [
		{
			"codigo": "V905",
			"nome": "Smart Watch",
			"preco": 1500.0,
			"qtde_disponivel": 22
		}
	],
	"pageable": {
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 20,
		"unpaged": false,
		"paged": true
	},
	"last": true,
	"totalElements": 1,
	"totalPages": 1,
	"size": 20,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 1,
	"empty": false
}
```

#### Compra

##### Criação do produto/JSON
 - (POST) `localhost:8082/compra`

```json
{
	"data": "2022-04-02T13:34:00.000",
	"cpf": "123456789",
	"produtos": 
		{
			"V905": 2
		}
}
```

#### Listar compras 

##### Obter a listagem total dos produtos (paginada)
 - (GET) `localhost:8082/compra`

##### Obter um produto especificando seu código
 - (GET) `localhost:8082/compra?cpf={cpfDoCliente}`

##### Retorno da aplicação/JSON
```json
{
	"content": [
		{
            "data_compra": "2022-04-02T13:34:00",
            "cpf_cliente": "123456789",
            "valor_total_compra": 3000.0,
            "status": "CONCLUIDO",
			"produtos": [
				{
					"codigo": "V905",
					"nome": "Smart Watch",
					"preco_unitario": 1500.0,
					"quantidade": 2
				}
			]
		}
	],
	"pageable": {
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 20,
		"unpaged": false,
		"paged": true
	},
	"last": true,
	"totalElements": 1,
	"totalPages": 1,
	"size": 20,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 1,
	"empty": false
}
```


#### Criação de usuário

##### Criação do user/JSON
 - (POST) `localhost:8083/user`
```json
{
  "userName": "user04",
  "password": "user04",
  "authority": ["CLIENTE", "ADMIN"],
  "enabled": true
}
```


#### Login

##### Autenticação do user/JSON
 - (POST) `localhost:8083/user/login`
```json
{
  "userName": "user04",
  "password": "user04"
}
```

##### Retorno da aplicação/JSON
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJNeUFQUCIsInN1YiI6InVzZXIwNCIsImlhdCI6MTY1MTM0NjQxOSwiZXhwI
  joxNjUxMzUwMDE5LCJyb2xlIjoiUk9MRV9BRE1JTiJ9.OcM8L7vLKrBKUBM9uwJQAFuD7PVJuOEH8OW7ksXCK2I"
}
```

##### Retorno da aplicação
	Status Code - 200 - OK
	Status Code - 201 - Created
	Status Code - 400 - Bad Request Error
	Status Code - 500 - Internal Server Error
