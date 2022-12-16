package com.example.workshopnextleveltechnologiesmvc.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserServiceModel {
    private String username;
    private String password;
    private String email;
}
