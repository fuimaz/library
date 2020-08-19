package com.hk.library.dto.query;

import lombok.Data;
import lombok.NonNull;

@Data
public class VoteQuery {

    @NonNull
    private String questionTid;

    @NonNull
    private String answer;

    @NonNull
    private String userTid;

    private String userName;
}
