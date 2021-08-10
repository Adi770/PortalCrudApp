package pl.lepa.crudapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lepa.crudapp.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
