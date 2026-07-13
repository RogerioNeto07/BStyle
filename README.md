# BStyle

## Descrição:
O sistema consiste em um catálogo virtual para um brechó online. O sistema permite que os usuários cadastrem seus produtos e que os clientes acessem a plataforma para ver os produtos disponíveis.

## Modelo Lógico:
<img width="954" height="840" alt="BStyleModeloLógico" src="https://github.com/user-attachments/assets/78d55290-7540-4bbd-87b9-5ce1c7d634d4" />
" />

## Endpoints:
API disponível em: http://localhost:8080

### Produtos:
| Método   | Endpoint                  | Descrição                                            |
| -------- | ------------------------- | ---------------------------------------------------- |
| `GET`    | `/produtos`               | Lista todos os produtos da vendedora                 |
| `GET`    | `/produtos/:id`           | Detalhes de um produto  |
| `POST`   | `/produtos`               | Cadastra um novo produto                             |
| `PUT`    | `/produtos/:id`           | Atualiza informações do produto                      |
| `DELETE` | `/produtos/:id`           | Remove (ou desativa) um produto                      |
| `GET`    | `/produtos/tipo/:tipo_id` | Lista produtos por tipo                              |
| `GET`    | `/produtos/tag/:tag_id`   | Lista produtos por tag                               |

### Busca/Exibição:
| Método | Endpoint                  | Descrição                                   |
| ------ | ------------------------- | ------------------------------------------- |
| `GET`  | `/produtos/tipo/:tipo_id` | Lista produtos de um tipo específico        |
| `GET`  | `/produtos/tag/:tag_id`   | Lista produtos de uma tag específica        |
| `GET`  | `/feed`                   | Lista produtos mais recentes (feed público) |

### Tags:
| Método   | Endpoint    | Descrição                   |
| -------- | ----------- | --------------------------- |
| `GET`    | `/tags`     | Lista todas as tags         |
| `GET`    | `/tags/:id` | Retorna detalhes de uma tag |
| `POST`   | `/tags`     | Cria uma nova tag           |
| `PUT`    | `/tags/:id` | Atualiza nome de uma tag    |
| `DELETE` | `/tags/:id` | Exclui uma tag              |

### Tipos:
| Método   | Endpoint     | Descrição                        |
| -------- | ------------ | -------------------------------- |
| `GET`    | `/tipos`     | Lista todos os tipos de produtos |
| `GET`    | `/tipos/:id` | Detalhes de um tipo              |
| `POST`   | `/tipos`     | Cria um tipo de produto          |
| `PUT`    | `/tipos/:id` | Atualiza nome do tipo            |
| `DELETE` | `/tipos/:id` | Exclui um tipo                   |

### Cores:
| Método   | Endpoint     | Descrição                        |
| -------- | ------------ | -------------------------------- |
| `GET`    | `/cores`     | Lista todos as cores |
| `GET`    | `/cores/:id` | Detalhes de uma cor              |
| `POST`   | `/cores`     | Cria uma cor          |
| `PUT`    | `/cores/:id` | Atualiza nome da cor            |
| `DELETE` | `/cores/:id` | Exclui uma cor                   |

### Avaliações:
| Método   | Endpoint     | Descrição                        |
| -------- | ------------ | -------------------------------- |
| `GET`    | `/avaliacoes`     | Lista todas as avaliacoes |
| `POST`   | `/avaliacoes`     | Cria uma avaliacao          |

### Denúncias:
| Método   | Endpoint     | Descrição                        |
| -------- | ------------ | -------------------------------- |
| `GET`    | `/denuncias`     | Lista todas as denuncias |
| `POST`   | `/denuncias`     | Cria uma denuncia          |

## Executando com Docker
Certifique-se de ter Docker e Docker Compose instalados.
Para construir e subir a imagem do projeto rode o comando:
```sh
docker compose up -d --build
