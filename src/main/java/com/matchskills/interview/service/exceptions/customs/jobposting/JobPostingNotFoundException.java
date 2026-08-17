package com.matchskills.interview.service.exceptions.customs.jobposting;

public class JobPostingNotFoundException extends RuntimeException {
    public JobPostingNotFoundException() {
        super("JobPosting not found");
    }
}
