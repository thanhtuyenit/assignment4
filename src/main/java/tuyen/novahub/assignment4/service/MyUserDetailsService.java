package tuyen.novahub.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

import tuyen.novahub.assignment4.common.MyUserDetails;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.repository.RoleRepository;
import tuyen.novahub.assignment4.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private final UserRepository	userRepository;
	
	private final RoleRepository	roleRepository;
	
	@Autowired
	
	public MyUserDetailsService(UserRepository userRepository, RoleRepository userRolesRepository) {
		
		this.userRepository = userRepository;
		
		this.roleRepository = userRolesRepository;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username){
		User user = userRepository.findByEmail(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with email: " + username);
		} else {
			List<String> userRoles = roleRepository.findRoleByEmail(username);
			return new MyUserDetails(user, userRoles);
			
		}
		
	}
	
}
