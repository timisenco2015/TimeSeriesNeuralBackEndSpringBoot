package controllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.TimeSeriesNeuralController;
import service.TimeSeriesNeuralService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeSeriesNeuralControllerTest {
	 
	@Autowired
	private MockMvc mockMvc;
		
		@Mock
		TimeSeriesNeuralService tSNService;
		
		@InjectMocks
		private TimeSeriesNeuralController timeSeriesNeuralController;
		
		@Before
		public void setUp() throws Exception
		{
			mockMvc = MockMvcBuilders.standaloneSetup(timeSeriesNeuralController).build();
		}


	
		
		
		@Test
		public void testgetSessionFullDetails() throws Exception
		{
			JSONObject jObject = new JSONObject();
			jObject.put("experimenterName", "Roger Sperry");
			jObject.put("startDate","2020-01-24");
			jObject.put("endDate","2020-01-27");
			
			
			
			mockMvc.perform( MockMvcRequestBuilders
				      .get("/neuron/sessionFullDetails")
				      .param(asJsonString(jObject))
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
				     
		
		}
		
		
		@Test
		public void testGetBoutMoment() throws Exception
		{
			
			
			mockMvc.perform( MockMvcRequestBuilders
				      .get("/neuron/boutMomentDetails")
				      .param("projectName", "Sherwood Project")
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
				     
		
		}
		
		@Test
		public void testGetFirstTwoCellsNeuralData() throws Exception
		{
			
			
			mockMvc.perform( MockMvcRequestBuilders
				      .get("/neuron/firstTwoCellsNeuralData")
				      .param("projectName", "Sherwood Project")
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
				     
		
		}
		
		
		public static String asJsonString(final Object obj) 
		{
		    try 
		    {
		        return new ObjectMapper().writeValueAsString(obj);
		    } 
		    catch (Exception e) 
		    {
		        throw new RuntimeException(e);
		    }
		}
		

}
