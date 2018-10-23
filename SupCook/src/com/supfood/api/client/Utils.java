package com.supfood.api.client;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utils {
    public static void redirectWithError(HttpServletResponse response, String redirectUrl, String identifier) throws IOException {
        Cookie cookie = new Cookie("error", identifier);
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        response.sendRedirect(redirectUrl);
    }
}
