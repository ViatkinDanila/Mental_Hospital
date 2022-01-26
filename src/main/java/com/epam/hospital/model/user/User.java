package com.epam.hospital.model.user;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Entity {
    private int userId;
    private int userRoleId;
    private String email;
    private String firstname;
    private String lastname;
    private String number;
    private String hashedPassword;
    private boolean isBanned;
}
