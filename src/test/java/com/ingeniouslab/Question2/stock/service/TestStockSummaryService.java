package com.ingeniouslab.Question2.stock.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestStockSummaryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestStockSummaryService.class);

	@Autowired
	StockSummaryService stockSummaryService;
	
	@Test
	@Rollback(value=false)
	public void test_getMarketSummary() {
    	
    	try {
    		stockSummaryService.getMarketSummary();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
    }
}
