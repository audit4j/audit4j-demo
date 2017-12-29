package org.audt4j.demo.spring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.audt4j.demo.spring.model.Payment;
import org.audt4j.demo.spring.service.InventoryService;
import org.audt4j.demo.spring.service.Item;
import org.audt4j.demo.spring.service.PaymentService;
import org.audt4j.demo.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuditDemoController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/testWithoutAnnotations", method = RequestMethod.GET)
    public String testWithoutAnnotations() {
        inventoryService.addItem(new Item("Iphone6", 600.5));
        return "redirect:/demo";
    }

    @RequestMapping(value = "/testClassAnnotation", method = RequestMethod.GET)
    public String testClassAnnotation() {
        userService.getUserByuserName("admin");

        return "redirect:/demo";
    }

    @RequestMapping(value = "/testMethodAnnotationWithoutMark", method = RequestMethod.GET)
    public String testMethodAnnotationWithoutMark() {
        paymentService.checkout(new Payment("john", 600.5, new Item("Iphone6", 600.5)));

        return "redirect:/demo";
    }
    
    
    @RequestMapping(value = "/testMethodAnnotationMarked", method = RequestMethod.GET)
    public String testMethodAnnotationMarked() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Iphone6", 600.5));

        paymentService.savePayment("john", items);
        return "redirect:/demo";
    }
    
    
    @RequestMapping(value = "/testIgnoreAuditAnnotation", method = RequestMethod.GET)
    public String testIgnoreAuditAnnotation() {

        userService.changePassword("123", "abc");
        return "redirect:/demo";
    }
    
    
    @RequestMapping(value = "/testDeidentifyAnnotation", method = RequestMethod.GET)
    public String testDeidentifyAnnotation() {

       paymentService.saveCreditCard("John Doyl", new Date(2020, 10, 12), "2344343576543354");
        return "redirect:/demo";
    }
}
