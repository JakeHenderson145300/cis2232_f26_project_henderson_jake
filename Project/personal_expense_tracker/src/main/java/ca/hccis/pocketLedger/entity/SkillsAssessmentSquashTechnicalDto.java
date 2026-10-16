package ca.hccis.pocketLedger.entity;

import ca.hccis.pocketLedger.jpa.entity.PersonalExpenseTracker;

import java.util.ArrayList;
import java.util.List;

public class SkillsAssessmentSquashTechnicalDto {
    private List<PersonalExpenseTracker> assessments;

    // default and parameterized constructor


    public SkillsAssessmentSquashTechnicalDto() {
        assessments = new ArrayList<PersonalExpenseTracker>();
    }

    public SkillsAssessmentSquashTechnicalDto(List<PersonalExpenseTracker> assessments) {
        this.assessments = assessments;
    }

    public void addSkillsAssessmentSquashTechnical(PersonalExpenseTracker skillsAssessmentSquashTechnical) {
        this.assessments.add(skillsAssessmentSquashTechnical);
    }

    public List<PersonalExpenseTracker> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<PersonalExpenseTracker> assessments) {
        this.assessments = assessments;
    }
}
