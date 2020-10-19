package com.PortfolioAction.PortfolioAction.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PortFolioTarget")
@Component
public class PortFolioTarget {

    public String getScripName() {
        return ScripName;
    }

    public void setScripName(String scripName) {
        ScripName = scripName;
    }

    public Integer getPercentofShares() {
        return PercentofShares;
    }

    public void setPercentofShares(Integer percentofShares) {
        PercentofShares = percentofShares;
    }

    @Override
    public String toString() {
        return "PortFolioTarget{" +
                "ScripName='" + ScripName + '\'' +
                ", PercentofShares=" + PercentofShares +
                '}';
    }

    @Id
    private String ScripName;
    private Integer PercentofShares;
}
