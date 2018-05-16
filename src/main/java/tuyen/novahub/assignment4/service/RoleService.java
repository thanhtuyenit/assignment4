package tuyen.novahub.assignment4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
//	public List<Role> findAll() {
//    return roleRepository.findAll();
//  }

//	public List<String> findRoleByEmail(String email) {
//		return roleRepository.findRoleByEmail(email);
//	}

}
