package sinaga.junior.projecttimelapse.repositories;

import org.springframework.data.repository.CrudRepository;
import sinaga.junior.projecttimelapse.domain.User;

public interface UserRepository extends CrudRepository<User,Long>  {
    User findByUsername(String username);
    User getById(Long id);
}
