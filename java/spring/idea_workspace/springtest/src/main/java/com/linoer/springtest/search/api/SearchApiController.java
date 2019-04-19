package com.linoer.springtest.search.api;

import com.linoer.springtest.service.SearchService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchApiController {
    private SearchService searchService;
    private final static Log logger = LogFactory.getLog(SearchApiController.class);
    @Autowired
    public SearchApiController(SearchService searchService){
        this.searchService = searchService;
    }

    @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
    public List search(@PathVariable String searchType){
        logger.info("search type :" + searchType);
        return searchService.search(searchType);
    }


}
