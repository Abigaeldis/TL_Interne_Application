package com.m2i.TL_Interne_Application.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.m2i.TL_Interne_Application.entities.Utilisateur;
import com.m2i.TL_Interne_Application.services.UtilisateurService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggedInFilter implements Filter {
	@Autowired private UtilisateurService service;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) response;
			
			/*
			 * A ENLEVER!!!!!!!!!!!!
			 */
			chain.doFilter(request, response);
		/*
		 * Si on essaie d'accéder au endpoint de login, on autorise l'accès
		 * sans vérifier d'autre condition
		 */
//		if ("/login".equals(httpReq.getServletPath())
//				|| "OPTIONS".equals(httpReq.getMethod())) {
//			chain.doFilter(request, response);
//			return;
//		}

		/*
		 * Si le token n'est pas renseigné, on interdit l'accès
		 */
//		String auth = httpReq.getHeader("token");
//		if (auth == null || auth.isBlank()) {
//			httpResp.sendError(HttpStatus.UNAUTHORIZED.value());
//			return;
//		}
		
		/*
		 * Si le token est renseigné mais ne correspond à aucun user
		 * on interdit l'accès
		 * Sinon, on autorise l'accès
		 */
//		Utilisateur user = service.getByToken(auth);
//		if (user == null) {
//			httpResp.sendError(HttpStatus.UNAUTHORIZED.value());
//		} else {
//			chain.doFilter(request, response);
//		}
	}
}
