package com.spiczek.kanban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Piotr Siczek
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public MongoUserDetailsService mongoUserDetailsService() {
		return new MongoUserDetailsService();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("admin").password("pass").roles("USER");
//	}

	@Autowired
	protected void configureAuthenticationWithMongo(AuthenticationManagerBuilder auth, MongoUserDetailsService mongoUserDetailsService) throws Exception {
		auth.userDetailsService(mongoUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/signup", "/about").permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic().and().csrf().disable();
	}

	//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**")
//				.authorizeRequests()
//					.antMatchers("/", "/login**", "/webjars/**")
//					.permitAll()
//				.anyRequest()
//					.authenticated();
//	}

//    @Autowired
//    private OAuth2ClientContext oauth2ClientContext;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**")
//            .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//    }
//
//    private Filter ssoFilter() {
//        OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
//        OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
//        githubFilter.setRestTemplate(githubTemplate);
//        githubFilter.setTokenServices(new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId()));
//
//        return githubFilter;
//    }
//
//    @Bean
//    @ConfigurationProperties("github.client")
//    public OAuth2ProtectedResourceDetails github() {
//        return new AuthorizationCodeResourceDetails();
//    }
//
//    @Bean
//    @ConfigurationProperties("github.resource")
//    public ResourceServerProperties githubResource() {
//        return new ResourceServerProperties();
//    }
//
//    @Bean
//    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter);
//        registration.setOrder(-100);
//        return registration;
//    }
}
