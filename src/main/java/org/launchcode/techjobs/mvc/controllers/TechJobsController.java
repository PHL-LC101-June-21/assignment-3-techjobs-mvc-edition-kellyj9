package org.launchcode.techjobs.mvc.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;

public class TechJobsController {

    // HashMap to contain labels for navigation link choices
    private static HashMap<String, String> actionChoices = new HashMap<>();
    private static HashMap<String, String> columnChoices = new HashMap<>();

    // Constructor
    public TechJobsController() {
        // navigation options
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        // category options
        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("positionType", "Position Type");
        columnChoices.put("coreCompetency", "Skill");
    }

    // Getters

    @ModelAttribute("actions")
    public static HashMap<String, String> getActionChoices() {
        return actionChoices;
    }

    @ModelAttribute("columns")
    public static HashMap<String, String> getColumnChoices() {
        return columnChoices;
    }
}
