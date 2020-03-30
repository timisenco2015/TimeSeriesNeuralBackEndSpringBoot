package repoTest;


import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import dto.Sessions;
import repository.CellsRepo;
import repository.GPIORepo;
import repository.SessionRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionRepoTest 
{
		 
	@Autowired
	SessionRepo sessionRepo;
	

	@Test
	public void saveTest() 
	{
		Sessions sessions = new Sessions();
				
		sessions.setAcquisitionSWVersion("12-01-2019"); 

		sessions.setAnimalDateOfBirth("12-01-2019");

		sessions.setAnimalID("m_001"); 

		sessions.setAnimalSex("m");

		sessions.setAnimalSpecies("C57-BL6");

		sessions.setAnimalWeight("20.5");

		sessions.setExperimenterName("Roger Sperry"); 
				
		sessions.setMicroscopeEXLEDPower(0.40);

		sessions.setMicroscopeOGLEDPower(0.20); 

		sessions.setSamplingRate(30); 

		sessions.setSessionId("RS_49"); 

		sessions.setRecordingStartTime("2020-01-24T13:30:10+00:00"); 

		sessions.setAcquisitionSWVersion("1.3.0");

		sessionRepo.save(sessions);
			  
		assertNotNull(sessionRepo.findBySessionId("RS_49"));
	}
		  
		  
	@Test
	public void findSessionDetailsByIdTest() 
	{
			  
		assertNotNull(sessionRepo.findBySessionId("RS_49"));
	}
		  
	@Test
	public void findSessionDetailsByIdStartDateEndDateTest() 
	{
		assertNotNull(sessionRepo.findSessionFullDetails("RS_49","2020-01-25","2020-01-27"));
	}
		  
		  
	}



