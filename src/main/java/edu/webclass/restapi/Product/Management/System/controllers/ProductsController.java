package edu.webclass.restapi.Product.Management.System.controllers;

import edu.webclass.restapi.Product.Management.System.models.Product;
import edu.webclass.restapi.Product.Management.System.models.dto.ProductDto;
import edu.webclass.restapi.Product.Management.System.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{name}")
    public ProductDto getProduct(@PathVariable(name = "name") String title) {
        Optional<Product> product = productService.getProduct(title);
        return product.map(ProductDto::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/list")
    public List<ProductDto> listAllProducts(){
        return productService.findAllProducts().stream().map(product -> new ProductDto(product)).toList();
    }

    @PostMapping("/add")
    public boolean addProduct(@RequestHeader("name") String title,@RequestHeader String brand,@RequestHeader int price){
        return productService.addProduct(title,brand,price);
    }
}
