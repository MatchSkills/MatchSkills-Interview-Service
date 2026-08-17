package com.matchskills.interview.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ExtractSoftskillsResponse {

    private Map<String, Integer> results;

}

