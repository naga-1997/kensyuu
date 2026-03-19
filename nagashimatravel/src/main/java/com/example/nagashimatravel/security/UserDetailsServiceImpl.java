package com.example.nagashimatravel.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;
	private final MessageSource messageSource;

	public UserDetailsServiceImpl(UserRepository userRepository, MessageSource messageSource) {
		this.userRepository = userRepository;
		this.messageSource = messageSource;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User user = userRepository.findByEmail(email);
			//存在しないメアドが打たれたとしても確実に例外処理
			if (user == null) {
				throw new UsernameNotFoundException(
						messageSource.getMessage("securirty.user.error", null, Locale.getDefault()));
			}
			String userRoleName = user.getRole().getName();
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(userRoleName));
			return new UserDetailsImpl(user, authorities);
		} catch (Exception e) {
			throw new UsernameNotFoundException(
					(messageSource.getMessage("securirty.user.error", null, Locale.getDefault())));
		}
	}
}
