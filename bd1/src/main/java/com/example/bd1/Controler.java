package com.example.bd1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class Controler {
    private ClientsRepo clientsRepo;
    @Autowired
    public Controler(ClientsRepo clientsRepo) {
        this.clientsRepo = clientsRepo;
    }
    @RequestMapping("/add")
    public String addData(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("socialMedia") String socialMedia, Model model)
            throws Exception {
        Clients clients = new Clients(firstName, lastName, mobileNumber, socialMedia, true);
        clientsRepo.save(clients);
        model.addAttribute("clients", clients);
        return "redirect:/show";
    }

    @RequestMapping("/show")
    public String show(Model model) {
        model.addAttribute("clients", clientsRepo.findAll());
            return "show";
    }
    @RequestMapping("/delete")
    public String kasuj(@RequestParam("id") Integer id, Model model){
        clientsRepo.deleteById(id);
        model.addAttribute("clients", clientsRepo.findAll());

        return "show";
    }
    @RequestMapping("/find")
    public String find(@RequestParam("lastName") String lastName, Model model){
        model.addAttribute("clients", clientsRepo.findAllBylastName(lastName));
        return "show";
    }
    @RequestMapping("/redirect")
    public String redirect(
            @RequestParam("id") Integer id, Model model
    )
            throws Exception {
        model.addAttribute("clients", clientsRepo.findById(id));
        return "update";
    }

    @RequestMapping("/update")
    public String update(
            @RequestParam("id") Integer id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("socialMedia") String socialMedia, Model model)
            throws Exception {
        Clients clients = new Clients(id, firstName, lastName, mobileNumber, socialMedia, true);
        clientsRepo.save(clients);
        model.addAttribute("clients", clients);
        return "show";
    }


}

