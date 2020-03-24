package szakdolgozat.search.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;
import szakdolgozat.search.dto.BookDTO;
import szakdolgozat.search.mapper.BookMapper;
import szakdolgozat.search.repository.BookRepository;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {
    @Autowired
    @Getter
    @Setter
    private BookRepository repository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBook() {
        return bookMapper.bookListToBookDTOList(repository.findAll());
    }

    public List<BookDTO> getBooksByAll(String filter, Integer filter2, Integer filter3) {
        List<BookDTO> filteredList = Collections.<BookDTO>emptyList();
        List<BookDTO> filteredList2 = Collections.<BookDTO>emptyList();
        filteredList = this.getBooksByName(filter);
        filteredList2 = this.getBooksByPrice(filter2, filter3);

        Set<BookDTO> result = filteredList.stream()
                .distinct()
                .filter(filteredList2::contains)
                .collect(Collectors.toSet());
        filteredList.clear();
        filteredList.addAll(result);
        return filteredList;
    }

    public List<BookDTO> getBooksByRate(Integer filter, Integer filter2) {
        List<BookDTO> filteredList = Collections.<BookDTO>emptyList();
        if (filter == null || filter2 == null)
            return filteredList;

        /*filteredList = bookMapper.bookListToBookDTOList(repository.findAllByRealrateBetween(filter, filter2));
        
        //Clear the same elements 
        Set<BookDTO> set = new HashSet<>(filteredList);
        filteredList.clear();
        filteredList.addAll(set);*/

        return filteredList;
    }

    public List<BookDTO> getBooksByPrice(Integer filter, Integer filter2) {
        List<BookDTO> filteredList = Collections.<BookDTO>emptyList();
        if (filter == null || filter2 == null)
            return filteredList;

        filteredList = bookMapper.bookListToBookDTOList(repository.findAllByPriceBetween(filter, filter2));

        //Clear the same elements 
        Set<BookDTO> set = new HashSet<>(filteredList);
        filteredList.clear();
        filteredList.addAll(set);

        return filteredList;
    }

    public List<BookDTO> getBooksByName(String filter) {
        if (filter.isEmpty())
            return bookMapper.bookListToBookDTOList(repository.findAll());

        List<BookDTO> filteredList = bookMapper.bookListToBookDTOList(repository.findAllByTitleContaining(filter));
        filteredList.addAll(bookMapper.bookListToBookDTOList(repository.findAllByAuthorContaining(filter)));

        //Clear the same elements 
        Set<BookDTO> set = new HashSet<>(filteredList);
        filteredList.clear();
        filteredList.addAll(set);

        return filteredList;
    }

}