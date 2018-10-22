package sinaga.junior.projecttimelapse.exception;

public class ProjectNotFoundExceptionResponse {
    private String ProjectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return ProjectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        ProjectNotFound = projectNotFound;
    }
}
