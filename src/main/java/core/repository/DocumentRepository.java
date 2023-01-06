package main.java.core.repository;

import main.java.core.domain.Document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	
	List<Document> findByUserId(String user);

}

