package pl.lepa.crudapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lepa.crudapp.model.ResetToken;

import java.util.Optional;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken,Long> {
    Optional<ResetToken> findByToken(String token);
}
