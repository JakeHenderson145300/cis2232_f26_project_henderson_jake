package ca.hccis.pocketLedger.graphql;

import ca.hccis.pocketLedger.jpa.entity.SquashSkills;
import ca.hccis.pocketLedger.repositories.SkillsAssessmentSquashTechnicalRepository;
import ca.hccis.pocketLedger.bo.SkillsAssessmentSquashTechnicalBO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SkillsAssessmentSquashTechnicalGraphQLController {

    private final SkillsAssessmentSquashTechnicalRepository repo;

    public SkillsAssessmentSquashTechnicalGraphQLController(SkillsAssessmentSquashTechnicalRepository repo) {
        this.repo = repo;
    }

    @QueryMapping
    public List<SquashSkills> assessments() {
        List<SquashSkills> all = new ArrayList<>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @QueryMapping
    public SquashSkills assessmentById(@Argument Integer id) {
        Optional<SquashSkills> opt = repo.findById(id);
        return opt.orElse(null);
    }

    @QueryMapping
    public List<SquashSkills> findByAthlete(@Argument String name) {
        return repo.findByAthleteNameContaining(name);
    }

    @QueryMapping
    public List<SquashSkills> findByAssessor(@Argument String name) {
        return repo.findByAssessorNameContaining(name);
    }

    @MutationMapping
    public SquashSkills createAssessment(@Argument SkillsAssessmentSquashTechnicalInput input) {
        SquashSkills entity = input.toEntity();
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(entity);
        return repo.save(entity);
    }

    @MutationMapping
    public SquashSkills updateAssessment(@Argument Integer id, @Argument SkillsAssessmentSquashTechnicalInput input) {
        SquashSkills entity = input.toEntity();
        entity.setId(id);
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(entity);
        return repo.save(entity);
    }

    @MutationMapping
    public Boolean deleteAssessment(@Argument Integer id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}