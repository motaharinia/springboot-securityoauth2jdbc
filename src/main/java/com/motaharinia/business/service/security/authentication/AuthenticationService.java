package com.motaharinia.business.service.security.authentication;

import com.motaharinia.persistence.orm.adminuser.AdminUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    LoginExceptionModel loginFailed(HttpServletRequest request, HttpServletResponse response, LoginExceptionEnum loginExceptionEnum) throws Exception;

    Integer getFailureCountByUsername(String username) throws Exception;

    Boolean setFailureCountByUsername(String username, Integer failureCount) throws Exception;

    Boolean setFailureCountByUser(AdminUser user, Integer failureCount) throws Exception;
}
