package pl.senderek.gieldal2.tester.service.external.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.senderek.gieldal2.tester.dto.ShareDTO;
import pl.senderek.gieldal2.tester.model.Share;

import java.util.List;

/**
 * Interfejs pozwalający na mapowanie obiektów typu {@link pl.senderek.gieldal2.tester.model.Share} na obieky {@link pl.senderek.gieldal2.tester.dto.ShareDTO} oraz na odwrót
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, StockMapper.class})
public interface ShareMapper {

    @Mappings({
            @Mapping(source = "owner.id", target = "ownerId"),
            @Mapping(source = "stock.id", target = "stockId")
    })
    ShareDTO shareToShareDTO(Share share);

    @Mappings({
            @Mapping(source = "ownerId", target = "owner.id"),
            @Mapping(source = "stockId", target = "stock.id")
    })
    Share shareDTOToShare(ShareDTO share);

    List<Share> shareDTOToShare(List<ShareDTO> shares);

    List<ShareDTO> shareToShareDTO(List<Share> shares);
}
