package com.charles.shiro.springbootshiro.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangshengli
 */
@Configuration
public class ShiroConfig {

    /**
     * 将自己的验证方式加入容器
     */

    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        // 启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        customRealm.setAuthenticationCachingEnabled(true);
        // 启用授权缓存，即缓存AuthorizationInfo信息，默认false,一旦配置了缓存管理器，授权缓存默认开启
        customRealm.setAuthorizationCachingEnabled(true);

        return customRealm;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //配置缓存管理器
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * @return ShiroFilterFactoryBean
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/loginFail");
        bean.setUnauthorizedUrl("/unpermission");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/loginFail","anon");
        map.put("/login", "anon");
        map.put("/unpermission", "anon");
        map.put("/**", "authc,perms[add]");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * 缓存管理器
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        MemoryConstrainedCacheManager mccm = new MemoryConstrainedCacheManager();
        return mccm;
    }

}