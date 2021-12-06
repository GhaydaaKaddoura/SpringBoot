package com.example.SpringProject.Services;


import com.example.SpringProject.dao.ProductDao;
import com.example.SpringProject.dao.SalesDao;
import com.example.SpringProject.model.ActionResult;
import com.example.SpringProject.model.Product;
import com.example.SpringProject.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class SalesServices {

    ActionResult actionResult;

   // @Autowired
    private SalesDao salesDao;

    private Logger logger = LoggerFactory.getLogger(LogService.class);

    public ActionResult addSale(Sale sale) {
        try {
            sale.setTotal(sale.getPrice() * sale.getQuantity());
            salesDao.save(sale);
            actionResult = new ActionResult(0, "Success", sale);
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @CachePut(value = "sales", key = "#sale.id")
    public ActionResult updateSale(int id, Sale sale) {
        try {
            if (salesDao.findById(id) != null) {
                sale.setId(id);
                sale.setTotal(sale.getPrice() * sale.getQuantity());
                //log
                logger.info("Service: Fetching sale with id {} for update salenfo ", sale.getId());

                salesDao.save(sale);

                actionResult = new ActionResult(0, "Success", sale);
                //log
                logger.info("Service: Done updateSale {} " ,sale);

            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    @CacheEvict(value = "sales", key = "#id",allEntries=true)
    public ActionResult deleteSale(int id) {
        try {
            if (salesDao.findById(id) != null) {
                salesDao.deleteById(id);
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

    @CacheEvict(value = "AllParam", key = "#id",allEntries=true)
    public ActionResult getSales() {
        try {
            List<Sale> salesList = (List<Sale>) salesDao.findAll();
            if (!salesList.isEmpty()) {
                actionResult = new ActionResult(0, "Success", salesList);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    public ActionResult getSaleById(int id) {
        try {
            Optional<Sale> sale = salesDao.findById(id);
            if (sale != null) {
                actionResult = new ActionResult(0, "Success", sale);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }


    public ActionResult getSalesByProductId(int productId) {
        try {
            List<Sale> salesList = (List<Sale>) salesDao.findByProductId(productId);
            if (!salesList.isEmpty()) {
                actionResult = new ActionResult(0, "Success", salesList);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }


    public ActionResult getSalesByClientId(int clientId) {
        try {
            List<Sale> salesList = (List<Sale>) salesDao.findByClientId(clientId);
            if (!salesList.isEmpty()) {
                actionResult = new ActionResult(0, "Success", salesList);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }



    public ActionResult updateSaleQuantityAndPrice(Map<String, ?> jsonBody) {
        try {
            int saleId = (Integer) jsonBody.get("id");
            int quantity = (Integer) jsonBody.get("quantity");
            Double price = (Double) jsonBody.get("price");

            Optional<Sale> sale = salesDao.findById(saleId);
            if(sale == null){
                return new ActionResult(0, "sale num is not found", null);
            }

            //log
            logger.info("Service: Fetching sale with id {} for update salenfo quantity {} price {}", sale.get().getId(),sale.get().getQuantity(),sale.get().getPrice());

            sale.get().setQuantity(quantity);
            sale.get().setPrice(price);
            sale.get().setTotal(price * quantity);
            salesDao.save(sale.get());
            actionResult = new ActionResult(0, "Success", sale);
            //log
            logger.info("Service: Done updateSaleQuantityAndPrice ");

            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }



    public ActionResult updateSaleQuantity(Map<String, ?> jsonBody) {
        try {
            int saleId = (Integer) jsonBody.get("id");
            int quantity = (Integer) jsonBody.get("quantity");

            Optional<Sale> sale = salesDao.findById(saleId);
            if(sale == null){
                return new ActionResult(0, "sale num is not found", null);
            }

            //log
            logger.info("Service: Fetching sale with id {} for update salenfo quantity {} ", sale.get().getId(),sale.get().getQuantity());

            sale.get().setQuantity(quantity);
            sale.get().setTotal(sale.get().getPrice() * quantity);
            salesDao.save(sale.get());
            actionResult = new ActionResult(0, "Success", sale);
            //log
            logger.info("Service: Done updateSaleQuantity ");

            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }



    public ActionResult updateSalePrice(Map<String, ?> jsonBody) {
        try {
            int saleId = (Integer) jsonBody.get("id");
            Double price = (Double) jsonBody.get("price");

            Optional<Sale> sale = salesDao.findById(saleId);
            if(sale == null){
                return new ActionResult(0, "sale num is not found", null);
            }

            //log
            logger.info("Service: Fetching sale with id {} for update salenfo price {} ", sale.get().getId(),sale.get().getPrice());

            sale.get().setPrice(price);
            sale.get().setTotal(price * sale.get().getQuantity());
            salesDao.save(sale.get());
            actionResult = new ActionResult(0, "Success", sale);
            //log
            logger.info("Service: Done updateSalePrice ");

            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }
}
