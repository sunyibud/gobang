package com.sunyi.gobang.common.cache.constant;

/**
 * 缓存名字
 *
 * @author sunyi
 * @date 2023/04/07
 */
public interface CacheNames extends OAuthCacheNames,BizCacheNames, UserCacheNames {
    /**
     *
     * 参考CacheKeyPrefix
     * cacheNames 与 key 之间的默认连接字符
     */
    String UNION = "::";

    /**
     * key内部的连接字符（自定义）
     */
    String UNION_KEY = ":";

}
