package com.colombano.projectTwo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.colombano.projectTwo.domain.Post;
import com.colombano.projectTwo.domain.User;
import com.colombano.projectTwo.dto.AuthorDTO;
import com.colombano.projectTwo.dto.CommentDTO;
import com.colombano.projectTwo.repository.PostRepository;
import com.colombano.projectTwo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		this.userRepository.deleteAll();
		this.postRepository.deleteAll();
		
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Goin' on a new trip!", "Hoping the Neatherlands are cool as everyone says ;)", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("10/01/2020"), "This year is gonna be nice *-*", "Have already any of you came to this place?", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Wish u a nice month ;)", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("I do hope so dude", sdf.parse("11/01/2020"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("I really wannna that be true :3", sdf.parse("12/01/2020"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1));
		post2.getComments().addAll(Arrays.asList(c2, c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
