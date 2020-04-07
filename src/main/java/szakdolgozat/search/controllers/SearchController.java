package szakdolgozat.search.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import szakdolgozat.search.dto.BookDTO;
import szakdolgozat.search.services.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService service;

    @GetMapping("/list")
    public List<BookDTO> getAllBook() {
        return service.getAllBook();
    }

    @GetMapping("/searchByAll")
    public List<BookDTO> getBooksByAll(
            @RequestParam(value = "filter") String filter,
            @RequestParam(value = "filter2") Integer filter2,
            @RequestParam(value = "filter3") Integer filter3) {

        return service.getBooksByAll(filter, filter2, filter3);
    }

    @GetMapping("/searchByName")
    public List<BookDTO> getBooksByName(@RequestParam(value = "filter") String filter) {

        return service.getBooksByName(filter);
    }

    @GetMapping("/searchByPrice")
    public List<BookDTO> getBooksByPrice(@RequestParam(value = "filter") Integer filter, @RequestParam(value = "filter2") Integer filter2) {

        return service.getBooksByPrice(filter, filter2);
    }

    @GetMapping("/searchByRate")
    public List<BookDTO> getBooksByRate(@RequestParam(value = "filter") Integer filter, @RequestParam(value = "filter2") Integer filter2) {

        return service.getBooksByRate(filter, filter2);
    }
}