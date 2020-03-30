package repoTest;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import dto.GPIOs;
import dto.Sessions;
import repository.GPIORepo;
import repository.SessionRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GPIORepoTest {
		 
	@Autowired
	SessionRepo sessionRepo;
	
	
	@Autowired
	GPIORepo gPIORepo;

	@Test
	public void saveTest() 
	{
		Sessions sessions = sessionRepo.findBySessionId("RS_49");
			 
		GPIOs gPIOs = new GPIOs();
			  
			  
		gPIOs.setCellColumnId(23); 
					
			  
		gPIOs.setChannels("0.000000000000000000e+00"); 
				
			  
		gPIOs.setSessions(sessions);
				
		gPIORepo.save(gPIOs);
			  
		 assertNotNull(gPIORepo.findGPIOsBySessionId("RS_49"));
	}
		  
		  
	@Test
	public void findCellSessionIdCellIdTest() 
	{
			  
		assertNotNull(gPIORepo.findGPIOsBySessionId("RS_49"));
	}
		  
		  
}



