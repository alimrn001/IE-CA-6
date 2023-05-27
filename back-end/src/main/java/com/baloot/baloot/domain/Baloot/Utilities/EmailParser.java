package com.baloot.baloot.domain.Baloot.Utilities;

public class EmailParser {
    public String getEmailUsername(String email) {
        return email.split("@")[0];
    }
}
