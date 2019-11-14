package pl.senderek.gieldal2.tester.service.external;

import pl.senderek.gieldal2.tester.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUser(Long userId);
    void createUser(UserDTO user);
    void modifyUser(UserDTO user);
    void deleteUser(Long userId);
}
