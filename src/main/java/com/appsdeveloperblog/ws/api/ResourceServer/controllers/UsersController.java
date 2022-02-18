package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import com.appsdeveloperblog.ws.api.ResourceServer.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

	@GetMapping("/status/check")
	public String status() {
		return "Working...";
	}

	@PreAuthorize("#id == #jwt.subject")
//	@PreAuthorize("hasRole('developer') or hasAnyAuthority('ROLE_developer') or #id == #jwt.subject")
//	@PostAuthorize()
//	@Secured("ROLE_developer")
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
	}


	@PostAuthorize("returnUbject.userId == $jwt.subject")
	@GetMapping("/{id}")
	public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		return new UserRest("valek", "cher", "");
	}
}
