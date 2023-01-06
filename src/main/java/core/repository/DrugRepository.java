package main.java.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import main.java.core.domain.Drugs;

public interface DrugRepository extends JpaRepository<Drugs, Integer>{
	
	List<Drugs> findByDrugNameLike(String name);

}
