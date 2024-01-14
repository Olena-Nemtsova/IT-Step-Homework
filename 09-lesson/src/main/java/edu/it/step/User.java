package edu.it.step;

import lombok.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User {
    @AllowPrint(value = false)
    private long id;
    @AllowPrint(value = false)
    private String uuid;
    @AllowPrint
    private String name;
    private String email;
    @AllowPrint(value = false)
    private String password;
    private Role role;
}
