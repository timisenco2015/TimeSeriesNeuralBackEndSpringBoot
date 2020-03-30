package repoTest;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dto.Cells;
import dto.Sessions;
import repository.CellsRepo;
import repository.GPIORepo;
import repository.SessionRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CellRepoTest {
		 
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	CellsRepo cellsRepo;
	
	@Autowired
	GPIORepo gPIORepo;

	@Test
	public void saveTest() 
	{
		Sessions sessions = sessionRepo.findBySessionId("RS_49");

		Cells cells = new Cells();	
			 
		cells.setFrames("-8.242275492301575079e-01");  
			  
		cells.setSessions(sessions);
		
		cells.setCellId(0); 
			  
		cells.setColumnId(0);  
			  
		sessionRepo.save(sessions);
			  	 
		assertNotNull(cellsRepo.selectCellsRow("RS_49",0));
		  
	}
		  
		  
	@Test
	public void findCellSessionIdCellIdTest() 
	{
		 assertNotNull(cellsRepo.selectCellsRow("RS_49",0));
	}
		  
		  
}



