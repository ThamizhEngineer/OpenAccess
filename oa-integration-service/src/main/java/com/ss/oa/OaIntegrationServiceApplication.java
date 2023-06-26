package com.ss.oa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ss.oashared.interceptor.AuthInterceptor;

//@Import({DataSourceConfiguration.class})
@SpringBootApplication
@EnableScheduling 
@EnableJpaRepositories(basePackages = {"com.ss"})
@EntityScan(basePackages = {"com.ss"})
@ComponentScan(basePackages = {"com.ss"})
//@EnableDiscoveryClient
public class OaIntegrationServiceApplication {

	private static final String[] REQUEST_METHOD_SUPPORTED = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD" };

	public static void main(String[] args) {
		SpringApplication.run(OaIntegrationServiceApplication.class, args);
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods(REQUEST_METHOD_SUPPORTED);
            }
        };
    }
    @Configuration
	public class AppConfig extends WebMvcConfigurerAdapter {
		
		@Autowired
		AuthInterceptor authInterceptor;
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(authInterceptor);
		}
}
//    @RestController
//    class ServiceInstanceRestController {
//
//        @Autowired
//        private DiscoveryClient discoveryClient;
//
//        @RequestMapping("/service-instances/{applicationName}")
//        public List<ServiceInstance> serviceInstancesByApplicationName(
//                @PathVariable String applicationName) {
//            return this.discoveryClient.getInstances(applicationName);
//        }
//    }
}
