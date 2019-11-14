package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.dto.UserDTO;
import pl.senderek.gieldal2.tester.service.external.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends StockApi implements UserService {

    private static String USER_API = BASE_STOCK_API + "/api/Users";

    @Override
    public List<UserDTO> getAllUsers() {
        String url = USER_API;
        return getForEntityList(url, UserDTO.class).getBody();
    }

    @Override
    public Optional<UserDTO> getUser(Long userId) {
        String url = USER_API + "/" + userId;
        ResponseEntity<UserDTO> response = getForEntity(url, UserDTO.class);
        return Optional.ofNullable(response.getBody());
    }

    @Override
    public void createUser(UserDTO user) {
        String url = USER_API;
        post(url, user);
    }

    @Override
    public void modifyUser(UserDTO user) {
        String url = USER_API + "/" + user.getId();
        put(url, user);
    }

    @Override
    public void deleteUser(Long userId) {
        String url = USER_API + "/" + userId;
        delete(url);
    }
}
