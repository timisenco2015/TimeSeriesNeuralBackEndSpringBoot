package inscopixCodeChallengrAPIs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@EnableAutoConfiguration

@Configuration


@ComponentScan(basePackages=
	{
			"inscopixCodeChallengrAPIs","controller","dao","dao.implementation","domain","dto","dto.implementation","repo","service","service.implementation"
	})

@EntityScan(basePackageClasses = 
	{
	
		dto.Cells.class,
		dto.Sessions.class,
		dto.GPIOs.class
	})

@EnableJpaRepositories(basePackageClasses = 
	{
		repository.SessionRepo.class,
		repository.CellsRepo.class,
		repository.GPIORepo.class
	})


public class Application 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(Application.class, args);
	}

}
