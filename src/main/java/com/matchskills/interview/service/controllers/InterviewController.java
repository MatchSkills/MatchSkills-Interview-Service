package com.matchskills.interview.service.controllers;

import com.matchskills.interview.service.dtos.FinishInterviewRequest;
import com.matchskills.interview.service.dtos.QuestionsResponse;
import com.matchskills.interview.service.services.InterviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    final private InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping("/jobposting/{jobpostingId}/questions")
    public ResponseEntity<QuestionsResponse> getQuestionsByJobPostingId(@PathVariable Long jobpostingId) {
        return ResponseEntity.status(HttpStatus.OK).body(interviewService.getQuestions(jobpostingId));
    }

    @PostMapping("/finish")
    public ResponseEntity<Void> finishInterview(@RequestBody FinishInterviewRequest finishInterviewRequest) {
        interviewService.finishInterview(finishInterviewRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
