package com.lushin.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

public class GormasterCleaningSite implements CleaningSiteInterface {
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

    @Override
    public List<Service> parseContent() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
            System.out.println("Sending request: " + url);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();
            System.out.println("Request sended: " + url);
            List<Service> services = new ArrayList<>();

            // https://regex101.com/r/hP46zd/1
            Pattern pattern = Pattern.compile("<tr[^>]*><td[^>]*>(?:<a[^>]*>){0,1}([^>]*)(?:<\\/a>){0,1}<\\/td><td>([^>]*)<\\/td><\\/tr>");
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