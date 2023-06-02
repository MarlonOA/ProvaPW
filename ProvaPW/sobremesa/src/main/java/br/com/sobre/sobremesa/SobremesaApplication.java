package br.com.sobre.sobremesa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.sobre.sobremesa.model.Usuario;
import br.com.sobre.sobremesa.repository.SobremesaRepository;
import br.com.sobre.sobremesa.repository.UsuarioRepository;

@SpringBootApplication
public class SobremesaApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SobremesaApplication.class, args);
    }

    @Bean
	CommandLineRunner commandLineRunner(SobremesaRepository sobremesaRepository, UsuarioRepository usuarioRepository, PasswordEncoder encoder){
        return args -> {
            List<Usuario> users = Stream.of(
                    new Usuario(1l, "Marlon", "Marlon", encoder.encode("111"), true),
					new Usuario(2l, "Tiago", "Tiago", encoder.encode("222"), true),
                    new Usuario(3l, "Taniro", "Taniro", encoder.encode("333"), true)
            ).collect(Collectors.toList());
            usuarioRepository.saveAll(users);
        };
    }

	// @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
    }
}