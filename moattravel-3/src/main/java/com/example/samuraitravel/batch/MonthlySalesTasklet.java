package com.example.samuraitravel.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MonthlySalesTasklet implements Tasklet {

	private final JdbcTemplate jdbcTemplate;

	public MonthlySalesTasklet(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		System.out.println("MonthlySalesTasklet 開始");
		String sql = """
				INSERT INTO monthly_sales (
				  target_month,
				  total_sales_amount,
				  total_reservation_count
				)
				SELECT
				  DATE_FORMAT(target_date, '%Y-%m') AS target_month,
				  COALESCE(SUM(total_sales_amount), 0) AS total_sales_amount,
				  COALESCE(SUM(total_reservation_count), 0) AS total_reservation_count
				FROM daily_sales
				GROUP BY DATE_FORMAT(target_date, '%Y-%m')
				ON DUPLICATE KEY UPDATE
				  total_sales_amount = VALUES(total_sales_amount),
				  total_reservation_count = VALUES(total_reservation_count)
				""";

		jdbcTemplate.update(sql);
		return RepeatStatus.FINISHED;
	}
}