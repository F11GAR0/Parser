package com.lushin.parser;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/parser")
public class ParserController {

    @PostMapping("/{url_encoded}")
    public List<Service> parseServices(@RequestBody string url_encoded) {
        List<Service> services = new ArrayList<>();

        services.add(new Service(1, "Уборка \"Стандарт\"", "150 руб./кв.м"));
        services.add(new Service(1, "Генеральная уборка", "300 руб./кв.м"));
        services.add(new Service(1, "Уборка в новостройке/уборка после ремонта, строительства", "200 руб./кв.м"));
        services.add(new Service(1, "Мойка стекол, зеркал (с одной стороны)", "200 руб./кв.м"));
        services.add(new Service(1, "Мойка окон (с двух сторон)", "400 руб./кв.м"));

        return services;
    }

}