package com.ingeniouslab.Question2.stock.service;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingeniouslab.Question2.stock.dao.IStockSummaryRepo;
import com.ingeniouslab.Question2.stock.entity.MarketSummary;

@Service
public class StockSummaryService {

	static final Logger LOGGER = LoggerFactory.getLogger(StockSummaryService.class);
	
	@Autowired
	private IStockSummaryRepo stockSummaryRepo;
	
	// Retrieve market summary data from URL
	public void getMarketSummary() {
		
		MarketSummary result = new MarketSummary();
		
		try {
			Document document = Jsoup.connect("https://klse.i3investor.com/index.jsp")
									.timeout(60000)
									.get();

			String totalGainer = document.select(".gainer").first().text();
			result.setNumGainers(totalGainer);
			String totalLoser = document.select(".loser").first().text();
			result.setNumLosers(totalLoser);
			
			LOGGER.debug("totalGainer: {}", result.getNumGainers());
			LOGGER.debug("totalLoser: {}", result.getNumLosers());
			
			saveMarketSummary(result);

		} catch (Exception e) {
			LOGGER.error("Exception: ", e);
		}
				
	}
	
	// Save market summary data into database
	public void saveMarketSummary(MarketSummary result) {
		result.setRecordDatetime(new Date());
		stockSummaryRepo.save(result);
		LOGGER.debug("Data saved");
	}
	
}

