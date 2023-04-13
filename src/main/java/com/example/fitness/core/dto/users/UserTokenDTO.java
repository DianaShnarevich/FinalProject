package com.example.fitness.core.dto.users;

import com.example.fitness.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Getter
@Setter
public class UserTokenDTO implements UserDetails {
	private String mail;
	private String role;
	private String fio;

	public UserTokenDTO(String mail, String role, String fio) {
		this.mail = mail;
		this.role = role;
		this.fio = fio;
	}

	public UserTokenDTO(UserEntity entity){
		this.mail = entity.getMail();
		this.role = entity.getRole().name();
		this.fio = entity.getFio();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authority;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return fio;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
