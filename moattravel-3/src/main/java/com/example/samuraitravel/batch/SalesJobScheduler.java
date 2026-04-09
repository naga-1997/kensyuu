package com.example.samuraitravel.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SalesJobScheduler {

	private final JobLauncher jobLauncher;
	private final Job dailySalesJob;
	private final Job monthlySalesJob;

	public SalesJobScheduler(JobLauncher jobLauncher, Job dailySalesJob, Job monthlySalesJob) {
		this.jobLauncher = jobLauncher;
		this.dailySalesJob = dailySalesJob;
		this.monthlySalesJob = monthlySalesJob;
	}

	@Scheduled(cron = "* * 11 * * ?")
	public void runDailySalesJob() throws Exception {
		 System.out.println("日次ジョブ起動");
		JobParameters params = new JobParametersBuilder()
				.addLong("timestamp", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(dailySalesJob, params);
	}

	@Scheduled(cron = "* * 11 * * ?")
	public void runMonthlySalesJob() throws Exception {
		 System.out.println("月次ジョブ起動");
		JobParameters params = new JobParametersBuilder()
				.addLong("timestamp", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(monthlySalesJob, params);
	}
}