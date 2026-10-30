package ca.hccis.pocketLedger.repositories;

import ca.hccis.pocketLedger.jpa.entity.SquashSkills;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsAssessmentSquashTechnicalRepository extends CrudRepository<SquashSkills, Integer> {
    /**
     * Use Spring Data JPA functionality to find a list  containing the
     * string passed in as a paramter.
     *
     * @param name The name to find
     * @return The list of items
     * @since 20251030
     * @author BJM
     */
    //https://www.baeldung.com/spring-jpa-like-queries
    List<SquashSkills> findByAthleteNameContaining(String name);
    List<SquashSkills> findByAssessorNameContaining(String name);

}