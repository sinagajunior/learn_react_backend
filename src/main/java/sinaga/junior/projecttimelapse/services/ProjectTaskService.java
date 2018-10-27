package sinaga.junior.projecttimelapse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sinaga.junior.projecttimelapse.domain.Backlog;
import sinaga.junior.projecttimelapse.domain.ProjectTask;
import sinaga.junior.projecttimelapse.exception.ProjectNotFoundException;
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


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String userName) {
        // PTs to be added to a Specific project , project !=null , BL is exists
        Backlog backlog = projectService.findProjectByIdentifiier(projectIdentifier, userName).getBacklog();
        // set the bl to pt
        System.out.println(backlog);
        projectTask.setBacklog(backlog);
        // we want our project like this IDPRO-1 ,IDPRO-2 .. 100,101
        Integer BacklogSequence = backlog.getPTSequence();
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);

        // add squence to project task
        projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        //initial priority when priority null
        // initial status when status null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");

        }
        //Fix bugs with pripority in Springboot server, need to check null first
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String id, String username) {

        projectService.findProjectByIdentifiier(id, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);

    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username) {

        // make sure we are searching on an existing backlog
        projectService.findProjectByIdentifiier(backlog_id, username);

        //make sure is our task is exist
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if (projectTask == null) {
            throw new ProjectNotFoundException("Project task  '" + pt_id + "' not found");

        }

        // make sure that backlog/project id in the path corresponds to right project
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException(" project Task '" + pt_id + "'  does not exist in " + backlog_id);

        }

        return projectTask;

    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask,String backlog_id,String pt_id,String username) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);
        projectTask = updatedTask;
        return  projectTaskRepository.save(projectTask);
    }


}
