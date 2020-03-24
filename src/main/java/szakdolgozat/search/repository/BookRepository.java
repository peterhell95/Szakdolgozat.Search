package szakdolgozat.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import szakdolgozat.search.model.Book;

@Repository("BookRepository")
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByTitleContaining(String filter);

    List<Book> findAllByAuthorContaining(String filter);

    List<Book> findAllByPriceBetween(Integer value1, Integer value2);

    //List<Book> findAllByRealrateBetween(Integer value1, Integer value2);

}