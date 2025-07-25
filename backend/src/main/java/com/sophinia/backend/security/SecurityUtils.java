package com.sophinia.backend.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static boolean hasRole ( String expectedRole ) {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principle.toString());
        if (principle instanceof UserDetails userDetails) {
            String actualRole = userDetails.getClass().getSimpleName();
            return actualRole.equalsIgnoreCase( expectedRole );
        }

        return false;
    }

}
