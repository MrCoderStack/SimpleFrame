package com.mrcoder.framecommon.config.http;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.impl.client.DefaultRedirectStrategy;

@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class MyRedirectStrategy extends DefaultRedirectStrategy {

    public static final MyRedirectStrategy INSTANCE = new MyRedirectStrategy();

    private static final String[] REDIRECT_METHODS = new String[]{};

    @Override
    protected boolean isRedirectable(final String method) {
        for (final String m : REDIRECT_METHODS) {
            if (m.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}

