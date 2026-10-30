package ca.hccis.pocketLedger.entity;

import ca.hccis.pocketLedger.jpa.entity.SquashSkills;

import java.util.ArrayList;
import java.util.List;

public class SkillsAssessmentSquashTechnicalDto {
    private List<SquashSkills> assessments;

    // default and parameterized constructor


    public SkillsAssessmentSquashTechnicalDto() {
        assessments = new ArrayList<SquashSkills>();
    }

    public SkillsAssessmentSquashTechnicalDto(List<SquashSkills> assessments) {
        this.assessments = assessments;
    }

    public void addSkillsAssessmentSquashTechnical(SquashSkills skillsAssessmentSquashTechnical) {
        this.assessments.add(skillsAssessmentSquashTechnical);
    }

    public List<SquashSkills> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<SquashSkills> assessments) {
        this.assessments = assessments;
    }
}
