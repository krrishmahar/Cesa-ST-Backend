package com.cesaST.backend.config.filter;

import com.cesaST.backend.model.Team;
import com.cesaST.backend.model.TeamPrincipal;
import com.cesaST.backend.repo.TeamRepository;
import com.cesaST.backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final TeamRepository teamRepo;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        String token;
        String teamName;

        if (auth != null && auth.startsWith("Bearer ")) {
            token = auth.substring(7);
            teamName = jwtService.extractUserName(token);

            if (teamName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                Team team = teamRepo.findByTeamName(teamName).orElseThrow(() -> new RuntimeException("Invalid Team"));

                if (team != null) {
                    TeamPrincipal userDetails = new TeamPrincipal(team);

                    if (jwtService.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
