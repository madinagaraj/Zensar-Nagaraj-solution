package com.PortfolioAction.PortfolioAction.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PortFolioActionResult")
@Component
public class PortFolioActionResult {



    public String getScripName() {
        return ScripName;
    }

    public void setScripName(String scripName) {
        ScripName = scripName;
    }

    public Integer getNoofShares() {
        return NoofShares;
    }

    public void setNoofShares(Integer noofShares) {
        NoofShares = noofShares;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }


    @Id
    public String ScripName;
    public Integer NoofShares;
    public String Action;

    @Override
    public String toString() {
        return "PortFolioActionResult{" +
                "ScripName='" + ScripName + '\'' +
                ", NoofShares=" + NoofShares +
                ", Action='" + Action + '\'' +
                '}';
    }



}
