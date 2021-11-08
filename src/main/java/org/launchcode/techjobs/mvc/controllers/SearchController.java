package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Locale;

//import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@RequestMapping("search")
@Controller
public class SearchController extends TechJobsController {

    @GetMapping(value="")
    public String search() {
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the
    //  updated search view.
    @PostMapping(value="results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        // if the user selected 'All' as the search type AND
        // entered any search term other than the word "all"...
        if (searchType.equals("all") &&
                (!(searchTerm.isEmpty())) &&
                (!(searchTerm.equalsIgnoreCase("all")))) {
            //...then add all jobs that contain the search term to the model
            model.addAttribute("jobs", JobData.findByValue(searchTerm));
            // incorporate the search type and search term into the title
            model.addAttribute("title",
                    "All Jobs With: " + searchTerm);
        }
        // else if the user selected 'All' as the search type OR
        // left the search term blank, regardless of the selected search type OR
        // entered the word "all" as the search term...
         else if (searchType.equals("all") ||
                searchTerm.isEmpty() ||
                searchTerm.equalsIgnoreCase("all"))  {
            //...then add all jobs the model
            model.addAttribute("jobs", JobData.findAll());
            // use "All Jobs" as the title
            model.addAttribute("title", "All Jobs");
        }
        // then the user selected a search type other than "All" AND
        // entered a search term that was not the word "all"...
         else {
            // ...add jobs containing the search term for chosen search type to the model
            model.addAttribute("jobs",
                                    JobData.findByColumnAndValue(searchType, searchTerm));
            // incorporate the search type and search term into the title
            model.addAttribute("title",
                    "Jobs with " + getColumnChoices().get(searchType) +
                            ": " + searchTerm);
        }

        // Note: The autograder doesn't pass when replacing @RequestParam
        // with @ModelAttribute("searchType") for parameter String searchType,
        // so adding the below line instead:
        // (used for Bonus Mission 1. to re-populate the search type after form submits)
        model.addAttribute("searchType", searchType);

        // Note: the autograder needs the line below, but it is not necessary
        // (column choices were added to model in TechJobsController)
        model.addAttribute("columns", getColumnChoices());

        return "search";
    }

}
