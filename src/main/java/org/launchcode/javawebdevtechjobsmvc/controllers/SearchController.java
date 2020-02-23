package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value="results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String columns) {

        model.addAttribute("columns", columnChoices);
        model.addAttribute("employers", JobData.getAllEmployers());
        model.addAttribute("locations", JobData.getAllLocations());
        model.addAttribute("positions", JobData.getAllPositionTypes());
        model.addAttribute("skills", JobData.getAllCoreCompetency());
        model.addAttribute("all", JobData.findAll());

        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            jobs = (JobData.findAll());
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(columns, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(columns) + ": " + searchTerm);
        }
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
            return "search";
    }

}
