package com.PortfolioAction.PortfolioAction.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "PortFolioSource")
@Table
@Component
public class PortFolioSource {
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

    @Override
    public String toString() {
        return "PortFolioSource{" +
                "ScripName='" + ScripName + '\'' +
                ", NoofShares=" + NoofShares +
                '}';
    }

    @Id
    private String ScripName;
    private Integer NoofShares;
}
