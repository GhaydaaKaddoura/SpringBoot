package com.example.SpringProject.controller;



import com.example.SpringProject.Services.ProductServices;
import com.example.SpringProject.model.ActionResult;
import com.example.SpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping("/addProduct")
    public ActionResult addProduct(@RequestBody Product product) {
        return productServices.addProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public ActionResult updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productServices.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ActionResult deleteProduct(@PathVariable int id) {
        return productServices.deleteProduct(id);
    }

    @GetMapping("/getProducts")
    public ActionResult getProducts() {
        return productServices.getProducts();
    }

    @GetMapping("/products/{id}")
    public ActionResult getProductById(@PathVariable int id) {
        return productServices.getProductById(id);
    }

}
