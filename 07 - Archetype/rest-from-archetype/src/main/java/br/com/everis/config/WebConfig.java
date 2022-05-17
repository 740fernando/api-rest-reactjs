package br.com.everis.config;


import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.everis.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

//	Implementação Content Negotiation via Extension
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.favorParameter(false)
//			.ignoreAcceptHeader(false)
//			.defaultContentType(MediaType.APPLICATION_JSON)
//			.mediaType("json",MediaType.APPLICATION_JSON)
//			.mediaType("xml",MediaType.APPLICATION_XML);			

//  Content Negociation via QUERY Parameter
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer
//			.favorParameter(true)
//			.parameterName("mediaType")
//			.ignoreAcceptHeader(true)
//			.useRegisteredExtensionsOnly(false)
//			.defaultContentType(MediaType.APPLICATION_JSON)
//			.mediaType("json",MediaType.APPLICATION_JSON)
//			.mediaType("xml",MediaType.APPLICATION_XML);			
//	}
	
	/**
	 * Habilita o Cors globalmente. Todos os recursos disponíveis para qlq origin.
	 * IMPORTANTE : Funcionamento no get, post, put, delete. No patch e/ou optional não vai funcionar, a nao ser que implemente o metodo AllowedMethods.
	 * JUSTIFICATIVA:  Na declaracao do padrao de acesso do CrossOrigin via browser, quando habilitamos CORS numa
	 * aplicacao spring boot, nao eh permitido acesso a recursos via metodo optional ou patch
	 * @param registry
	 */
	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS","HEAD","TRACE","CONNECT");
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json",MediaType.APPLICATION_JSON)
		.mediaType("xml",MediaType.APPLICATION_XML)
		.mediaType("x-yaml",MEDIA_TYPE_YML);
		
	}
}
