package com.tcr;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tcr.core.model.Skill;
import com.tcr.core.model.User;
import com.tcr.core.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.tcr"})
@EntityScan(basePackages = {"com.tcr.core"})
@ComponentScan(basePackages = {"com.tcr"})
public class DemoApplication extends SpringBootServletInitializer{
    
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
	
	@PostConstruct
	public void setupDbWithData(){
		User user= new User("Ashish", null);
		user.setSkills(Arrays.asList(new Skill("java"), new Skill("js")));
		user= userRepository.save(user);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(DemoApplication.class);
	    }
}
