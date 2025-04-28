package com.example_jvb.demo_jvb.controller;

import com.example_jvb.demo_jvb.dto.CategoryDTO;
import com.example_jvb.demo_jvb.model.Category;
import com.example_jvb.demo_jvb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Convertir una entidad Category a DTO
    private CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    // Convertir un DTO a entidad Category
    private Category toEntity(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName(), dto.getDescription(), null);
    }

    // Obtener todas las categorías como DTOs
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        List<CategoryDTO> dtos = categoryService.listCategories().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDTO(category));
    }

    // Crear una nueva categoría desde un DTO
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO dto) {
        try {
            Category category = toEntity(dto);
            categoryService.save(category);
            return new ResponseEntity<>(toDTO(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO dto) {
        try {
            Category currentCategory = categoryService.findById(id);
            if (currentCategory == null) {
                return ResponseEntity.notFound().build();
            }
            currentCategory.setName(dto.getName());
            currentCategory.setDescription(dto.getDescription());
            categoryService.save(currentCategory);
            return ResponseEntity.ok(toDTO(currentCategory));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
