package tuyen.novahub.assignment4.common;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import tuyen.novahub.assignment4.model.User;

public class MyUserDetails extends User implements UserDetails {
	
	private static final long	serialVersionUID	= 1L;
	
	private List<String>			userRoles;
	
	public MyUserDetails(User user, List<String> userRoles) {
		super(user);
		this.userRoles = userRoles;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		
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
	
	@Override
	public String getUsername() {
		return super.getEmail();
	}
	
}
