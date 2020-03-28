package ca.daoTest;

import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import dao.implementation.TimeSeriesNeuraDAOlmpl;
import domain.Container;
import repository.CellsRepo;
import repository.GPIORepo;
import repository.SessionRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSeriesNeuraDAOlmplTest  
{
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	CellsRepo cellsRepo;
	
	@Autowired
	GPIORepo gPIORepo;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	TimeSeriesNeuraDAOlmpl timeSeriesNeuraDAOlmpl;
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public <T> void getSessionFullDetailsTest() 
	{
		@SuppressWarnings("unused")
		Container<T> genericObject=null; 
	
		String startTimestamp = "2020-01-24T00:00:00+00:00";
		
		String endTimestamp = "2020-01-27T24:59:00+00:00";
		
		String experimenterName= "Roger Sperry";
		
		
		try 
		{
			
			 Container<T> timeSeriesNeuraDAOContainer = timeSeriesNeuraDAOlmpl.getSessionFullDetails(experimenterName,startTimestamp, endTimestamp);
			 String typeOfObject =  timeSeriesNeuraDAOContainer.getObjectType();
			 
			 if(typeOfObject.equalsIgnoreCase("Class Object"))
			 {
				 
					List<String> timeSeriesNeuraDAODataList=	(List<String>)timeSeriesNeuraDAOContainer.getObject();
				
					assertNotNull(timeSeriesNeuraDAODataList);
					
			 }
			 
			 else if (typeOfObject.equalsIgnoreCase("Error Object"))
			 {
				 Map<String, Object> errorMap = (Map<String, Object>)timeSeriesNeuraDAOContainer.getObject();
				 assertNotNull(errorMap.get("Insert Error"));
				 
			 }
				
		}
		catch (DataAccessException dataAccessException) 
		{
			 
		 }
						
			
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public <T> void getBoutMomentTest() 
	{
		@SuppressWarnings("unused")
		Container<T> genericObject=null; 
	
		
		
		try 
		{
			
			 Container<T> timeSeriesNeuraDAOContainer = timeSeriesNeuraDAOlmpl.getBoutMoment("RS_51");
			 String typeOfObject =  timeSeriesNeuraDAOContainer.getObjectType();
			 
			 if(typeOfObject.equalsIgnoreCase("Class Object"))
			 {
				 
					List<String> timeSeriesNeuraDAODataList=	(List<String>)timeSeriesNeuraDAOContainer.getObject();
				
					assertNotNull(timeSeriesNeuraDAODataList);
					
			 }
			 
			 else if (typeOfObject.equalsIgnoreCase("Error Object"))
			 {
				 Map<String, Object> errorMap = (Map<String, Object>)timeSeriesNeuraDAOContainer.getObject();
				 assertNotNull(errorMap.get("Insert Error"));
				 
			 }
				
		}
		catch (DataAccessException dataAccessException) 
		{
			 
		 }
						
			
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public <T> void getFirstTwoCellsNeuralDataTest() 
	{
		@SuppressWarnings("unused")
		Container<T> genericObject=null; 
	
		
		
		try 
		{
			
			 Container<T> timeSeriesNeuraDAOContainer = timeSeriesNeuraDAOlmpl.getFirstTwoCellsNeuralData("RS_51");
			 String typeOfObject =  timeSeriesNeuraDAOContainer.getObjectType();
			 
			 if(typeOfObject.equalsIgnoreCase("Class Object"))
			 {
				 
					List<String> timeSeriesNeuraDAODataList=	(List<String>)timeSeriesNeuraDAOContainer.getObject();
				
					assertNotNull(timeSeriesNeuraDAODataList);
					
			 }
			 
			 else if (typeOfObject.equalsIgnoreCase("Error Object"))
			 {
				 Map<String, Object> errorMap = (Map<String, Object>)timeSeriesNeuraDAOContainer.getObject();
				 assertNotNull(errorMap.get("Insert Error"));
				 
			 }
				
		}
		catch (DataAccessException dataAccessException) 
		{
			 
		 }
						
			
	}
	
		
	
}
