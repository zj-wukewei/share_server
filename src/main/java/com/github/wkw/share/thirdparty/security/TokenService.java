package com.github.wkw.share.thirdparty.security;

import com.github.wkw.share.Constants;
import com.github.wkw.share.domain.ShareUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class TokenService extends TokenBasedRememberMeServices {
    private static final String DELIMITER = ":";
    public static final Long TOKEN_LIFE = 3153600000L;

    public TokenService(UserDetailsService userDetailsService) {
        super(Constants.HTTP_HEADER.TOKEN, userDetailsService);
        setAlwaysRemember(true);
        setCookieName(Constants.HTTP_HEADER.TOKEN);
    }

    @Override
    protected String extractRememberMeCookie(HttpServletRequest request) {
        return request.getHeader(getCookieName());
    }


    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        logger.info("processAutoLoginCookie");
        if (cookieTokens.length != 3) {
            throw new InvalidCookieException("Cookie token did not contain 3 tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }

        long tokenExpiryTime;

        try {
            tokenExpiryTime = new Long(cookieTokens[1]).longValue();
        } catch (NumberFormatException nfe) {
            throw new InvalidCookieException(
                    "Cookie token[1] did not contain a valid number (contained '" + cookieTokens[1] + "')");
        }

//        if (isTokenExpired(tokenExpiryTime)) {
//            throw new InvalidCookieException("Cookie token[1] has expired (expired on '"
//                    + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
//        }

        ShareUserDetail userDetails = (ShareUserDetail) getUserDetailsService().loadUserByUsername(cookieTokens[0]);

        if (userDetails == null) {
            throw new InvalidCookieException("not have this user");
        }

        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, //userDetails.getExpiresAt().getTime(),
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAppType());

        if (!equals(expectedTokenSignature, cookieTokens[2])) {
            throw new InvalidCookieException("Cookie token[2] contained signature '"
                    + cookieTokens[2] + "' but expected '" + expectedTokenSignature + "'");
        }


        String token = encodeToken(tokenExpiryTime, userDetails.getUsername(), userDetails.getPassword(), userDetails.getAppType());
        String sToken = userDetails.getToken();
        if (!token.equals(sToken)) {
            throw new InvalidCookieException("repeated Login");
        }

        return userDetails;

    }

    public static String encodeToken(long tokenExpiryTime, String username, String password, Integer appId) {
        String signatureValue = makeTokenSignature(tokenExpiryTime, username, password, appId);
        return encodeToken(new String[]{username, Long.toString(tokenExpiryTime), signatureValue});
    }

    private static String makeTokenSignature(long tokenExpiryTime, String username, String password, Integer appId) {
        String data = username + ":" + tokenExpiryTime + ":" + password + ":" + appId + ":" + "Share";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(data.getBytes())));
    }

    public static Long tokenExpireDate(Long loginTime) {
        return loginTime + TOKEN_LIFE;
    }

    private static String encodeToken(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cookieTokens.length; i++) {
            sb.append(cookieTokens[i]);

            if (i < cookieTokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }

    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }
        return Utf8.encode(s);
    }

    public static String[] decodeToken(String cookieValue) throws InvalidCookieException {
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue = cookieValue + "=";
        }

        if (!Base64.isBase64(cookieValue.getBytes())) {
            throw new InvalidCookieException(
                    "Cookie token was not Base64 encoded; value was '" + cookieValue
                            + "'");
        }

        String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));

        String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText,
                DELIMITER);

        if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https"))
                && tokens[1].startsWith("//")) {
            // Assume we've accidentally split a URL (OpenID identifier)
            String[] newTokens = new String[tokens.length - 1];
            newTokens[0] = tokens[0] + ":" + tokens[1];
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
            tokens = newTokens;
        }

        return tokens;
    }

}
