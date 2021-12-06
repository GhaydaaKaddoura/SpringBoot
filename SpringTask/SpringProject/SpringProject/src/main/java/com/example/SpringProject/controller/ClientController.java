package com.example.SpringProject.controller;

import com.example.SpringProject.Services.ClientServices;
import com.example.SpringProject.model.ActionResult;
import com.example.SpringProject.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    @PostMapping("/addClient")
    public ActionResult addClient(@RequestBody Client client) {
        return clientServices.addClient(client);
    }

    @PutMapping("/updateClient/{id}")
    public ActionResult updateClient(@PathVariable int id, @RequestBody Client client) {
        return clientServices.updateClient(id, client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public ActionResult deleteClient(@PathVariable int id) {
        return clientServices.deleteClient(id);
    }

    @GetMapping("/getClients")
    public ActionResult getClients() {
        return clientServices.getClients();
    }

    @GetMapping("/clients/{id}")
    public ActionResult getClientById(@PathVariable int id) {
        return clientServices.getClientById(id);
    }

}
