package com.example_jvb.demo_jvb.controller;

import com.example_jvb.demo_jvb.dto.CreateProductDTO;
import com.example_jvb.demo_jvb.dto.ProductDTO;
import com.example_jvb.demo_jvb.model.Category;
import com.example_jvb.demo_jvb.model.Product;
import com.example_jvb.demo_jvb.services.CategoryService;
import com.example_jvb.demo_jvb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Convertir de Product a ProductDTO
    private ProductDTO toDTO(Product product) {
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : "Sin categoría";
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), categoryName);
    }

    // Obtener todos los productos como DTOs
    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<ProductDTO> dtos = productService.listProducts().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDTO(product));
    }

    // Crear un nuevo producto desde un CreateProductDTO
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody CreateProductDTO dto) {
        try {
            Category category = categoryService.findById(dto.getCategoryId());
            if (category == null) {
                return ResponseEntity.badRequest().body("Categoría no encontrada");
            }

            Product product = new Product(null, dto.getName(), dto.getPrice(), dto.getStock(), dto.getFabricationCost(), category);
            productService.save(product);
            return new ResponseEntity<>(toDTO(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto", HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody CreateProductDTO dto) {
        try {
            Product product = productService.findById(id);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            Category category = categoryService.findById(dto.getCategoryId());
            if (category == null) {
                return ResponseEntity.badRequest().body("Categoría no encontrada");
            }

            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());
            product.setFabricationCost(dto.getFabricationCost());
            product.setCategory(category);

            productService.save(product);
            return ResponseEntity.ok(toDTO(product));
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
