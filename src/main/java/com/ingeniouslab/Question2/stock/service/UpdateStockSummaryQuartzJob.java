package com.ingeniouslab.Question2.stock.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateStockSummaryQuartzJob implements Job {

	static final Logger LOGGER = LoggerFactory.getLogger(UpdateStockSummaryQuartzJob.class);
	
	@Autowired
	private StockSummaryService stockSummaryService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.debug("UpdateStockSummaryQuartzJob|Start");
		stockSummaryService.getMarketSummary();
		LOGGER.debug("UpdateStockSummaryQuartzJob|End");
	}
	
}

