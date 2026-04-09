package com.example.samuraitravel.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DailySalesTasklet implements Tasklet {

	private final JdbcTemplate jdbcTemplate;

	public DailySalesTasklet(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		System.out.println("DailySalesTasklet 開始");
		String sql = """
				INSERT INTO daily_sales (
				  target_date,
				  total_sales_amount,
				  total_reservation_count
				)
				SELECT
				  DATE(created_at) AS target_date,
				  COALESCE(SUM(amount), 0) AS total_sales_amount,
				  COUNT(*) AS total_reservation_count
				FROM reservations
				GROUP BY DATE(created_at)
				ON DUPLICATE KEY UPDATE
				  total_sales_amount = VALUES(total_sales_amount),
				  total_reservation_count = VALUES(total_reservation_count)
				""";

		jdbcTemplate.update(sql);
		return RepeatStatus.FINISHED;
	}
}