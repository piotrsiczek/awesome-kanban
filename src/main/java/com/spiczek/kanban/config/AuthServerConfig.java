package com.spiczek.kanban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author Piotr Siczek
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String WEB_CLIENT_ID = "kanban_web";
	private static final String WEB_CLIENT_SECRET = "wanban_web_1234";
	private static final String RESOURCE_ID = "kanban_api";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.jdbc(dataSource())
//				.withClient("sampleClientId")
//				.authorizedGrantTypes("implicit")
//				.scopes("read")
//				.autoApprove(true)
//				.and()
//				.withClient("clientIdPassword")
//				.secret("secret")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token")
//				.scopes("read");

		clients.inMemory()
				.withClient(WEB_CLIENT_ID)
				.secret(WEB_CLIENT_SECRET)
				.scopes("read", "write")
				.authorities("ROLE_USER", "ROLE_ADMIN")
				.authorizedGrantTypes("password")
				.resourceIds(RESOURCE_ID);
	}
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
//		return new JdbcTokenStore(dataSource());
	}
}