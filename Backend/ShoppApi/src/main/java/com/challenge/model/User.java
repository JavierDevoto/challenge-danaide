package com.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    protected String username;
    protected String firstName;
    protected String lastName;
    protected Boolean vip = false;

}
