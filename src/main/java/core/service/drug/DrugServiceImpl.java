package main.java.core.service.drug;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.core.domain.Drugs;
import main.java.core.repository.DrugRepository;

@Service
public class DrugServiceImpl implements DrugService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DrugServiceImpl.class);
	private final DrugRepository drugRepository;

	
	@Autowired
	public DrugServiceImpl(DrugRepository drugRepository) {
	    this.drugRepository = drugRepository;
	}
	public Drugs getAllDrugs() {
		return drugRepository.findOne(2628);
	}
	public List<Drugs> getSearchedDrugs(String name) {
		return drugRepository.findByDrugNameLike(name);
	}


}
