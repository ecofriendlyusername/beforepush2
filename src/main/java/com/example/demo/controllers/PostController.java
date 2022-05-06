package com.example.demo.controllers;

import com.example.demo.PaginationUtils;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.PostService;

import java.util.List;


@RestController
public class PostController {
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/post")
	public void post(@AuthenticationPrincipal OAuth2User principal, String title, String content, String tickers) {
		postService.post(principal.getAttribute("sub").toString(), title, content, tickers);
	}

	@GetMapping("/getposts")
	public ResponseEntity<?> post(@AuthenticationPrincipal OAuth2User principal) {
		return ResponseEntity.ok(postService.getPosts(principal.getAttribute("sub").toString()));
	}

	@PostMapping("/getpostspage")
	public ResponseEntity<?> post(@AuthenticationPrincipal OAuth2User principal, Pageable pageable) {
		Slice<Post> slice = postService.findSliceBy(pageable, principal.getAttribute("sub").toString());
		HttpHeaders headers = PaginationUtils.generateSliceHttpHeaders(slice);
		return new ResponseEntity<>(slice.getContent(), headers, HttpStatus.OK);
	}

	@DeleteMapping("/deletepost")
	public ResponseEntity<?> delete(@AuthenticationPrincipal OAuth2User principal, int id) {
		postService.delete(principal.getAttribute("sub").toString(), id);
		return ResponseEntity.ok("deleted");
	}

	@PostMapping("/testsomething")
	public void test(@RequestBody List<SomeTest> arr) {
		System.out.println(arr.get(0).getName());
	}
}
