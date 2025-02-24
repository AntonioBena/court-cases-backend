package com.interview.court.cases.service.impl;

import com.interview.court.cases.exception.domain.CourtException;
import com.interview.court.cases.model.court.Court;
import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CourtRequest;
import com.interview.court.cases.repository.CourtRepository;
import com.interview.court.cases.service.CourtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if (courtRepository.existsByCourtName(court.getCourtName())) {
            log.error("Court already exists: {}", court.getCourtName());
            throw new CourtException("You can not create court with name that is already in use");
        }
        return ResponseEntity.ok(
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
                .orElseThrow(() -> {
                    log.error("Court with name {} not found", courtDto.getCourtName());
                    return new CourtException("Court not found!");
                });
    }

    private Court updateCourtWithNewData(CourtDto courtDto, Court court) {
        court.setCourtName(courtDto.getCourtName());
        court.setCourtAddress(courtDto.getCourtAddress());
        return courtRepository.save(court);
    }
}