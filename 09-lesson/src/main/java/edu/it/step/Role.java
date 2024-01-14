package edu.it.step;

import lombok.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Role {
    private long id;
    private String name;
}
