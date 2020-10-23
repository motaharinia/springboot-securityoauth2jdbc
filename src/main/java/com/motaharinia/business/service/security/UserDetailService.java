package com.motaharinia.business.service.security;

import com.motaharinia.persistence.orm.adminuser.AdminUser;
import com.motaharinia.persistence.orm.adminuser.AdminUserRepository;
import com.motaharinia.persistence.orm.security.UserAuthenticatedDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;
@Component
public class UserDetailService implements UserDetailsService {

    private AdminUserRepository adminUserRepository;

    @Autowired
    public UserDetailService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AdminUser optionalUser = null;
        UserDetails userDetails=null;
        try {
            optionalUser = adminUserRepository.findUserByUsername(username);
            userDetails = new UserAuthenticatedDetail(optionalUser);
            new AccountStatusUserDetailsChecker().check(userDetails);
        } catch (Exception ex) {
            Logger.getLogger(UserDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userDetails;

    }
}
