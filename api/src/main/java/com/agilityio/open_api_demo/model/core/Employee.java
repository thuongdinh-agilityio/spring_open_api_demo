package com.agilityio.open_api_demo.model.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Department department;

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }
}
