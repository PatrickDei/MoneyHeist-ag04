package org.agency04.software.moneyheist.heists;

import org.agency04.software.moneyheist.heists.repository.HeistRepository;
import org.agency04.software.moneyheist.interfaces.ITransform;

import java.util.List;
import java.util.stream.Collectors;

public class HeistServiceImpl implements HeistService, ITransform {

    private final HeistRepository heistRepository;

    public HeistServiceImpl(HeistRepository heistRepository) {
        this.heistRepository = heistRepository;
    }

    @Override
    public List<HeistDTO> findAll() {
        return heistRepository.findAll().stream().map(this::HeistToDTO).collect(Collectors.toList());
    }
}
