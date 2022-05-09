package br.com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("admin123");
		System.out.println("My hash : ".concat(result));
	}
}
/**
 *  @EnableAutoConfiguration- Permite que o application content do spring seja automaticamente carregado baseado nos jars e nas
 *   configuracoes definidas, sempre é feita depois que os beans foram registrados no application content, isso tem a grande
 *   vantagem de diminuir a responsabildade da definição das configuracoes. Alguns casos pode definir a propriedade "exclude" para excluir
 *   algumas classes 
 *  
 *  @ComponentScan - É usada para definir para o Spring boot que ele deve scanear os pacotes e encontrar arquiv. de config
 * 
 */
