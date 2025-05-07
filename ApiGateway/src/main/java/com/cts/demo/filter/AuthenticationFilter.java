//package com.cts.demo.filter;
//
//import java.io.ObjectInputFilter.Config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.stereotype.Component;
//
//import com.cts.demo.util.JwtUtil;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.config> {
//
//	@Autowired
//	private RouteValidator validator;
//	
//	@Autowired
//	private JwtUtil util;
//	
//	public static class config{
//		
//	}
//	
//	public AuthenticationFilter() {
//		super(config.class);
//	}
//
//	@Override
//	public GatewayFilter apply(config config) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
