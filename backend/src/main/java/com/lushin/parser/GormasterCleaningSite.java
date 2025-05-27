package com.lushin.parser;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

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
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().connectOverCDP("ws://playwright:3000");
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            
            page.navigate(url);
            page.waitForSelector("#price div:nth-child(2) div div:nth-child(1) table tbody tr");

            List<Service> services = new ArrayList<>();
            List<ElementHandle> rows = page.querySelectorAll("#price div:nth-child(2) div div:nth-child(1) table tbody tr");

            for (ElementHandle row : rows) {
                Service service = new Service();
                service.setServiceInfo(row.querySelector("td:nth-child(1)").textContent());
                service.setPrice(row.querySelector("td:nth-child(2)").textContent());
                services.add(service);
            }

            context.close();
            browser.close();
            return services;
        }
    }
} 