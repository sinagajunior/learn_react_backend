package sinaga.junior.projecttimelapse.repositories;

import org.springframework.data.repository.CrudRepository;
import sinaga.junior.projecttimelapse.domain.Backlog;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String Identifier);


}
