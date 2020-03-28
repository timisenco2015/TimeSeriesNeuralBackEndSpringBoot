package service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import dao.TimeSeriesNeuralDAO;
import domain.Container;
import service.TimeSeriesNeuralService;


@Service("TimeSeriesNeuralService")
public class TimeSeriesNeuralServiceImpl implements TimeSeriesNeuralService
{
	@Autowired
	private TimeSeriesNeuralDAO timeSeriesNeuralDAO;
	
	public <T> Container<T> sessionJSONImport(MultipartFile file)
	{
		return timeSeriesNeuralDAO.sessionJSONImport(file);
		
	}
	
	public <T> Container<T> cellsCSVImport(MultipartFile file, String sessionId)
	{
		return timeSeriesNeuralDAO.cellsCSVImport(file,sessionId);
	}
	
	public <T> Container<T> gPIOCSVImport(MultipartFile file, String sessionId)
	{
		return timeSeriesNeuralDAO.gPIOCSVImport(file,sessionId);
	}
	
	public <T> Container<T> getSessionFullDetails(String experimenterName, String startDate, String endDate)
	{
		return timeSeriesNeuralDAO.getSessionFullDetails(experimenterName, startDate, endDate);
	}
	
	public <T> Container<T> getBoutMoment(String sessionId)
	{
		return timeSeriesNeuralDAO.getBoutMoment(sessionId);
	}
	
	public <T> Container<T> getFirstTwoCellsNeuralData(String sessionId)
	{
		return timeSeriesNeuralDAO.getFirstTwoCellsNeuralData(sessionId);
	}
}
