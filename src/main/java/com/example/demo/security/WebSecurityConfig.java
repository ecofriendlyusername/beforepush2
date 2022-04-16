package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.example.demo.AccountRepository;
import com.example.demo.controllers.PostController;
import com.example.demo.model.Account;
import com.example.demo.services.AccountService;
import com.example.demo.services.PostService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	AccountService accService;

	@Autowired
	public WebSecurityConfig(AccountService accService) {
		this.accService = accService;
	}
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http.cors().and().csrf().disable()
            .authorizeRequests(a -> a
                .antMatchers("/", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            ) .logout(l -> l
						.logoutSuccessUrl("/").permitAll()
				)
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .oauth2Login()
                    .userInfoEndpoint().oidcUserService(this.oidcUserService());
        // @formatter:on
    }

	private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
		final OidcUserService delegate = new OidcUserService();
	    return (userRequest) -> {
	    	OidcUser user = delegate.loadUser(userRequest);
	    	
	        if (!accService.existsBySub(user.getAttribute("sub"))) {
	        	accService.register(user.getAttribute("sub").toString(), user.getAttribute("email"));
	        }
	        return user;
	    };
	}
}
