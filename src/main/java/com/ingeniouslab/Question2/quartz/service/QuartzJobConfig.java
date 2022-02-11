package com.ingeniouslab.Question2.quartz.service;

import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.ingeniouslab.Question2.stock.service.UpdateStockSummaryQuartzJob;

@Configuration
@EnableAutoConfiguration
public class QuartzJobConfig {
	
	final static Logger LOGGER = LoggerFactory.getLogger(QuartzJobConfig.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private QuartzProperties quartzProperties;
	
    @Bean
    public SchedulerJobFactory springBeanJobFactory() {
    	SchedulerJobFactory jobFactory = new SchedulerJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
    
	@Bean(name="updateStockSummaryJob")
	public JobDetailFactoryBean updateStockSummaryJobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(UpdateStockSummaryQuartzJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}
	
	// Trigger job at 9am everyday
	@Bean(name="cronTriggerUpdateStockSummaryJob")
	public CronTriggerFactoryBean cronTriggerUpdateStockSummaryJob(@Qualifier("updateStockSummaryJob")JobDetail job) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(job);
		trigger.setCronExpression("0 0 9 ? * * *");
		return trigger;
	}
	
	@Bean
	public SchedulerFactoryBean scheduler(@Qualifier("cronTriggerUpdateStockSummaryJob")Trigger updateStockSummaryTrigger, @Qualifier("updateStockSummaryJob")JobDetail updateStockSummaryJob) {
		Trigger[] triggerList = new Trigger[] {updateStockSummaryTrigger};
		JobDetail[] jobDetailList = new JobDetail[] {updateStockSummaryJob};
		
		Properties properties = new Properties();
		properties.putAll(quartzProperties.getProperties());
		
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		
		factory.setAutoStartup(true);
		factory.setTransactionManager(transactionManager);
		factory.setWaitForJobsToCompleteOnShutdown(true);
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		factory.setQuartzProperties(properties);
		
		factory.setJobFactory(springBeanJobFactory());

		factory.setJobDetails(jobDetailList);
		factory.setTriggers(triggerList);
		
		return factory;
	}
}
