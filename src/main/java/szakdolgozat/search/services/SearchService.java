package szakdolgozat.search.services;

import java.util.List;

import szakdolgozat.search.dto.BookDTO;

public interface SearchService {
    public List<BookDTO> getAllBook();

    public List<BookDTO> getBooksByName(String filter);

    public List<BookDTO> getBooksByPrice(Integer filter, Integer filter2);

    public List<BookDTO> getBooksByRate(Integer filter, Integer filter2);

    public List<BookDTO> getBooksByAll(String filter, Integer filter2, Integer filter3);

}