package tuyen.novahub.assignment4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.repository.RoleRepository;

@Service
@Transactional
public class RoleDeleteService {
	
	@Autowired
	RoleRepository roleRepository;
}
