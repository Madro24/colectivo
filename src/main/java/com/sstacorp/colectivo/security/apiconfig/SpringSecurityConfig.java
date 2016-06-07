package com.sstacorp.colectivo.security.apiconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
* Expose the Spring Security Configuration
* 
* @author malalanayake
* 
*/
@Configuration
@ImportResource({ "classpath:/WEB-INF/spring/security/spring-security.xml" })
@ComponentScan("com.sstacorp.colectivo")
public class SpringSecurityConfig {

   public SpringSecurityConfig() {
       super();
   }

}