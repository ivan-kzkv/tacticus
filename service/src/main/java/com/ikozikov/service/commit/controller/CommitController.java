package com.ikozikov.service.commit.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ikozikov.service.commit.dto.CommitDto;
import com.ikozikov.service.commit.service.CommitService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
public class CommitController {
    
    private final CommitService commitService;


    @GetMapping("/commit")
    public List<CommitDto> fetchCommitsForScenario(@RequestParam Long scenarioId) {
        return commitService.fetchAllCommitsByScenario(scenarioId);
    }

    @GetMapping("/commit/chain/{id}")
    public List<CommitDto> fetchCommitChain(@PathVariable Long id) {
        return commitService.fetchCommitsChane(id);
    }

    @PostMapping("/commit")
    @ResponseStatus(HttpStatus.CREATED)
    public CommitDto saveCommit(@RequestBody CommitDto commitDto) {
        return commitService.saveCommit(commitDto);
    }
    
    @PutMapping("/commit/{id}")
    public CommitDto putMethodName(@PathVariable Long id, @RequestBody CommitDto commitDto) {
        return commitService.updateCommit(id, commitDto);
    }

    @DeleteMapping("/commit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommit(@PathVariable Long id) {
        commitService.deleteCommits(id);
    }
}
