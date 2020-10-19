package com.PortfolioAction.PortfolioAction.controller;

import com.PortfolioAction.PortfolioAction.model.PortFolioActionResult;
import com.PortfolioAction.PortfolioAction.model.PortFolioSource;
import com.PortfolioAction.PortfolioAction.model.PortFolioTarget;
import com.PortfolioAction.PortfolioAction.service.PortFolioActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PortFolioActionController {
    @Autowired
    PortFolioActionService service;

    @GetMapping("/")
    public String Welcome(){

        return "Use http://localhost:8087/PortFolioAction for Today's action";

    }

    @GetMapping("/PortFolioAction")
    public List<PortFolioActionResult> doService(){
       return service.getActionResult();
    }

    @GetMapping("/PortFolioSource")
    public List<PortFolioSource> showSource(){

       return service.getSource();


    }

    @GetMapping("/PortFolioTarget")
    public List<PortFolioTarget> showTarget(){

        return service.getTarget();


    }

}
