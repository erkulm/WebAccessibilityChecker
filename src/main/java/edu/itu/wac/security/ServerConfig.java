//package edu.itu.wac.security;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class ServerConfig {
//
//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(getHttpConnector());
//        tomcat.addAdditionalTomcatConnectors(getHttpsConnector());
//        return tomcat;
//    }
//
//    private Connector getHttpConnector() {
//        Connector http = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        http.setScheme("http");
//        http.setPort(80);
//        http.setSecure(false);
//        http.setRedirectPort(8086);
//        return http;
//    }
//
//    private Connector getHttpsConnector() {
//        Connector http = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        http.setScheme("http");
//        http.setPort(443);
//        http.setSecure(false);
//        http.setRedirectPort(8086);
//        return http;
//    }
//}
