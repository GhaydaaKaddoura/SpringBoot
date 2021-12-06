package com.example.SpringProject.Services;


import com.example.SpringProject.dao.ProductDao;
import com.example.SpringProject.model.ActionResult;
import com.example.SpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServices {

    ActionResult actionResult;

   // @Autowired
    private ProductDao productDao;

    public ActionResult addProduct(Product product) {
        try {
            productDao.save(product);
            actionResult = new ActionResult(0, "Success", product);
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }



    public ActionResult updateProduct(int id, Product product) {
        try {
            if (productDao.findById(id) != null) {
                product.setId(id);
                productDao.save(product);
                actionResult = new ActionResult(0, "Success", product);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    public ActionResult deleteProduct(int id) {
        try {
            if (productDao.findById(id) != null) {
                productDao.deleteById(id);
                actionResult = new ActionResult(0, "Success", "");
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    public ActionResult getProducts() {
        try {
            List<Product> productList = (List<Product>) productDao.findAll();
            if (!productList.isEmpty()) {
                actionResult = new ActionResult(0, "Success", productList);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    public ActionResult getProductById(int id) {
        try {
            Optional<Product> product = productDao.findById(id);
            if (product != null) {
                actionResult = new ActionResult(0, "Success", product);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }
}
