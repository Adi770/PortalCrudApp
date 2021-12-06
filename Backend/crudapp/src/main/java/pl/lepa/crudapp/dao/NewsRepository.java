package pl.lepa.crudapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.lepa.crudapp.model.News;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    List<News> findAll();

    Page<News> findAll(Pageable pageable);
}
