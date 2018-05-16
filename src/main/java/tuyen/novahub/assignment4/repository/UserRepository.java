package tuyen.novahub.assignment4.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findAll();
	
	public User findByEmail(String email);
	
	@SuppressWarnings("unchecked")
	public User save(User newUser);
	
	public User findByIdUser(int idUser);
	
	public int deleteByIdUser(int idUser);
	
	public User findByEmailAndEnabled(String email, int enabled);
	
}
