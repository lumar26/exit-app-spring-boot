package rs.bg.ac.fon.elab.iteh.exit.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEventDto {
    private String name;
    private LocalDate start;
    private String image;
    private Long stageId;
    private Long UserId;
    private Long[] performersIds;
}
