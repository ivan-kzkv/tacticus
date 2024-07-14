package com.ikozikov.service.commit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ikozikov.service.commit.dto.CommitDto;
import com.ikozikov.service.commit.repository.CommitRepository;
import com.ikozikov.service.commit.utils.CommitMaper;
import com.ikozikov.service.scenario.repository.ScenarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommitService {
    private final CommitRepository commitRepository;
    private final ScenarioRepository scenarioRepository;

    public List<CommitDto> fetchAllCommitsByScenario(Long scenarioId) {
        return commitRepository.findByScenarioIdOrderByStageAsc(scenarioId).stream().map(CommitMaper::toDto).toList();
    }

    public List<CommitDto> fetchCommitsChane(Long commitId) {
        return commitRepository.findCommitChain(commitId).stream().map(CommitMaper::toDto).toList();
    }

    public CommitDto saveCommit(CommitDto commitDto) {
        return Optional.of(commitDto)
        .flatMap(cDto -> scenarioRepository.findById(cDto.getId()))
        .map(scenario -> CommitMaper.toModel(commitDto, commitRepository, scenario))
        .map(commitRepository::save)
        .map(savedCommit -> {
            Optional.ofNullable(commitDto.getPreviousCommit())
            .flatMap(commitRepository::findById)
            .ifPresent(previousCommit -> {
                previousCommit.setNextCommit(savedCommit);
            });
            return savedCommit;
        })
        .map(CommitMaper::toDto).get();
    }

    public CommitDto updateCommit(Long id, CommitDto commitDto) {
        return Optional.of(id)
        .flatMap(commitRepository::findById)
        .map(commitModel -> {
            commitModel.setCommitName(commitDto.getCommitName());
            commitModel.setDescription(commitDto.getDescription());
            return commitRepository.save(commitModel);
        })
        .map(CommitMaper::toDto)
        .get();  
    }

    public void deleteCommits(Long commitId) {
        commitRepository.deleteById(commitId);
    }
}
