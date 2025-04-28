package com.example_jvb.demo_jvb.model; // Paquete donde se encuentra la clase

import jakarta.persistence.*; // Importa anotaciones de JPA (para la conexión con la BD)
import lombok.AllArgsConstructor; // Constructor con todos los argumentos
import lombok.Data; // Genera getters, setters, equals, hashCode, toString...
import lombok.NoArgsConstructor; // Constructor sin argumentos

@Entity // Marca esta clase como una entidad JPA (una tabla en la base de datos)
@Data // Anotación de Lombok para generar automáticamente getters/setters y más
@AllArgsConstructor // Constructor con todos los campos
@NoArgsConstructor // Constructor vacío

@Table(name="productos") // Define el nombre de la tabla en la base de datos

public class Product {

    @Id
    // Marca este campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Autoincremental (propio de MySQL)
    private Integer id;
    // Clave primaria autogenerada

    private String name;
    // Nombre del producto
    private double price;
    // Precio de venta del producto

    // Nuevos campos añadidos relacionados con inventario y fabricación
    private Integer stock;
    // Cantidad en inventario
    private double fabricationCost;
    // Coste de fabricación del producto

    @ManyToOne
    // Relación muchos productos -> una categoría
    @JoinColumn(name = "category_id")
    // Clave foránea que referencia a la tabla Category
    private Category category;
    // Categoría a la que pertenece el producto

    // No se necesita escribir manualmente constructores, getters ni setters gracias a Lombok
}
