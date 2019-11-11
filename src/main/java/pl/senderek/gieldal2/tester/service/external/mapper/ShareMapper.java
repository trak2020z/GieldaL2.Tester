package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.senderek.gieldal2.tester.dto.ShareDTO;
import pl.senderek.gieldal2.tester.model.Share;

@Mapper(uses={UserMapper.class})
public interface ShareMapper {
    @Mapping(source="owner.id", target="ownerId")
    ShareDTO shareToShareDTO(Share share);
    Share shareDTOToShare(ShareDTO share);
}
