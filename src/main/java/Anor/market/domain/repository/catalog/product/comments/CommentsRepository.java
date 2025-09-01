package Anor.market.domain.repository.catalog.product.comments;

import Anor.market.domain.model.entity.catalog.product.comments.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, String> {

    @Query("select count(c) from CommentsEntity c")
    int commentsCount();
}
