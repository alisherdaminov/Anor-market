package Anor.market.presentation.controller.catalog.product.comments;

import Anor.market.application.dto.catalog.product.comments.create.CommentsCreateDTO;
import Anor.market.application.dto.catalog.product.comments.dto.CommentsDTO;
import Anor.market.application.service.catalog.product.comments.CommentsServiceImpl;
import Anor.market.presentation.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class Comments {

    @Autowired
    private CommentsServiceImpl service;

    @PostMapping("/{productId}")
    public ResponseEntity<AppResponse<CommentsDTO>> createComment(
            @PathVariable("productId") String productId,
            @RequestBody CommentsCreateDTO createDTO) {
        return ResponseEntity.ok().body(new AppResponse<>(service.createComment(productId, createDTO), "success", new Date()));
    }

    @GetMapping("/list")
    public ResponseEntity<AppResponse<List<CommentsDTO>>> getAllComments() {
        return ResponseEntity.ok().body(new AppResponse<>(service.getAllComments(), "success", new Date()));
    }

    @DeleteMapping("/{commentsId}")
    public ResponseEntity<AppResponse<String>> deleteCommentById(@PathVariable("commentsId") String commentsId) {
        return ResponseEntity.ok().body(new AppResponse<>(service.deleteCommentById(commentsId), "success", new Date()));
    }

}
