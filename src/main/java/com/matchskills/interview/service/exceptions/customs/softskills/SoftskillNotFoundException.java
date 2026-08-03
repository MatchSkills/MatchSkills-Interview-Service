package com.matchskills.interview.service.exceptions.customs.softskills;

public class SoftskillNotFoundException extends RuntimeException {
    public SoftskillNotFoundException() {
        super("Softskill not found");
    }
}
