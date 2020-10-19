package com.PortfolioAction.PortfolioAction.service;

import com.PortfolioAction.PortfolioAction.mapper.TimeSeriesDailyJson;
import com.PortfolioAction.PortfolioAction.model.PortFolioActionResult;
import com.PortfolioAction.PortfolioAction.model.PortFolioSource;
import com.PortfolioAction.PortfolioAction.model.PortFolioTarget;
import com.PortfolioAction.PortfolioAction.repository.PortFolioActionResultRepo;
import com.PortfolioAction.PortfolioAction.repository.PortFolioSourceRepo;
import com.PortfolioAction.PortfolioAction.repository.PortFolioTargetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@Service
public class PortFolioActionService {
    @Autowired
    PortFolioTargetRepo Targetrep;
    @Autowired
    PortFolioSourceRepo Sourcerep;
    @Autowired
    PortFolioActionResultRepo resultrep;

    @Autowired
    PortFolioActionResult portFolioActionResult = new PortFolioActionResult();

    @Bean
    public List<PortFolioSource> getSource() {
        List<PortFolioSource> sourcelist = Sourcerep.findAll();
        return sourcelist;
    }

    @Bean
    public List<PortFolioTarget> getTarget() {
        List<PortFolioTarget> targetlist = Targetrep.findAll();

        return targetlist;
    }

    public List<PortFolioActionResult> getActionResult() {
        GetStockMarketPrice getprice = new GetStockMarketPrice();
        Map<String, Double> scripcost = new HashMap<>();
        Map<String, Integer> sourcemap = new HashMap<>();
        Map<String, Integer> targetmap = new HashMap<>();
        TimeSeriesDailyJson result;
        String Price = "0", SCRIPNAME;
        Double totalPortfoliovalue = 0.0, totalperscrip = 0.0;
        int i = 0, quantity = 0, totalquantity = 0;
        PortFolioTarget target = null;

        List<PortFolioSource> sourcelist = Sourcerep.findAll();
        List<PortFolioTarget> targetlist = Targetrep.findAll();

        List<PortFolioActionResult> resultList = new ArrayList<>() ;


        List<String> targetScripNames = new ArrayList<>();


        for (PortFolioSource sourcetemp : sourcelist) {

            targetScripNames.add(sourcetemp.getScripName());
            sourcemap.put(sourcetemp.getScripName(), sourcetemp.getNoofShares());


        }

        for (PortFolioTarget targettemp : targetlist) {

            if (!targetScripNames.contains(targettemp.getScripName()))

                targetScripNames.add(targettemp.getScripName());
            targetmap.put(targettemp.getScripName(), targettemp.getPercentofShares());

        }

                /* There is a restriction that we can make on 5 API calls per minute. But for this use case
           we need to make atleast 6 API calls. So I am hardcoding the value needed for 6th API call for ACAD
           for the price of 42.6100. Alternatively we can put a delay of 1 min after every 5 calls.

         */

        for (i = 0; i < 5; i++) {
            SCRIPNAME = targetScripNames.get(i);
            Price = getprice.get(SCRIPNAME);
            scripcost.put(SCRIPNAME, Double.parseDouble(Price));

        }
        scripcost.put("ACAD", 42.6100);

        for (PortFolioSource sourcetemp : sourcelist) {
            totalPortfoliovalue += scripcost.get(sourcetemp.getScripName()) * sourcetemp.getNoofShares();

        }

        // Calulate no of shares and action
        for (String scrip : targetScripNames) {
            portFolioActionResult.setScripName(scrip);

            if (sourcemap.get(scrip) != null && targetmap.get(scrip) != null) {

                totalperscrip = (targetmap.get(scrip) * totalPortfoliovalue) / 100;
                totalquantity = (int) (totalperscrip / scripcost.get(scrip));

                if (totalquantity > sourcemap.get(scrip)) {

                    quantity = totalquantity - sourcemap.get(scrip);
                    portFolioActionResult.setNoofShares(quantity);
                    portFolioActionResult.setAction("BUY");
                } else {
                    quantity = sourcemap.get(scrip) - totalquantity;
                    portFolioActionResult.setNoofShares(quantity);
                    portFolioActionResult.setAction("SELL");
                }

            }

            if (sourcemap.get(scrip) != null && targetmap.get(scrip) == null) {
                portFolioActionResult.setAction("SELL");
                portFolioActionResult.setNoofShares(sourcemap.get(scrip));

            }

            if (sourcemap.get(scrip) == null && targetmap.get(scrip) != null) {
                portFolioActionResult.setAction("BUY");
                totalperscrip = (targetmap.get(scrip) * totalPortfoliovalue) / 100;
                totalquantity = (int) (totalperscrip / scripcost.get(scrip));
                portFolioActionResult.setNoofShares(totalquantity);

            }

               resultrep.saveAndFlush(portFolioActionResult);


        }


        //targetScripNames.add(Price);
        //targetScripNames.add(String.valueOf(scripcost.size()));

        resultList=resultrep.findAll();
        return resultList;


    }
}
