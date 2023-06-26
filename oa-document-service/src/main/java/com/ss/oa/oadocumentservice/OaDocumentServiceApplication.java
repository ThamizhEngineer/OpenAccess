package com.ss.oa.oadocumentservice;

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
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import com.netflix.discovery.shared.Application;
import com.ss.oashared.interceptor.AuthInterceptor;
//@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ss"})
@EntityScan(basePackages = {"com.ss"})
@ComponentScan(basePackages = {"com.ss"})
@EnableAsync
public class OaDocumentServiceApplication implements WebMvcConfigurer {
	private static final String[] REQUEST_METHOD_SUPPORTED = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD" };

	public static void main(String[] args) {
		SpringApplication.run(OaDocumentServiceApplication.class, args);
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
//    @Autowired
//    private EurekaClient eurekaClient;
//     
//    public void doRequest() {
//        Application application 
//          = eurekaClient.getApplication("oa-service");
//        InstanceInfo instanceInfo = application.getInstances().get(0);
//        String hostname = instanceInfo.getHostName();
//        int port = instanceInfo.getPort();
//        // ...
//    }
    
    @Bean("asyncThreadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
