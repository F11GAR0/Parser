package com.lushin.parser;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.net.URLDecoder;

@RestController
@RequestMapping("/api/parser")
public class ParserController {

    @PostMapping
    public List<Service> parseServices(@RequestBody ParserRequest request) {
        List<Service> services = new ArrayList<>();
        String urlDecoded = "";
        try {
            String urlEncoded = request.getUrlEncoded();
            System.out.println("URL encoded: " + urlEncoded);
            if (urlEncoded == null || urlEncoded.isEmpty()) {
                System.out.println("URL is empty");
                return new ArrayList<>();
            }
            urlDecoded = URLDecoder.decode(urlEncoded, "UTF-8");
            System.out.println(urlDecoded);
        } catch (UnsupportedEncodingException e) {
            return new ArrayList<>();
        }

        CleaningSiteInterface cleaningSite = CleaningSiteFactory.createCleaningSite(urlDecoded);
        services = cleaningSite.parseContent();

        // services.add(new Service(1, "Уборка \"Стандарт\"", "150 руб./кв.м"));
        // services.add(new Service(1, "Генеральная уборка", "300 руб./кв.м"));
        // services.add(new Service(1, "Уборка в новостройке/уборка после ремонта, строительства", "200 руб./кв.м"));
        // services.add(new Service(1, "Мойка стекол, зеркал (с одной стороны)", "200 руб./кв.м"));
        // services.add(new Service(1, "Мойка окон (с двух сторон)", "400 руб./кв.м"));

        return services;
    }

}