package tuyen.novahub.assignment4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	@Query(value = "select a.name_role from role a, \"user\" b where b.email=?1 and a.id_role = b.id_role", nativeQuery = true)
	public List<String> findRoleByEmail(String email);
	
}