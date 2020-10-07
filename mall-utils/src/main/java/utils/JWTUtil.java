package utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.*;

/**
 * @Author: YRB
 * @Description:
 * @Date: Create in 10:11 2020/10/6
 */
public class JWTUtil {
    /**
     * 设置过期时间及密匙
     * CALENDAR_FIELD 时间单位
     * CALENDAR_INTERVAL 有效时间
     * SECRET_KEY 密匙
     */
    public static final int CALENDAR_FIELD = Calendar.MINUTE;
    public static final int CALENDAR_INTERVAL = 60 * 24;
    private static final String SECRET_KEY = "6A50A18D70FA63636645C65459F1D77A";
    private static final String subject = "admin";

    /**
     * 创建Token
     *
     * @param id 自己需要存储进token中的信息
     * @return token
     */
    public static String createToken(Integer id) {
        // 头部
        HashMap<String, Object> headerMap = new HashMap<>(4);
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        // 当前时间与过期时间
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        //延迟一天，每次获得这个TOKEN就延迟一天
        calendar.add(CALENDAR_FIELD,CALENDAR_INTERVAL);
        date=calendar.getTime();


        return Jwts.builder().setHeader(headerMap)
                .setSubject(subject)
                .claim("usrid",id)
                .setIssuedAt(new Date())//创建时间
                .setExpiration(date)       //有效时间
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact() //指定加密算法
                ;


    }

    /**
     * 验证、解析Token
     *
     * @param token 用户提交的token
     * @return 该token中的信息
     */
    public static Claims CheckToken(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
//test
//    public static void main(String[] args) {

//        String token = JWTUtil.createToken(12);
//        System.out.println(token);
//        Claims claims = JWTUtil.CheckToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsInVzcmlkIjoxMiwiaWF0IjoxNjAxOTcwMzYyLCJleHAiOjE2MDIwNTY3NjF9.vJCKKwJFXRcmtyCNVgY9Trtuo0XfRR5pdTKk-RhqazY");
//        System.out.println(claims.get("usrid"));
//    }
}


