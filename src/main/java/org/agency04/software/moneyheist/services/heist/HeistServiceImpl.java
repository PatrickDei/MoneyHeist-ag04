package org.agency04.software.moneyheist.services.heist;

import org.agency04.software.moneyheist.dto.heist.HeistDTO;
import org.agency04.software.moneyheist.repositories.heist.HeistRepository;
import org.agency04.software.moneyheist.transformation.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeistServiceImpl implements HeistService {

    @Autowired
    private Transformation transformation;
    private final HeistRepository heistRepository;

    public HeistServiceImpl(HeistRepository heistRepository) {
        this.heistRepository = heistRepository;
    }

    @Override
    public List<HeistDTO> findAll() {
        return heistRepository.findAll().stream().map(Transformation::HeistToDTO).collect(Collectors.toList());
    }
}
