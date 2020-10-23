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
            userAuthenticatedModel.setDateOfBirth(new CustomDate(adminUser.getDateOfBirth()));
            userAuthenticatedModel.setDefaultAdminUserContact_address(adminUser.getDefaultAdminUserContact().getAddress());
            userAuthenticatedModel.setFirstName(adminUser.getFirstName());
            userAuthenticatedModel.setGender_id(adminUser.getGender().getId());
            userAuthenticatedModel.setId(adminUser.getId());
            userAuthenticatedModel.setLastName(adminUser.getLastName());
            userAuthenticatedModel.setPassword(adminUser.getPassword());
            adminUser.getSkillSet().stream().forEach(item -> {
                AdminUserSkillModel adminUserSkillModel = new AdminUserSkillModel();
                adminUserSkillModel.setId(item.getId());
                adminUserSkillModel.setTitle(item.getTitle());
                userAuthenticatedModel.getSkillList().add(adminUserSkillModel);
            });
            userAuthenticatedModel.setUsername(adminUser.getUsername());
        }
        return userAuthenticatedModel;
    }
}
