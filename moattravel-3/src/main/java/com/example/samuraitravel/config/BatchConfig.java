package com.example.samuraitravel.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.samuraitravel.batch.DailySalesTasklet;
import com.example.samuraitravel.batch.MonthlySalesTasklet;

@Configuration
public class BatchConfig {

	@Bean
	public Step dailySalesStep(
			JobRepository jobRepository,
			PlatformTransactionManager transactionManager,
			DailySalesTasklet dailySalesTasklet) {
		return new StepBuilder("dailySalesStep", jobRepository)
				.tasklet(dailySalesTasklet, transactionManager)
				.build();
	}

	@Bean
	public Step monthlySalesStep(
			JobRepository jobRepository,
			PlatformTransactionManager transactionManager,
			MonthlySalesTasklet monthlySalesTasklet) {
		return new StepBuilder("monthlySalesStep", jobRepository)
				.tasklet(monthlySalesTasklet, transactionManager)
				.build();
	}

	@Bean
	public Job dailySalesJob(JobRepository jobRepository, Step dailySalesStep) {
		return new JobBuilder("dailySalesJob", jobRepository)
				.start(dailySalesStep)
				.build();
	}

	@Bean
	public Job monthlySalesJob(JobRepository jobRepository, Step monthlySalesStep) {
		return new JobBuilder("monthlySalesJob", jobRepository)
				.start(monthlySalesStep)
				.build();
	}
}