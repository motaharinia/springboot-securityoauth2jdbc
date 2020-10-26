package com.motaharinia.presentation.security;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.persistence.orm.adminuser.AdminUser;
import com.motaharinia.presentation.adminuser.AdminUserModel;
import com.motaharinia.presentation.adminuserskill.AdminUserSkillModel;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public class UserAuthenticatedModel extends AdminUserModel implements Serializable {
    public static UserAuthenticatedModel getUserAuthenticatedModel(AdminUser adminUser) throws UtilityException {
        UserAuthenticatedModel userAuthenticatedModel = new UserAuthenticatedModel();
        if (!ObjectUtils.isEmpty(adminUser)) {
            userAuthenticatedModel.setId(adminUser.getId());
            userAuthenticatedModel.setUsername(adminUser.getUsername());
            userAuthenticatedModel.setPassword(adminUser.getPassword());
            userAuthenticatedModel.setFirstName(adminUser.getFirstName());
            userAuthenticatedModel.setLastName(adminUser.getLastName());
            userAuthenticatedModel.setDateOfBirth(new CustomDate(adminUser.getDateOfBirth()));
            if (!ObjectUtils.isEmpty(adminUser.getDefaultAdminUserContact())) {
                userAuthenticatedModel.setDefaultAdminUserContact_address(adminUser.getDefaultAdminUserContact().getAddress());
            }
            if (!ObjectUtils.isEmpty(adminUser.getGender())) {
                userAuthenticatedModel.setGender_id(adminUser.getGender().getId());
            }
            if (!ObjectUtils.isEmpty(adminUser.getSkillSet())) {
                adminUser.getSkillSet().stream().forEach(item -> {
                    AdminUserSkillModel adminUserSkillModel = new AdminUserSkillModel();
                    adminUserSkillModel.setId(item.getId());
                    adminUserSkillModel.setTitle(item.getTitle());
                    userAuthenticatedModel.getSkillList().add(adminUserSkillModel);
                });
            }
        }
        return userAuthenticatedModel;
    }
}
