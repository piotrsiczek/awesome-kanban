package com.spiczek.kanban.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Piotr Siczek
 */
@Configuration
public class CorsConfig {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////		http.requestMatchers().antMatchers("/oauth/token", "/rest/**")
////				.and()
////				.csrf().disable()
////				.authorizeRequests().anyRequest().permitAll()
////				.and()
////				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}



//	<sec:http pattern="/oauth/token" create-session="stateless"
//	authentication-manager-ref="authenticationManager">
//	<sec:intercept-url pattern="/oauth/token" access="IS_AUTHENTICATED_FULLY" />
//	<sec:anonymous enabled="false" />
//	<sec:http-basic entry-point-ref="clientAuthenticationEntryPoint" />
//	<sec:custom-filter ref="clientCredentialsTokenEndpointFilter" before="BASIC_AUTH_FILTER" />
//	<sec:access-denied-handler ref="oauthAccessDeniedHandler" />
//	</sec:http>
}
