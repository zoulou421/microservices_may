package com.formationkilo.customer_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {

    @Value("${global.params.p1}")
    private String a;

    @Value("${global.params.p2}")
    private String b;
    private final CustomerConfigParams customerConfigParams;

    public ConfigTestRestController(CustomerConfigParams customerConfigParams) {
        this.customerConfigParams = customerConfigParams;
    }

    // Add @Autowired here so Spring knows this is the primary entry point
    @Autowired
    public ConfigTestRestController(
            @Value("${global.params.p1}") String a,
            @Value("${global.params.p2}") String b,
            CustomerConfigParams customerConfigParams) {
        this.a = a;
        this.b = b;
        this.customerConfigParams = customerConfigParams;
    }

    @GetMapping("/testConfig1")
    public Map<String, String> configTest() {
        return Map.of("p1", a, "p2", b);
    }

    @GetMapping("/testConfig2")
    public CustomerConfigParams configTest2() {
        return customerConfigParams;
    }

}
