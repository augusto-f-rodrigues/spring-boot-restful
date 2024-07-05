package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping()
    public ResponseEntity<ProductModel> createProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping()
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            for (ProductModel productModel : productList) {
                productModel.add(linkTo(methodOn(ProductController.class).getProductById(productModel.getProductId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var productModel = product.get();
        productModel.add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Product List"));
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") UUID id,
                                                      @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
