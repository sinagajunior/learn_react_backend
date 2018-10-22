package sinaga.junior.projecttimelapse.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Email(message = "username needs to be an email")
   @NotBlank(message = "username is required")
   @Column(unique = true)
   private String username;
   @NotBlank(message = "please enter your full name")
   private  String fullname;
   @NotBlank(message = "password field is required")
   private String password;
   @Transient
   private  String confirmPassword;
   private Date create_At;
   private Date update_At;

   // One to many with project
    @OneToMany(cascade = CascadeType.REFRESH ,  fetch = FetchType.EAGER, mappedBy = "user" , orphanRemoval = true)
     private List<Project> projects = new ArrayList<>();

    public User() {

    }

    @PrePersist
    protected  void onCreate () { this.create_At = new Date();}

    @PreUpdate
    protected  void onUpdate () { this.update_At = new Date(); }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }
}
