package kr.codeback.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PreferenceResponseDTO {

    private UUID entityID;
    private boolean likeSign;


    @Builder
    private PreferenceResponseDTO(UUID entityID, boolean likeSign) {
        this.entityID = entityID;
        this.likeSign = likeSign;
    }
}
