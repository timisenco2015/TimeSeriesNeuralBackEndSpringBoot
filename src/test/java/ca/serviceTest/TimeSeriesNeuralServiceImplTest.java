package ca.serviceTest;


import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import domain.Container;
import service.TimeSeriesNeuralService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSeriesNeuralServiceImplTest<T>
{
	
	 @Autowired
	 TimeSeriesNeuralService timeSeriesNeuralService;
	

	@Test
	public void getSessionFullDetailsTest() throws JSONException 
	 {
		String startTimestamp = "2020-01-24T00:00:00+00:00";
		
		String endTimestamp = "2020-01-27T24:59:00+00:00";
		
		String experimenterName= "Roger Sperry";
		
		
		Container<T> timeSeriesNeuralServiceContainer = timeSeriesNeuralService.getSessionFullDetails(experimenterName, startTimestamp, endTimestamp);
					
		String typeOfObject = timeSeriesNeuralServiceContainer.getObjectType();
			
		if(typeOfObject.equalsIgnoreCase("Class Object"))
		{
			@SuppressWarnings("unchecked")
			List<String> timeSeriesNeuraDAODataList=	(List<String>)timeSeriesNeuralServiceContainer.getObject();
			
			assertNotNull(timeSeriesNeuraDAODataList);
		}
			
			
		else if (typeOfObject.equalsIgnoreCase("Error Object"))
		 {
			 @SuppressWarnings("unchecked")
			Map<String, Object> errorMap = (Map<String, Object>)timeSeriesNeuralServiceContainer.getObject();
			 assertNotNull(errorMap.get("Insert Error"));
			 
		 }
	}

	@Test
	public void getBoutMomentTest() throws JSONException 
	 {
		Container<T> timeSeriesNeuralServiceContainer = timeSeriesNeuralService.getBoutMoment("RS_51");
					
		String typeOfObject = timeSeriesNeuralServiceContainer.getObjectType();
			
		if(typeOfObject.equalsIgnoreCase("Class Object"))
		{
			@SuppressWarnings("unchecked")
			List<String> timeSeriesNeuraDAODataList=	(List<String>)timeSeriesNeuralServiceContainer.getObject();
			
			assertNotNull(timeSeriesNeuraDAODataList);
		}
			
			
		else if (typeOfObject.equalsIgnoreCase("Error Object"))
		 {
			 @SuppressWarnings("unchecked")
			Map<String, Object> errorMap = (Map<String, Object>)timeSeriesNeuralServiceContainer.getObject();
			 assertNotNull(errorMap.get("Insert Error"));
			 
		 }
	}

	@Test
	public void getFirstTwoCellsNeuralDataTest() throws JSONException 
	 {
		Container<T> timeSeriesNeuralServiceContainer = timeSeriesNeuralService.getFirstTwoCellsNeuralData("RS_51");
					
		String typeOfObject = timeSeriesNeuralServiceContainer.getObjectType();
			
		if(typeOfObject.equalsIgnoreCase("Class Object"))
		{
			@SuppressWarnings("unchecked")
			List<String> timeSeriesNeuraDAODataList=	(List<String>)timeSeriesNeuralServiceContainer.getObject();
			
			assertNotNull(timeSeriesNeuraDAODataList);
		}
			
			
		else if (typeOfObject.equalsIgnoreCase("Error Object"))
		 {
			 @SuppressWarnings("unchecked")
			Map<String, Object> errorMap = (Map<String, Object>)timeSeriesNeuralServiceContainer.getObject();
			 assertNotNull(errorMap.get("Insert Error"));
			 
		 }
	}
	
}
