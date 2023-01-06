package main.java.core.service.drug;

import java.util.List;

import main.java.core.domain.Drugs;

public interface DrugService {
	
	public Drugs getAllDrugs();
	public List<Drugs> getSearchedDrugs(String name);

}
