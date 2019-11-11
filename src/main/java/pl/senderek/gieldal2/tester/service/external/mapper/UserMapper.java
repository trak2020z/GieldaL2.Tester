package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import pl.senderek.gieldal2.tester.dto.UserDTO;
import pl.senderek.gieldal2.tester.model.User;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO user);
}
