package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dto.GPIOs;



public interface GPIORepo extends JpaRepository<GPIOs, Long> 
{
	@Query(value ="select cast(channels as NUMERIC(4,2)) from  gpiostable where session_id=?1", nativeQuery = true)
	String[] findGPIOsBySessionId(String sessionId);
	
}
