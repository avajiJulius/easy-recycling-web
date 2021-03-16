package com.easyrecycling.web.entity.model;

import com.easyrecycling.web.entity.enums.Role;
import com.easyrecycling.web.exception.RegistrationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Version
    @Column(name = "version")
    private int version;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private boolean enable;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public Builder withEnabled(boolean isEnabled) {
            newUser.enable = isEnabled;
            return this;
        }

        public Builder withRole(Role role) {
            newUser.role = role;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
