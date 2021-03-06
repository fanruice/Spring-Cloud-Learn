package com.zst.controller;

import com.zst.entity.Person;
import com.zst.feign.FirstEurekaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 不能缺少 @Configuration 这个注解
 * @author: Zhoust
 * @date: 2018/08/27 下午11:32
 * @version: V1.0
 */
@RestController
@Configuration
public class InvokerController {


    @Autowired
    private FirstEurekaProvider personService;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @GetMapping("router")
    public String router() {
        System.out.println("dsadsadsaddddddd");
        RestTemplate restTemplate = getRestTemplate();
        //通过服务名称进行调用
        String forObject = restTemplate.getForObject("http://first-eureka-provider/get/100", String.class);
        return forObject;
    }

    @GetMapping("getPerson/{id}")
    public Person getPersonInformation(@PathVariable("id")String id) {
        return personService.getPersonById(Integer.valueOf(id));
    }

}
