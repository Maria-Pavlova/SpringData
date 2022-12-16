package com.example.workshopnextleveltechnologiesmvc.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}
