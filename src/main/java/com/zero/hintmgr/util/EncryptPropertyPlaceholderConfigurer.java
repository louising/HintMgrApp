package com.zero.hintmgr.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/** 
<bean name="propertyConfigurer" class="com.zero.cem.framework.util.EncryptPropertyPlaceholderConfigurer">
    <property name="locations">
        <list>
            <value>classpath:config.properties</value>
        </list>
    </property>
</bean>

 * @author Administrator
 * @version 2018-5-30
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames = { "jdbc.username", "jdbc.password", "redis.auth" };

    private boolean isEncryptProp(String propertyName) {
        for (String encryptName : encryptPropNames) {
            if (encryptName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            String decryptValue = propertyValue;
            try {
                decryptValue = SecurityUtil.encode(propertyValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return decryptValue;
        } else {
            return propertyValue;
        }
    }
}