package com.motaharinia.presentation.captchacode;

import com.motaharinia.business.service.captchacode.CaptchCodeTypeEnum;
import com.motaharinia.business.service.captchacode.CaptchaCodeBusinessExceptionKeyEnum;
import com.motaharinia.business.service.captchacode.CaptchaCodeModel;
import com.motaharinia.business.service.captchacode.CaptchaCodeService;
import com.motaharinia.msutility.customexception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@RestController
public class CaptchaCodeController {

    private CaptchaCodeService captchaCodeService;

    @Autowired
    public CaptchaCodeController(CaptchaCodeService captchaCodeService) {
        this.captchaCodeService = captchaCodeService;
    }

    @GetMapping(value = "/captchaCode/{captchaCodeType}/{reference}/")
    public byte[] captcha(HttpServletResponse response, HttpServletRequest request, @PathVariable("captchaCodeType") String captchaCodeType, @PathVariable("reference") String reference) throws Exception {

        CaptchaCodeModel captchaCodeModel = null;

        if (ObjectUtils.isEmpty(captchaCodeType)) {
            throw new BusinessException(getClass(), CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_TYPE_IS_NULL, "");
        }
        if (ObjectUtils.isEmpty(reference)) {
            throw new BusinessException(getClass(), CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_REFERENCE_IS_NULL, "");
        }
        reference = URLDecoder.decode(reference, "UTF-8");
        CaptchCodeTypeEnum captchCodeTypeEnum = CaptchCodeTypeEnum.valueOf(captchaCodeType);

        if (captchCodeTypeEnum == CaptchCodeTypeEnum.LOGIN_CHECK_BY_USERNAME) {
            captchaCodeModel = captchaCodeService.createOrUpdate(CaptchCodeTypeEnum.LOGIN_CHECK_BY_USERNAME, null, reference, request);
        } else {
            captchaCodeModel = captchaCodeService.createOrUpdate(captchCodeTypeEnum, Integer.parseInt(reference), null, request);

        }

        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        //access to custom header from 8080
        response.addHeader("Access-Control-Expose-Headers", "captchaKey");
        response.addHeader("captchaKey", captchaCodeModel.getCaptchaKey());

        return captchaCodeModel.getCaptchaImageByteArray();

    }

}
