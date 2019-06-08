package com.example.polls.util;

import com.example.polls.model.Poll;
import com.example.polls.model.User;
import com.example.polls.payload.ChoiceResponse;
import com.example.polls.payload.PollResponse;
import com.example.polls.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(
        Poll poll,
        Map<Long, Long> choiceVotesMap,
        User creator,
        Long userVote
    ) {
        PollResponse pollResponse = PollResponse.builder()
            .id(poll.getId())
            .question(poll.getQuestion())
            .creationDateTime(poll.getCreatedAt())
            .expirationDateTime(poll.getExpirationDateTime())
            .isExpired(poll.getExpirationDateTime().isBefore(Instant.now()))
            .build();

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(
            choice -> {
                ChoiceResponse choiceResponse = new ChoiceResponse();
                choiceResponse.setId(choice.getId());
                choiceResponse.setText(choice.getText());

                if (choiceVotesMap.containsKey(choice.getId())) {
                    choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
                } else {
                    choiceResponse.setVoteCount(0);
                }
                return choiceResponse;
            }).collect(Collectors.toList());
        
        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(
            creator.getId(),
            creator.getUsername(),
            creator.getName()
        );
        pollResponse.setCreatedBy(creatorSummary);
        
        if (userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }
        
        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);
        
        return pollResponse;
    }
}
