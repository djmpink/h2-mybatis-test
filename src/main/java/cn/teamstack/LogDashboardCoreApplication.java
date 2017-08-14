package cn.teamstack;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableAdminServer
@EnableWebSocket
public class LogDashboardCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogDashboardCoreApplication.class, args);
	}
}
