package com.PortfolioAction.PortfolioAction.repository;
import com.PortfolioAction.PortfolioAction.model.PortFolioTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortFolioTargetRepo extends JpaRepository<PortFolioTarget,String> {


}