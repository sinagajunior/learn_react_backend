package sinaga.junior.projecttimelapse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sinaga.junior.projecttimelapse.domain.Backlog;
import sinaga.junior.projecttimelapse.domain.ProjectTask;
import sinaga.junior.projecttimelapse.repositories.BacklogRepository;
import sinaga.junior.projecttimelapse.repositories.ProjectRepository;
import sinaga.junior.projecttimelapse.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {


    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;


    public ProjectTask addProjectTask (String projectIdentifier,ProjectTask projectTask, String userName) {
        // PTs to be added to a Specific project , project !=null , BL is exists
        Backlog backlog = projectService.findProjectByIdentifiier(projectIdentifier,userName).getBacklog();
        // set the bl to pt
        System.out.println(backlog);
        projectTask.setBacklog(backlog);





    }




}
