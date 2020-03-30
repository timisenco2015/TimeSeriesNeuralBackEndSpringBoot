package controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import domain.Container;
import service.TimeSeriesNeuralService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/neuron")
public class TimeSeriesNeuralController 
{
	
	@Autowired
	TimeSeriesNeuralService tSNService;
	
	// imports session files
	@PostMapping("/importSessionJSON")
	@ResponseBody
	public <T> Container<T> importSessionJSON(@RequestParam("file") MultipartFile file) throws IOException, Exception 
	{
		return tSNService.sessionJSONImport(file);
		
	}
	
	
	//import cells files
	@PostMapping("/importCellsCSV")
	@ResponseBody
	public  <T> Container<T> importCellsCSV(@RequestParam("file") MultipartFile file,@RequestParam("sessionId")String sessionId) throws IOException, Exception 
	{
		return tSNService.cellsCSVImport(file,sessionId);
		
	}
	
	//import gpios files
	@PostMapping("/importGPIOCSV")
	@ResponseBody
	public  <T> Container<T> importGPIOCSV(@RequestParam("file") MultipartFile file,@RequestParam("sessionId")String sessionId) throws IOException, Exception 
	{
		return tSNService.gPIOCSVImport(file,sessionId);
		
	}
	
	
	
	//get session details by experimental name, start date and end date
	@GetMapping("/sessionFullDetails")
	@ResponseBody
	public <T> Container<T> getSessionFullDetails(@RequestParam("experimenterName")String experimenterName,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate)
	{
		return tSNService.getSessionFullDetails(experimenterName, startDate, endDate);
	}
	
	
	// get bout moment (This is the third select question - bonus question)
	@GetMapping("/boutMomentDetails")
	@ResponseBody
	public <T> Container<T> getBoutMoment(@RequestParam("sessionId")String sessionId)
	{
		return tSNService.getBoutMoment(sessionId);
	}
	
	
	//get first two rows from cells table using session id
	@GetMapping("/firstTwoCellsNeuralData")
	@ResponseBody
	public <T> Container<T> getFirstTwoCellsNeuralData(@RequestParam("sessionId")String sessionId)
	{
		return tSNService.getFirstTwoCellsNeuralData(sessionId);
	}
	
}
