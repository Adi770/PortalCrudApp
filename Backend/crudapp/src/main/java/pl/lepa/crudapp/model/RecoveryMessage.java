package pl.lepa.crudapp.model;

import lombok.Data;

@Data
public class RecoveryMessage {

    private String title;
    private String baseUrl;
    private String message;
    private String userEmail;
}
