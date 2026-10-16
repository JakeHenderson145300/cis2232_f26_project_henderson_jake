package ca.hccis.pocketLedger.graphql;

import ca.hccis.pocketLedger.jpa.entity.PersonalExpenseTracker;

public class SkillsAssessmentSquashTechnicalInput {
    public String assessmentDate;
    public String createdDateTime;
    public String athleteName;
    public String assessorName;
    public Integer forehandDrives;
    public Integer backhandDrives;
    public Integer forehandVolleyMax;
    public Integer forehandVolleySum;
    public Integer backhandVolleyMax;
    public Integer backhandVolleySum;

    public PersonalExpenseTracker toEntity() {
        PersonalExpenseTracker e = new PersonalExpenseTracker();
        e.setAssessmentDate(this.assessmentDate);
        e.setCreatedDateTime(this.createdDateTime);
        e.setAthleteName(this.athleteName);
        e.setAssessorName(this.assessorName);
        e.setForehandDrives(this.forehandDrives);
        e.setBackhandDrives(this.backhandDrives);
        e.setForehandVolleyMax(this.forehandVolleyMax);
        e.setForehandVolleySum(this.forehandVolleySum);
        e.setBackhandVolleyMax(this.backhandVolleyMax);
        e.setBackhandVolleySum(this.backhandVolleySum);
        // technicalScore will be computed in BO before save
        return e;
    }
}