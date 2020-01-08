package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.UserDTO;
import pl.senderek.gieldal2.tester.model.User;

/**
 * Interfejs pozwalający na mapowanie obiektów typu {@link pl.senderek.gieldal2.tester.model.User} na obieky {@link pl.senderek.gieldal2.tester.dto.UserDTO} oraz na odwrót
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    @Mappings({
            @Mapping(target = "shares", ignore = true),
            @Mapping(target = "sellOffers", ignore = true),
            @Mapping(target = "buyOffers", ignore = true)
    })
    User userDTOToUser(UserDTO user);
}
