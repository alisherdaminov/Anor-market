package Anor.market.application.service.catalog.product.comments;

import Anor.market.application.dto.catalog.product.comments.create.CommentsCreateDTO;
import Anor.market.application.dto.catalog.product.comments.dto.CommentsDTO;
import Anor.market.application.mapper.catalog.product.comments.CommentsMapper;
import Anor.market.domain.model.auth.UserEntity;
import Anor.market.domain.model.catalog.product.comments.CommentsEntity;
import Anor.market.domain.model.catalog.product.products.ProductEntity;
import Anor.market.domain.repository.auth.UserRepository;
import Anor.market.domain.repository.catalog.product.comments.CommentsRepository;
import Anor.market.domain.repository.catalog.product.products.ProductRepository;
import Anor.market.domain.service.catalog.product.comments.CommentsService;
import Anor.market.infrastucture.config.validation.SpringSecurityValid;
import Anor.market.shared.exceptions.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CommentsServiceImpl implements CommentsService three main functions which are able to create, get and delete comments
 * The comments Service is for giving products comment whether it is ok or not!
 * the user can only create comments, though others
 * the Admin has a full permission to do the all.
 *
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentsMapper commentsMapper;


    /// CREATE A COMMENT WITH BOTH USER OR ADMIN
    @Override
    public CommentsDTO createComment(String productId, CommentsCreateDTO createDTO) {
        //the current user
        Integer userId = SpringSecurityValid.getCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AppBadException("User id not found!"));
        //products
        ProductEntity product = productRepository.findByStringId(productId).orElseThrow(() -> new AppBadException("Product id not found!"));

        // create comments
        CommentsEntity comments = commentsMapper.toEntity(createDTO);
        comments.setProductEntityComments(product);//<---------> PARENT LINK

        //the username and product description is coming from other databases
        comments.setCommentersName(user.getFirstName());
        comments.setProductDescription(product.getProductDescription());

        //saved into DATABASE
        commentsRepository.save(comments);
        //to dto
        return commentsMapper.toDTO(comments);
    }

    /// GET ALL COMMENTS
    @Override
    public List<CommentsDTO> getAllComments() {
        return commentsRepository.findAll(Sort.by(Sort.Direction.DESC, "localDate")).stream().map(commentsMapper::toDTO).toList();
    }

    /// GET ALL COMMENTS COUNT
    @Override
    public int getAllCommentsCount() {
        return commentsRepository.commentsCount();
    }

    /// DELETE THE COMMENTS BY ID
    @Override
    public String deleteCommentById(String commentsId) {
        commentsRepository.deleteByCommentsId(commentsId);
        return "Deleted!";
    }
}
