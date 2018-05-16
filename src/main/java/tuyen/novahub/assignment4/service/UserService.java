package tuyen.novahub.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(User newUser) {
		return userRepository.save(newUser);
	}

	public User findByIdUser(int idUser) {
		return userRepository.findByIdUser(idUser);
	}

	public int deleteByIdUser(int idUser) {
return userRepository.deleteByIdUser(idUser);		
	}

	public User findByEmailAndEnabled(String email, int enabled) {
		return userRepository.findByEmailAndEnabled(email, enabled);
	}

}
