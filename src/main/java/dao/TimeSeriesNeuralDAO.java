package dao;

import org.springframework.web.multipart.MultipartFile;

import domain.Container;

public interface TimeSeriesNeuralDAO 
{
	public <T> Container<T> sessionJSONImport(MultipartFile file);
	public <T> Container<T> cellsCSVImport(MultipartFile file, String sessionId);
	public <T> Container<T> gPIOCSVImport(MultipartFile file, String sessionId);
	public <T> Container<T> getSessionFullDetails(String experimenterName, String startDate, String endDate);
	public <T> Container<T> getBoutMoment(String sessionId);
	public <T> Container<T> getFirstTwoCellsNeuralData(String sessionId);

}
