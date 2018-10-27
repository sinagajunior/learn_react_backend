package sinaga.junior.projecttimelapse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sinaga.junior.projecttimelapse.domain.User;
import sinaga.junior.projecttimelapse.exception.UsernameAlreadyExistsException;
import sinaga.junior.projecttimelapse.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

      @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return  userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("user name  "+newUser.getUsername()+"  already exists");
        }

    }







}
