package com.example_jvb.demo_jvb.services; // Paquete del servicio

import com.example_jvb.demo_jvb.model.Product; // Importa la entidad Product
import com.example_jvb.demo_jvb.repository.ProductRepository; // Importa el repositorio
import org.springframework.beans.factory.annotation.Autowired; // Para inyección de dependencias
import org.springframework.stereotype.Service; // Marca la clase como un servicio de Spring

import java.util.List; // Para trabajar con listas

@Service // Declara esta clase como un servicio gestionado por Spring (inyección de dependencias, etc.)
public class ProductService {

    @Autowired // Inyecta automáticamente una instancia de ProductRepository
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        // Devuelve todos los productos de la base de datos
        return productRepository.findAll();
    }

    public void save(Product product) {
        // Guarda un producto nuevo o actualiza uno existente
        productRepository.save(product);
    }

    public Product findById(Integer id) {
        // Busca un producto por su ID. Si no existe, devuelve null
        return productRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        // Elimina un producto por su ID
        productRepository.deleteById(id);
    }
}
