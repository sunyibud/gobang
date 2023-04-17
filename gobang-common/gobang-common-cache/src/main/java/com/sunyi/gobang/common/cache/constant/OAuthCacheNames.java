package com.sunyi.gobang.common.cache.constant;

/**
 * @author sunyi
 * @date 2023/04/07
 */
public interface OAuthCacheNames
{

    /**
     * oauth 授权相关key
     */
    String OAUTH_PREFIX = "gobang_oauth:";

    /**
     * 保存邮箱验证码 缓存使用key
     */
    String CAPTCHA_KEY = "captcha:key";

    /**
     * token 授权相关key
     */
    String OAUTH_TOKEN_PREFIX = OAUTH_PREFIX + "token:";

    /**
     * 保存token 缓存使用key
     */
    String ACCESS = OAUTH_TOKEN_PREFIX + "access:";

    /**
     * 刷新token 缓存使用key
     */
    String REFRESH_TO_ACCESS = OAUTH_TOKEN_PREFIX + "refresh_to_access:";

    /**
     * 根据uid获取保存的token key缓存使用的key
     */
    String UID_TO_ACCESS = OAUTH_TOKEN_PREFIX + "uid_to_access:";
}
