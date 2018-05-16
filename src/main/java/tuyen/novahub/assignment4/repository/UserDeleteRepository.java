package tuyen.novahub.assignment4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.model.UserDelete;

@Repository
public interface UserDeleteRepository extends CrudRepository<User, Integer> {
	
	public UserDelete save(UserDelete newUser);
}
