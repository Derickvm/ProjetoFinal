package com.agenda_service_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = {"com.agenda_service_back.categoria","com.agenda_service_back.agendamento","com.agenda_service_back.endereco","com.agenda_service_back.prestador","com.agenda_service_back.servico","com.agenda_service_back.telefone","com.agenda_service_back.usuarios"})
@SpringBootApplication
public class AgendaServiceBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaServiceBackApplication.class, args);
	}

}
