package org.example.service;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SwaggerAggregatorService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public SwaggerAggregatorService(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> aggregateSwaggerDocs() {
        Map<String, Object> aggregatedDocs = new HashMap<>();
        for (String service : discoveryClient.getServices()) {
            String url = "http://" + service + "/v3/api-docs";
            try {
                Object doc = restTemplate.getForObject(url, Object.class);
                aggregatedDocs.put(service, doc);
            } catch (Exception e) {
                aggregatedDocs.put(service, "Error retrieving Swagger documentation: " + e.getMessage());
            }
        }
        return aggregatedDocs;
    }
}
