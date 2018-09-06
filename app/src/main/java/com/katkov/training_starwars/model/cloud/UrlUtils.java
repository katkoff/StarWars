package com.katkov.training_starwars.model.cloud;

import android.net.Uri;

public final class UrlUtils {

    private static final String KEY_PAGE = "page";

    public static int getNextPageId(String nextPageUrl) {
        Uri uri = Uri.parse(nextPageUrl);
        String nextPageString = uri.getQueryParameter(KEY_PAGE);
        return Integer.valueOf(nextPageString);
    }
}
