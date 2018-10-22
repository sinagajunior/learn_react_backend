package sinaga.junior.projecttimelapse.repositories;

import org.springframework.data.repository.CrudRepository;
import sinaga.junior.projecttimelapse.domain.Project;
import sinaga.junior.projecttimelapse.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
    ProjectTask findByProjectSequence(String sequence);

}
