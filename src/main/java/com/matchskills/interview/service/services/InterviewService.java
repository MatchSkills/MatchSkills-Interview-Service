package com.matchskills.interview.service.services;

import com.matchskills.interview.service.dtos.EditSoftSkillsRequest;
import com.matchskills.interview.service.dtos.ExtractSoftskillsResponse;
import com.matchskills.interview.service.dtos.FinishInterviewRequest;
import com.matchskills.interview.service.dtos.QuestionsResponse;
import com.matchskills.interview.service.entitys.SoftskillEntity;
import com.matchskills.interview.service.exceptions.customs.jobposting.JobPostingNotFoundException;
import com.matchskills.interview.service.jwt.InternalTokenProvider;
import com.matchskills.interview.service.repositorys.JobPostingRepository;
import com.matchskills.interview.service.repositorys.SoftskillRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class InterviewService {

    final private JobPostingRepository jobPostingRepository;
    final private SoftskillRepository softskillRepository;
    final private InternalTokenProvider internalTokenProvider;
    final private RestClient restClient = RestClient.create();
    final private String iaServiceUrl;
    final private String iaJobApplicationUrl;

    public InterviewService(JobPostingRepository jobPostingRepository,
                            SoftskillRepository softskillRepository,
                            InternalTokenProvider internalTokenProvider,
                            @Value("${ia.url}") String iaServiceUrl,
                            @Value("${jobapplication.url}") String jobApplicationServiceUrl) {
        this.jobPostingRepository = jobPostingRepository;
        this.softskillRepository = softskillRepository;
        this.internalTokenProvider = internalTokenProvider;
        this.iaServiceUrl = iaServiceUrl;
        this.iaJobApplicationUrl = jobApplicationServiceUrl;
    }

    public QuestionsResponse getQuestions(Long jobpostingId){

        var jobposting = jobPostingRepository.findById(jobpostingId)
                .orElseThrow(JobPostingNotFoundException::new);

        var targetSoftSkills = jobposting.getTargetSoftskills().keySet();

        var softskills = softskillRepository.findByNameIgnoreCaseIn(targetSoftSkills);

        var targetQuestions = new ArrayList<Map<String, String>>();

        for (SoftskillEntity softskill : softskills) {

            var questions = softskill.getQuestions();

            var randomQuestion = questions.get(ThreadLocalRandom.current().nextInt(questions.size()));

            targetQuestions.add(Map.of("question",randomQuestion));

        }

        return new QuestionsResponse(targetQuestions);

    }

    @Async
    public void finishInterview(FinishInterviewRequest finishInterviewRequest){

        String internalTokenAI = internalTokenProvider.generate("interview-service");

        var resultsAi = restClient.post()
                .uri(iaServiceUrl + "ai/extract-softskills")
                .header("X-Internal-Token", internalTokenAI)
                .body(finishInterviewRequest)
                .retrieve()
                .body(ExtractSoftskillsResponse.class);

        String internalTokenJobApplication = internalTokenProvider.generate("interview-service");

        restClient.put()
                .uri(iaServiceUrl + "job-application/edit-softskills")
                .header("X-Internal-Token", internalTokenJobApplication)
                .body(new EditSoftSkillsRequest(finishInterviewRequest.getJobApplicationId(),resultsAi.getResults()))
                .retrieve();
    }

}
