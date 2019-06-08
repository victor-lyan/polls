package com.example.polls.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ChoiceResponse {
    
    private Long id;
    private String text;
    private long voteCount;
}
