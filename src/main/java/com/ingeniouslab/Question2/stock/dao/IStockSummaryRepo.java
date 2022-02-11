package com.ingeniouslab.Question2.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ingeniouslab.Question2.stock.entity.MarketSummary;

public interface IStockSummaryRepo extends JpaRepository<MarketSummary, String>, JpaSpecificationExecutor<MarketSummary>{

}
