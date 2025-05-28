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
        String urlEncoded = request.getUrlEncoded();
        try {
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

        return services;
    }

}