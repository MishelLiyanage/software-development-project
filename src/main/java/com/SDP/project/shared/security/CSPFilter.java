package com.SDP.project.shared.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Content-Security-Policy",
                "default-src 'self'; " +
                        "script-src 'self' https://sandbox.payhere.lk https://www.google-analytics.com https://maxcdn.bootstrapcdn.com; " +
                        "style-src 'self' 'unsafe-inline' https://maxcdn.bootstrapcdn.com; " +
                        "img-src 'self' data:; " +
                        "connect-src 'self' https://sandbox.payhere.lk; " +
                        "frame-src https://sandbox.payhere.lk;"
        );

        chain.doFilter(request, response);
    }
}
