package Anor.market.domain.service.catalog.product.comments;

import Anor.market.application.dto.catalog.product.comments.create.CommentsCreateDTO;
import Anor.market.application.dto.catalog.product.comments.dto.CommentsDTO;

import java.util.List;

public interface CommentsService {

    CommentsDTO createComment(String productId, CommentsCreateDTO createDTO);

    List<CommentsDTO> getAllComments();

    int getAllCommentsCount();

    String deleteCommentById(String commentsId);
}
