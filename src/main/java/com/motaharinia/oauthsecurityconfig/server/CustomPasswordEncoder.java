package com.motaharinia.oauthsecurityconfig.server;

import com.motaharinia.business.service.security.PasswordEncoderGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String encodedPassword = PasswordEncoderGenerator.generate(rawPassword.toString());
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordEncoderGenerator.check(rawPassword.toString(), encodedPassword);
    }

}
