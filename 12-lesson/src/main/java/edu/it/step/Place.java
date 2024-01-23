package edu.it.step;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Place implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int number;
    private SeatStatus status;
    private String reservedBy;
    private String phoneNumber;
    private UUID clientId;

    public Place(int number, SeatStatus status) {
        this.number = number;
        this.status = status;
    }

    public enum SeatStatus {
        FREE,
        RESERVED,
        BOOKED,
        BOOKED_CONFIRMED
    }
}
