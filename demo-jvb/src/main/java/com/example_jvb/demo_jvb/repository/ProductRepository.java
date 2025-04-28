package com.example_jvb.demo_jvb.repository; // Paquete donde se encuentra el repositorio

import com.example_jvb.demo_jvb.model.Product; // Importa la clase Product (la entidad)
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base de JPA para CRUD
import org.springframework.stereotype.Repository; // Marca esta interfaz como un componente de Spring

@Repository // Anotación que indica que esta interfaz es un repositorio de Spring
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Esta interfaz hereda métodos CRUD y más: save, findById, findAll, deleteById, etc.
    // El primer parámetro es la entidad (Product)
    // El segundo es el tipo de su clave primaria (Integer)

    // Aquí puedes añadir métodos personalizados si quieres, por ejemplo:
    // List<Product> findByName(String name);
    // List<Product> findByPriceLessThan(double price);
}
