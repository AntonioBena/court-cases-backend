package com.interview.court.cases;

import com.interview.court.cases.model.court.Court;
import com.interview.court.cases.model.court_case.Case;
import com.interview.court.cases.model.court_case.CaseStatus;
import com.interview.court.cases.model.decision.Decision;
import com.interview.court.cases.model.decision.DecisionType;
import com.interview.court.cases.model.dto.requests.RegistrationRequest;

import java.time.LocalDateTime;
import java.util.Set;

public class TestUtils {

    public static RegistrationRequest generateRegistrationRequest(String email, String firstName, String lastName, String password) {
        return RegistrationRequest.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .build();
    }

    public static Decision generateNewDecision(String desc, String label, DecisionType decisionType) {
        return  Decision.builder()
                .decisionDate(LocalDateTime.now())
                .decisionDescription(desc)
                .decisionLabel(label)
                .decisionType(decisionType)
                .build();
    }


    public static Case generateNewCase(String caseLabel, CaseStatus status, String desc, Set<Decision> decisionSet, Court court) {
        return Case.builder()
                .caseLabel(caseLabel)
                .caseStatus(status)
                .decisions(decisionSet)
                .description(desc)
                .court(court)
                .build();
    }

    public static Court generateNewCourt(String name, String address) {
        return  Court.builder()
                .courtAddress(address)
                .courtName(name)
                .build();
    }
}
