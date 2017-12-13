package com.ex.gauge_ex;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

@RestController
public class Controller {
    
    @Autowired
    private MetricRegistry metricRegistry;
    
    public Integer count = 0;
    
    @PostConstruct
    public void init() {
        
        metricRegistry.register(MetricRegistry.name("Test Gauge", "cache-evictions"), new Gauge<Integer>() {
            
            @Override
            public Integer getValue() {
                return count;
            }
        });
        
    }
    

    @GetMapping("/greet")
    String greet(@RequestParam(defaultValue = "World") String name) {

        count = count + 1 ;

        return "Hello: " + name + " " + LocalDateTime.now();
    }
}
