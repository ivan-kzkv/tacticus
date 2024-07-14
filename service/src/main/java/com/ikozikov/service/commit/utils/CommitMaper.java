package com.ikozikov.service.commit.utils;

import java.util.Optional;

import com.ikozikov.service.commit.dto.CommitDto;
import com.ikozikov.service.commit.model.CommitModel;
import com.ikozikov.service.commit.repository.CommitRepository;
import com.ikozikov.service.scenario.model.Scenario;

public class CommitMaper {
    public static CommitModel toModel(CommitDto commitDto, CommitRepository repository, Scenario scenario) {
        var commitModel = CommitModel.builder()
        .id(commitDto.getId())
        .commitName(commitDto.getCommitName())
        .description(commitDto.getDescription())
        .stage(commitDto.getStage())
        .build();

        Optional.ofNullable(commitDto.getPreviousCommit()).flatMap(repository::findById)
        .ifPresent(commitModel::setPreviousCommit); 
        Optional.ofNullable(commitDto.getNextCommit()).flatMap(repository::findById)
        .ifPresent(commitModel::setNextCommit); 
        Optional.ofNullable(scenario).ifPresent(commitModel::setScenario); 
        
        return commitModel;
    }

    public static CommitDto toDto(CommitModel commitModel) {
        var commitDto = CommitDto.builder()
        .id(commitModel.getId())
        .commitName(commitModel.getCommitName())
        .description(commitModel.getDescription())
        .stage(commitModel.getStage())
        .scenario(commitModel.getScenario().getId())
        .build();

        Optional.ofNullable(commitModel.getPreviousCommit())
        .map(CommitModel::getId)
        .ifPresent(id -> commitDto.setPreviousCommit(id));

        Optional.ofNullable(commitModel.getNextCommit())
        .map(CommitModel::getId)
        .ifPresent(id -> commitDto.setNextCommit(id));

        return commitDto;
    }
}
