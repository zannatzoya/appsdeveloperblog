package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {
	
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	WebClient webClient;
	
	
//	@GetMapping("/albums")
//	public String getAlbums(Model model, 
//			@AuthenticationPrincipal OidcUser principal) {
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//		
//		OAuth2AuthorizedClient oauth2client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
//		String jwtAccessToken = oauth2client.getAccessToken().getTokenValue();
//		System.out.println("jwtAccessToken= " + jwtAccessToken);
//		System.out.println("principal= " + principal);
//		
//		OidcIdToken idToken = principal.getIdToken();
//		String idTokenValue = idToken.getTokenValue();
//		System.out.println("idToken= " + idTokenValue);
//				
//		
//		String url = "http://localhost:8083/albums";
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Bearer "+jwtAccessToken);
//		
//		HttpEntity<List<AlbumRest>> entity = new HttpEntity<>(headers);
//		
//		ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(
//				url
//				, HttpMethod.GET, entity
//				, new ParameterizedTypeReference<List<AlbumRest>>() {});
//
//		List<AlbumRest> albums = responseEntity.getBody();
//        model.addAttribute("albums", albums);
//		
//		
//		return "albums";
//	}
	
	@GetMapping("/albums")
	public String getAlbums(Model model, 
			@AuthenticationPrincipal OidcUser principal) {
		
		System.out.println("principal= " + principal);
		
		String url = "http://localhost:8083/albums";
		
		List<AlbumRest> albums = webClient.get()
				.uri(url)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>(){})
				.block();
		
		model.addAttribute("albums", albums);
		
		
		return "albums";
	}
	
}
