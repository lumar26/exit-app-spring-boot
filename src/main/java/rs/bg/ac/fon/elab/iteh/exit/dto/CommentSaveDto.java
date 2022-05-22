package rs.bg.ac.fon.elab.iteh.exit.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentSaveDto {
    private int rate;
    private String content;
    private Long eventId;
    private Long userId;
}
