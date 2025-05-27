package com.lushin.parser;

import java.util.ArrayList;
import java.util.List;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

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

    private static String createRemoteBrowserAndGetWsEndpoint() {
		var client = HttpClient.newHttpClient();

		var request = HttpRequest.newBuilder(
				URI.create("ws://playwright:3000/browsers"))
			.POST(noBody())
			.build();

		try {
			return client.send(request, ofString()).body();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

    @Override
    public List<Service> parseContent() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().connectOverCDP("ws://playwright:3000");
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            
            page.navigate(url);
            page.waitForSelector("#DataTables_Table_0 tbody tr");

            List<Service> services = new ArrayList<>();
            List<ElementHandle> rows = page.querySelectorAll("#DataTables_Table_0 tbody tr");

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