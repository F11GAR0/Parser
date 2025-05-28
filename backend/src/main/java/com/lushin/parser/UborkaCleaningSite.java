package com.lushin.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.IDN;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class UborkaCleaningSite implements CleaningSiteInterface {
    private String url;
    private List<Service> services;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public List<Service> getServices() {
        return services;
    }

    @Override
    public void setServices(List<Service> services) {
        this.services = services;
    }

    public static URI createUriWithCyrillic(String url) throws MalformedURLException, URISyntaxException {
        java.net.URL rawUrl = new java.net.URL(url);

        String asciiHost = IDN.toASCII(rawUrl.getHost());

        return new URI(
            rawUrl.getProtocol(),
            null, // userInfo
            asciiHost,
            rawUrl.getPort(),
            rawUrl.getPath(),
            rawUrl.getQuery(),
            rawUrl.getRef()
        );
    }

    @Override
    public List<Service> parseContent() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder()
                .uri(UborkaCleaningSite.createUriWithCyrillic(url))
                .GET()
                .build();
            }
            catch (MalformedURLException | URISyntaxException e){
                return new ArrayList<>();
            }
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();

            List<Service> services = new ArrayList<>();
            
            // Pattern to match table rows with service info and price
            Pattern pattern = Pattern.compile("<tr[^>]*>[^>]*<td[^>]*>([^>]*)<\\/td><td[^>]*>([^>]*)<\\/td><td[^>]*>(?:[^>]*)<\\/td><td[^>]*>(?:[^>]*)<\\/td><\\/tr>");
            Matcher matcher = pattern.matcher(html);

            while (matcher.find()) {
                Service service = new Service();
                service.setServiceInfo(matcher.group(1).trim());
                service.setPrice(matcher.group(2).trim());
                services.add(service);
            }

            return services;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to parse content from " + url, e);
        }
    }
}