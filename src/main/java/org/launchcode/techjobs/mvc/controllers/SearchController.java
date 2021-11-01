package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@RequestMapping("search")
@Controller
public class SearchController {

    @GetMapping(value="")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value="results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchType.equals("all") && (!(searchTerm.isEmpty())) && (!(searchTerm.equals("all")))) {
            model.addAttribute("jobs", JobData.findByValue(searchTerm));
            model.addAttribute("title", searchTerm);

        } else if (searchType.equals("all") || searchTerm.isEmpty() || searchTerm.equals("all"))  {

            model.addAttribute("jobs", JobData.findAll());
            model.addAttribute("title", "All Jobs");
        }
        else {
            model.addAttribute("jobs", JobData.findByColumnAndValue(searchType, searchTerm));
            model.addAttribute("title", searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        return "search";

    }
}
