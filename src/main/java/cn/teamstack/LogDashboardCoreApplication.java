package cn.teamstack;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableAdminServer
@EnableWebSocket
@MapperScan("cn.teamstack.repository.*")
public class LogDashboardCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogDashboardCoreApplication.class, args);
	}
}
