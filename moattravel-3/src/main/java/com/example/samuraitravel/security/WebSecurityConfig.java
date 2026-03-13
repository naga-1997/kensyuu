package com.example.samuraitravel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						//全てのユーザーにアクセスを許可するURL
						.requestMatchers("/css/**", "/images/**", "/js/**", "/storage/**", "/", "/login", "/signup/**", "/houses")
						.permitAll()
						//管理者にのみアクセスを許可するUL
						.requestMatchers("/admin/**").hasRole("ADMIN")
						//上記以外のURLはログインが必要
						.anyRequest().authenticated())
				.formLogin((form) -> form
						//ログインページのURL
						.loginPage("/login")
						//ログインフォームの送信先URL
						.loginProcessingUrl("/login")
						//ログイン成功時のリダイレクト先URL
						.defaultSuccessUrl("/?loggedIn")
						//ログイン失敗時のリダイレクト先URL
						.failureUrl("/login?error")
						.permitAll())
				.logout((logout) -> logout
						//ログアウト時のリダイレクト先URL
						.logoutSuccessUrl("/?loggedOut")
						.permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
