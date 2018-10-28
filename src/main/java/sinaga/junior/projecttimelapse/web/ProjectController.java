package sinaga.junior.projecttimelapse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sinaga.junior.projecttimelapse.domain.Project;
import sinaga.junior.projecttimelapse.services.MapValidationErrorService;
import sinaga.junior.projecttimelapse.services.ProjectService;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.security.Principal;

@RestController
@RequestMapping("/api/project")
@CrossOrigin

public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap !=null) return  errorMap;

        Project project1 = projectService.saveOrUpdateProject(project,principal.getName());
        return  new ResponseEntity<Project>(project, HttpStatus.CREATED);

    }

    @GetMapping("/{projectId}")
    public  ResponseEntity getProjectById(@PathVariable String projectId, Principal principal) {
        Project project =  projectService.findProjectByIdentifiier(projectId, principal.getName());
        return  new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal) {
        return  projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{projectId}")
    public  ResponseEntity<?> deleteproject(@PathVariable String projectId , Principal principal) {
        projectService.deleteProjectByIdentifier(projectId,principal.getName());
        return  new ResponseEntity<String>("project with ID '"+projectId+" ' was deleted" , HttpStatus.OK);
    }






}
