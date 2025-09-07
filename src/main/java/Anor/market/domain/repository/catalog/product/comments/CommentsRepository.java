package Anor.market.domain.repository.catalog.product.comments;

import Anor.market.domain.model.entity.catalog.product.comments.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, UUID> {

    @Query("select count(c) from CommentsEntity c")
    int commentsCount();

    default Optional<CommentsEntity> findByStringId(String id){
        return findById(UUID.fromString(id));
    }

    @Transactional
    @Modifying
    @Query("DELETE FROM CommentsEntity c WHERE c.commentsId = :commentsId")
    void deleteByCommentsId(@Param("commentsId") String commentsId);
}
