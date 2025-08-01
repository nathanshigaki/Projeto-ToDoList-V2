package br.com.nathanshigaki.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Defalt Server URL")})
@SpringBootApplication
public class V2Application {

	public static void main(String[] args) {
		SpringApplication.run(V2Application.class, args);
	}
}
