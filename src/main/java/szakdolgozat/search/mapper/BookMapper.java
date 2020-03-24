package szakdolgozat.search.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import szakdolgozat.search.dto.BookDTO;
import szakdolgozat.search.model.Book;

@Mapper
public interface BookMapper {

    public BookDTO bookToBookDTO(Book source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rate", ignore = true)
    @Mapping(target = "ratecount", ignore = true)
    public Book bookDTOToBook(BookDTO source);

    @IterableMapping(qualifiedByName = "bookToBookDTO")
    public default List<BookDTO> bookListToBookDTOList(List<Book> source) {
        return source == null ? new ArrayList<>() : source.stream().map(this::bookToBookDTO).collect(Collectors.toList());
    }

    public Book bookDTOToBookUpdate(BookDTO source);

}
