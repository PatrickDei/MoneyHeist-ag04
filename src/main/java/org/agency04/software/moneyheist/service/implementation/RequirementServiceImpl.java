package org.agency04.software.moneyheist.service.implementation;

import org.agency04.software.moneyheist.entity.requirement.HeistRequirement;
import org.agency04.software.moneyheist.repository.RequirementRepository;
import org.agency04.software.moneyheist.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Override
    public List<HeistRequirement> findAll(){
        return requirementRepository.findAll();
    }
}
