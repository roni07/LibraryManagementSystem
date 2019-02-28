package library.management.system.librarymanagement.security_constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import library.management.system.librarymanagement.acl_enum.ServicesEnum;
import library.management.system.librarymanagement.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static library.management.system.librarymanagement.security_constant.SecurityConstant.SECRET;
import static library.management.system.librarymanagement.security_constant.SecurityConstant.TOKEN_PREFIX;

public class JwtGenerator {

    public static String generateToken(User user) {

        Claims claim = Jwts.claims();

        final List<Integer> bitMasks = BitMaskHandler.generateBitMask(new ArrayList<>(Arrays.asList(ServicesEnum.BOOK_ADMIN,
                ServicesEnum.ISSUE_ADMIN)));

        claim.setSubject(user.getUserId());
        claim.put("bitMask", bitMasks);

        String token = Jwts.builder()
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        return token;
    }


    public static String parseTokenGetUserName(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }

    public static List parseTokenGetBitMask(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .get("bitMask", List.class);
    }
}
