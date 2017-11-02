package org.product.warranty.pro.services.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.ProUserRepository;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
	
	@Autowired
	@Qualifier("ProUserRepository")
	ProUserRepository proUserRepository;
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header");
			}

			final String token = authHeader.substring(7);
			try {
				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				ProUser user = proUserRepository.getUserByEmail(claims.getSubject());
				if(user == null || !user.getAuth_token().equals(token)) {
					throw new WPServiceException("Missing or invalid Authorization header", 500);
				}
				request.setAttribute("claims", claims);
			} catch (final SignatureException | WPServiceException | WPDataAccessException e) {
				throw new ServletException("Invalid token");
			}
			chain.doFilter(req, res);
		}
	}
}
