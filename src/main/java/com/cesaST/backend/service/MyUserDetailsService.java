package com.cesaST.backend.service;


import com.cesaST.backend.model.Team;
import com.cesaST.backend.model.TeamPrincipal;
import com.cesaST.backend.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TeamRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Team team = userRepo.findByTeamName(username);
        if (team == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new TeamPrincipal(team);
    }
}
