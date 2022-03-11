package edu.ntnu.oflarsen.backendcrud.repository;

import java.util.List;
import edu.ntnu.oflarsen.backendcrud.model.Tutorial;
public interface TutorialRepository {
    int save(Tutorial book);
    int update(Tutorial book);
    Tutorial findById(Long id);
    int deleteById(Long id);
    List<Tutorial> findAll();
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
    int deleteAll();
}