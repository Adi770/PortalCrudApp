package pl.lepa.crudapp.service;


import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_OmitComments;
import org.springframework.stereotype.Service;
import pl.lepa.crudapp.model.Comment;
import pl.lepa.crudapp.model.CommentDto;
import pl.lepa.crudapp.model.News;
import pl.lepa.crudapp.model.NewsDto;

import java.util.List;

@Service
public class NewsService {


    public void createNews(NewsDto newsDto) {
    }

    public void editNews(NewsDto newsDto) {
    }

    public List<News> newsList(int page,int size){
        return;
    }

    public News getNews(long id){
        return ;
    }

    public void deleteNews(long id){

    }

    public void createComment(long idNews, CommentDto commentDto){}

    public void editComment(long idComment,CommentDto commentDto){}

    public List<Comment> commentList(){
        return
    }

}
