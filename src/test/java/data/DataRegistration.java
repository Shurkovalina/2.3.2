package data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataRegistration {
    private final String login;
    private final String password;
    private final String status;

}



