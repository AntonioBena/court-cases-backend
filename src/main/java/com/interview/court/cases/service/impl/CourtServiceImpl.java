package com.interview.court.cases.service.impl;

import com.interview.court.cases.model.court.Court;
import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CourtRequest;
import com.interview.court.cases.repository.CourtRepository;
import com.interview.court.cases.service.CourtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final ModelMapper mapper;


    @Override
    public ResponseEntity<?> createNewCourt(CourtRequest courtRequest) {
        var court = courtRequest.getCourtDto();
        log.info("Creating new court: {}", court);
        return courtRepository.existsByCourtName(court.getCourtName()) ?
                returnErrorAndLog(court)
                : ResponseEntity.ok(
                        mapper.map(
                                courtRepository.save(
                                        mapper.map(court, Court.class)
                                ), CourtDto.class)
        );
    }

    @Override
    public CourtDto updateCourt(CourtRequest courtRequest) {
        var courtDto = courtRequest.getCourtDto();

        return courtRepository.findCourtByCourtName(courtDto.getCourtName())
                .map(c -> updateCourtWithNewData(courtDto, c))
                .map(c -> mapper.map(c, CourtDto.class))
                .orElseThrow(() -> new IllegalStateException("Court not found!")); //TODO custom exception
    }

    private ResponseEntity<?>returnErrorAndLog(CourtDto court){
        log.error("Error while creating court: {}", court);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of("error",
                                "Court with name: "+ court.getCourtName() +
                                        " Already exist!."));
    }

    private Court updateCourtWithNewData(CourtDto courtDto, Court court) {
        court.setCourtName(courtDto.getCourtName());
        court.setCourtAddress(courtDto.getCourtAddress());
        return courtRepository.save(court);
    }
}