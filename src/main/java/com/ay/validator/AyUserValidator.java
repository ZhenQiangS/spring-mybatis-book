package com.ay.validator;

import com.ay.model.AyUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 描述：用户数据校验类
 *
 * @author ZQS
 * @create 2020/5/23
 */
@Component
public class AyUserValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return AyUser.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        AyUser p = (AyUser) target;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "年龄不能小于 0 岁");
        } else if (p.getAge() > 150) {
            errors.rejectValue("age", "年龄不能大于150岁");
        }

    }
}
