package de.robinschuerer.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;

import de.robinschuerer.norcom.searchservice.SearchService;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<ArrayNode> getData(final String query){

        final ArrayNode result = searchService.search(query);

        return ResponseEntity.ok(result);
    }

}
