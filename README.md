Workshop criado com base na aula de Spring Boot do curso Java Completo - Nélio Alves. Estou fazendo melhorias e implementações mais robustas, como tratamento de exceções, endpoints concisos, utilização de containers do Docker para a implementação do PostgreSQL e PgAdmin, e a utilização do Swagger para uma melhor visualização do mapeamento das classes.

```mermaid
classDiagram
    class User {
        - id: Integer
        - name: String
        - email: String
        - phone: String
        - password: String
    }

    class Order {
        - id: Integer
        - moment: Date
        - orderStatus: OrderStatus
        + total(): double
    }

    class Product {
        - id: Integer
        - name: String
        - description: String
        - price: Double
        - imgUrl: String
    }

    class OrderItem {
        - quantity: Integer
        - price: Double
        + subTotal(): double
    }

    class Payment {
        - id: Integer
        - moment: Date
    }

    class Category {
        - id: Integer
        - name: String
    }

    class OrderStatus {
        <<enum>>
        - WAITING_PAYMENT: int
        - PAID: int
        - SHIPPED: int
        - DELIVERED: int
        - CANCELED: int
    }

    User "1" --o "*" Order : "orders"
    Order "1" --o "0..1" Payment : "payment"
    Order "1" --o "*" OrderItem : "items"
    OrderItem "*" --o "1" Product : "products"
    Product "*" --o "*" Category : "categories"
    Order "1" --o "*" OrderItem : "orders"
    Order "1" --o "1" User : "client"
```
