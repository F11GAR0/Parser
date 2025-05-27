package com.lushin.parser;

public class CleaningSiteFactory {
    public static CleaningSiteInterface createCleaningSite(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        String lowerUrl = url.toLowerCase();

        if (lowerUrl.contains("у-борка")) {
            UborkaCleaningSite site = new UborkaCleaningSite();
            site.setUrl(url);
            return site;
        } else if (lowerUrl.contains("gor-master")) {
            GormasterCleaningSite site = new GormasterCleaningSite();
            site.setUrl(url);
            return site;
        } else {
            throw new IllegalArgumentException("Unsupported cleaning site URL: " + url);
        }
    }
} 