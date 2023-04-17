package com.sunyi.gobang.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * @author Sunyi
 * @date 2023/4/8
 */
public class JwtUtil
{
    //方便yaml文件中设置
    private static final long expire = 604800;//jwt过期时间（秒）：7天
    private static final String secret = "sunyiUniversityGraduateDesign234";//密钥（随便填够32位就行）
    private static final String header = "Authorization";//jwt名称

    // 生成jwt
    public static String generateToken(Long userId)
    {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId.toString())
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)// 7天过期
                .signWith(SignatureAlgorithm.HS512, secret)//密钥
                .compact();
    }

    // 解析jwt
    public static Claims getClaimByToken(String jwt)
    {
        try
        {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e)
        {
            return null;
        }
    }

    // jwt是否过期
    public static boolean isTokenExpired(Claims claims)
    {
        //过期时间是否在当前时间之前
        return claims.getExpiration().before(new Date());
    }
}
