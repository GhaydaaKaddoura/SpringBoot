package com.example.SpringProject.Services;

import com.example.SpringProject.dao.ClientDao;
import com.example.SpringProject.model.ActionResult;
import com.example.SpringProject.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientServices {

    ActionResult actionResult;

  //  @Autowired
    private ClientDao clientDao;

    public ActionResult addClient(Client client) {
        try {
            clientDao.save(client);
            actionResult = new ActionResult(0, "Success", client);
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }



    public ActionResult updateClient(int id, Client client) {
        try {
            if (clientDao.findById(id) != null) {
                client.setId(id);
                clientDao.save(client);
                actionResult = new ActionResult(0, "Success", client);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    public ActionResult deleteClient(int id) {
        try {
            if (clientDao.findById(id) != null) {
                clientDao.deleteById(id);
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

    public ActionResult getClients() {
        try {
            List<Client> clientList = (List<Client>) clientDao.findAll();
            if (!clientList.isEmpty()) {
                actionResult = new ActionResult(0, "Success", clientList);
            } else {
                actionResult = new ActionResult(0, "No data found", "");
            }
            return actionResult;
        } catch (Exception ex) {
            actionResult = new ActionResult(-99, ex.getMessage(), null);
            return actionResult;
        }
    }

    public ActionResult getClientById(int id) {
        try {
            Optional<Client> client = clientDao.findById(id);
            if (client != null) {
                actionResult = new ActionResult(0, "Success", client);
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
