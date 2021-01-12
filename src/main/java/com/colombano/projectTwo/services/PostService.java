package com.colombano.projectTwo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colombano.projectTwo.domain.Post;
import com.colombano.projectTwo.repository.PostRepository;
import com.colombano.projectTwo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
		
	public List<Post> findByTitle(String title) {
		return postRepository.searchTitle(title);
	}
	
}
