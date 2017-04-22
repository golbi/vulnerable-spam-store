package net.bixbit.vulnerablespam;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class VulnerableSpamStoreApplication implements ServletContextInitializer
{

	public static void main(String[] args)
	{
		SpringApplication.run(VulnerableSpamStoreApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException 
	{
		Set<SessionTrackingMode> set = new HashSet<>();
		set.add(SessionTrackingMode.COOKIE);
		set.add(SessionTrackingMode.URL);
		servletContext.setSessionTrackingModes(set);
	}
}
