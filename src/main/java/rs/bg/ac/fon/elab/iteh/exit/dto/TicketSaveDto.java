package rs.bg.ac.fon.elab.iteh.exit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TicketSaveDto {
    private double price;
    private String description;
    private LocalDate purchaseDate;
    private double discount;
    private long ownerId;
    private long stageId;
}
