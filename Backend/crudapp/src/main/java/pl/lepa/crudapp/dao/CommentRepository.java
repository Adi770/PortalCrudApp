package pl.lepa.crudapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lepa.crudapp.model.Comment;
import pl.lepa.crudapp.model.News;



@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByNews(News news, Pageable pageable);
    Page<Comment> findAllByNews_Id(Long newsId,Pageable pageable);
}
