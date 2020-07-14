package net.sabercrafts.coursemgmt.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import net.sabercrafts.coursemgmt.entity.Role;
import net.sabercrafts.coursemgmt.entity.User;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = -4213512976367360981L;

	private User user;
	private Long id;

	public UserPrincipal() {
	}

	public UserPrincipal(User user) {
		this.user = user;
		this.id = user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		Set<Role> roles = user.getRoles();

		if (roles == null)
			return new ArrayList<>();

		roles.forEach((role) -> {

			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

			role.getAuthorities()
					.forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName())));

		});

		return grantedAuthorities;
	}

	@Override
	public String getPassword() {

		return user.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		// we're using email to authenticate.
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
