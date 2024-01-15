package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final   UserRepository userRepository;
    private final   SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserService(UserRepository userRepository,SequenceGeneratorService sequenceGeneratorService ){
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User createUser(User user) {
        long _id = sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
        user.setId(_id);
        System.out.println("hahaha:"+ user.toString());
        User userResult = userRepository.insert(user);
        System.out.println("userResult:"+userResult.toString());

        return userResult;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        System.out.println("OKOK");
        return optionalUser.orElse(null);
        //  return userRepository.findByUsername(username).get();
    }
    // Add more methods as per your requirements
}