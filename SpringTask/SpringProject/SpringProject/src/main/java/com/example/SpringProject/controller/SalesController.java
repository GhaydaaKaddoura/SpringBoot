package com.example.SpringProject.controller;


import com.example.SpringProject.Services.SalesServices;
import com.example.SpringProject.model.ActionResult;
import com.example.SpringProject.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesServices salesServices;

    @PostMapping("/addSale")
    public ActionResult addSale(@RequestBody Sale sale) {
        return salesServices.addSale(sale);
    }

    @PutMapping("/updateSale/{id}")
    public ActionResult updateSale(@PathVariable int id, @RequestBody Sale sale) {
        return salesServices.updateSale(id, sale);
    }

    @DeleteMapping("/deleteSale/{id}")
    public ActionResult deleteSale(@PathVariable int id) {
        return salesServices.deleteSale(id);
    }

    @GetMapping("/getSales")
    @Cacheable(value="salesApi")
    public ActionResult getSales() {
        return salesServices.getSales();
    }

    @GetMapping("/sales/{id}")
    public ActionResult getSaleById(@PathVariable int id) {
        return salesServices.getSaleById(id);
    }

    @GetMapping("/saleByProduct/{productId}")
    public ActionResult getSaleByProductId(@PathVariable int productId) {
        return salesServices.getSalesByProductId(productId);
    }

    @GetMapping("/saleByClient/{clientId}")
    public ActionResult getSaleByClientId(@PathVariable int clientId) {
        return salesServices.getSalesByClientId(clientId);
    }

    @PostMapping("/updateSaleQuantity")
    private ActionResult updateSaleQuantity(@RequestBody Map<String, ?> jsonBody) {
        return salesServices.updateSaleQuantity(jsonBody);
    }

    @PostMapping("/updateSalePrice")
    private ActionResult updateSalePrice(@RequestBody Map<String, ?> jsonBody) {
        return salesServices.updateSalePrice(jsonBody);
    }

    @PostMapping("/updateSaleQuantityAndPrice")
    private ActionResult updateSaleQuantityAndPrice(@RequestBody Map<String, ?> jsonBody) {
        return salesServices.updateSaleQuantityAndPrice(jsonBody);
    }

}
