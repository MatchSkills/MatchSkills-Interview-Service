package com.matchskills.interview.service.exceptions.customs.jobposting;

public class NotJobPostingOwnerException extends RuntimeException {
    public NotJobPostingOwnerException() {
        super("This job posting does not belong to this company.");
    }
}
