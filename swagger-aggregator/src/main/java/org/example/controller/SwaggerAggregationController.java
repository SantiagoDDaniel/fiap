package org.example.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SwaggerAggregationController {

    private final DiscoveryClient discoveryClient;
    private RestTemplate restTemplate = new RestTemplate();

    public SwaggerAggregationController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/swagger-docs")
    public Map<String, Object> aggregateSwaggerDocs() {
        Map<String, Object> aggregatedDocs = new HashMap<>();
        discoveryClient.getServices().forEach(service -> {
            String url = "http://" + service + "/v3/api-docs";
            try {
                Object doc = restTemplate.getForObject(url, Object.class);
                aggregatedDocs.put(service, doc);
            } catch (Exception e) {
                aggregatedDocs.put(service, "Error retrieving documentation: " + e.getMessage());
            }
        });
        return aggregatedDocs;
    }
}
