package com.example.demo.controllers;

import com.example.demo.model.Account;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.services.CommentService;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private CommentService comService;

    @Autowired
    public CommentController(CommentService comService) {
        this.comService = comService;
    }

    @PostMapping("/comment")
    public void post(@AuthenticationPrincipal OAuth2User principal, int postId, String content) {
        comService.post(principal.getAttribute("sub").toString(), postId, content);
    }

    @PostMapping("/deletecomment")
    public void deleteComment(@AuthenticationPrincipal OAuth2User principal, int commentId) {
        comService.deleteComment(principal.getAttribute("sub").toString(), commentId);
    }
}
