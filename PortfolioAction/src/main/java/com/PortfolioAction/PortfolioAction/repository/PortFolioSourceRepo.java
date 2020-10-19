package com.PortfolioAction.PortfolioAction.repository;

import com.PortfolioAction.PortfolioAction.model.PortFolioSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortFolioSourceRepo extends JpaRepository<PortFolioSource,String> {


}
