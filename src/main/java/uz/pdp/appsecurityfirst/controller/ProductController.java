package uz.pdp.appsecurityfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appsecurityfirst.entity.Product;
import uz.pdp.appsecurityfirst.repo.ProductRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepo productRepo;
    @GetMapping
    public HttpEntity<?> getAllProduct(){
        List<Product> productList =productRepo.findAll();
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(  productRepo.save(product));
    }
    @PutMapping("/{id}")
    public HttpEntity<?>  editProduct(@PathVariable Integer id , @RequestBody Product product){
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()){
            Product editproductt =optionalProduct.get();
            editproductt.setName(product.getName());
            productRepo.save(editproductt);
            return ResponseEntity.ok(editproductt);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id ){
        productRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepo.findById(id);
        return ResponseEntity.status(optionalProduct.isPresent()?200:404).body(optionalProduct.orElse(null));
    }

}
