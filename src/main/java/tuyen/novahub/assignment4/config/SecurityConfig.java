package tuyen.novahub.assignment4.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tuyen.novahub.assignment4.service.MyUserDetailsService;



@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = MyUserDetailsService.class)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired
 private UserDetailsService userDetailsService;

 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
  auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
 } 

 @Override

 protected void configure(HttpSecurity http) throws Exception {
	 http
	 .authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
	 .and()
	    	.formLogin()
				    .loginPage("/show-login")
				    .usernameParameter("email").passwordParameter("password")
				    .defaultSuccessUrl("/login-success")
				    .loginProcessingUrl("/login")
				    .failureUrl("/show-login?msg=0")
	  .and()
		    .logout()
		    	.logoutUrl("/logout")
		    	.logoutSuccessUrl("/")
		.and()
	  		.exceptionHandling().accessDeniedPage("/403")
	  .and()
	    .csrf().disable();

 }

 @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
     return new BCryptPasswordEncoder();
    }
 
 @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("resources/js/**","resources/css/**",
				"resources/img/**","resources/assets/**",
				"resources/contactform/**",
				"resources/fonts/**");
	}

}