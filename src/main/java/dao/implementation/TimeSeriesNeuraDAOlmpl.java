package dao.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dao.TimeSeriesNeuralDAO;
import dto.Cells;
import dto.GPIOs;
import dto.Sessions;
import repository.CellsRepo;
import repository.GPIORepo;
import repository.SessionRepo;
import domain.Cell;
import domain.Container;
import domain.GPIO;
import domain.Session;


@Repository
public class TimeSeriesNeuraDAOlmpl<T> implements TimeSeriesNeuralDAO 
{
	CSVReader csvReader = null;
	InputStreamReader reader = null;
	BufferedReader bufferedReader= null;
	Cells cells = new Cells();
	
	Sessions sessions =null;
	
	Cells responseCells = null;
	
	Cell domainCell =null;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	CellsRepo cellsRepo;
	
	@Autowired
	GPIORepo gPIORepo;
	
	
	// import session files and populate session table
	@SuppressWarnings({ "hiding", "unchecked", "null" })
	@Override
	public <T> Container<T>  sessionJSONImport(MultipartFile file)
	{
		Sessions sessions = new Sessions();
		Sessions responseSessions = null;
		Session domainSession = new Session();
		Map<String, Object> errorMap = new HashMap<String, Object>();
		@SuppressWarnings("unused")
		Container<T> genericObject=null;
		String[] line;
		
		if (file.isEmpty()) 
		{
			errorMap.put("Empty file", "Please upload a file");	
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");
			
		}
		else if (!file.getOriginalFilename().toUpperCase().contains(".JSON"))
		{
			errorMap.put("Incorrect file type.", "Please enter a JSON file.");	
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");
			
		}
		
		try 
		{
			
			//read csv file
			reader = new InputStreamReader(file.getInputStream());
			csvReader = new CSVReaderBuilder(reader).build();
			line = csvReader.readNext();
			if (line[0].replace("{","").startsWith("Acquisition SW Version"))
			{
				sessions.setAcquisitionSWVersion(line[0].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[1].startsWith("Animal Date of Birth"))
			{
				sessions.setAnimalDateOfBirth(line[1].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[2].startsWith("Animal ID"))
			{
				sessions.setAnimalID(line[2].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[3].startsWith("Animal Sex"))
			{
				sessions.setAnimalSex(line[3].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[4].startsWith("Animal Species"))
			{
				sessions.setAnimalSpecies(line[4].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[5].startsWith("Animal Weight"))
			{
				sessions.setAnimalWeight(line[5].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[6].startsWith("Experimenter Name"))
			{
				System.out.println(line[6].split(":\\s+")[1].replaceAll("\"",""));
				sessions.setExperimenterName(line[6].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[7].startsWith("Microscope EX LED Power"))
			{
				sessions.setMicroscopeEXLEDPower(Double.parseDouble(line[7].split(":\\s+")[1].replaceAll("\"","")));
			}
			
			if (line[8].startsWith("Microscope OG LED Power"))
			{
				sessions.setMicroscopeOGLEDPower(Double.parseDouble(line[8].split(":\\s+")[1].replaceAll("\"","")));
			}
			
			
			if (line[9].startsWith("Sampling rate"))
			{
				sessions.setSamplingRate(Integer.parseInt(line[9].split(":\\s+")[1].replaceAll("\"","")));
			}
			
			if (line[10].startsWith("Session ID") || line[10].startsWith("Session_ID"))
			{
				sessions.setSessionId(line[10].split(":\\s+")[1].replaceAll("\"",""));
			}
			
			if (line[11].replace("}","").startsWith("Recording start time"))
			{
				
				sessions.setRecordingStartTime(line[11].split(":\\s+")[1].replace('"',' ').replace("}","").trim());
			}
			
			
			responseSessions = sessionRepo.save(sessions);
			domainSession.setAcquisitionSWVersion(responseSessions.getAcquisitionSWVersion());
			domainSession.setAnimalDateOfBirth(responseSessions.getAnimalDateOfBirth());
			domainSession.setAnimalID(responseSessions.getAnimalID());
			domainSession.setAnimalSex(responseSessions.getAnimalSex());
			domainSession.setAnimalSpecies(responseSessions.getAnimalSpecies()); 
			domainSession.setAnimalWeight(responseSessions.getAnimalWeight());
			domainSession.setExperimenterName(responseSessions.getExperimenterName());
			domainSession.setMicroscopeEXLEDPower(responseSessions.getMicroscopeEXLEDPower());
			domainSession.setMicroscopeOGLEDPower(responseSessions.getMicroscopeOGLEDPower());
			domainSession.setSamplingRate(responseSessions.getSamplingRate()); 
			domainSession.setSessionId(responseSessions.getSessionId());
			domainSession.setRecordingStartTime(responseSessions.getRecordingStartTime()); 
		
			return genericObject = (Container<T>) new Container<Session>(domainSession,"Class Object");
		
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
			errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
		} 
		finally 
		{
			System.gc();
			try 
			{
				reader.close();
				csvReader.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	
	//read session files from specify location and populate session table
	@SuppressWarnings({ "hiding", "unchecked", "unused", "resource" })
	@Override
	public <T> Container<T> sessionImportFromFileLocation(String fileLocation)
	{
				
				
		Sessions sessions = new Sessions();
		
		Sessions responseSessions = null;
		
		Session domainSession = new Session();
		
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		@SuppressWarnings("unused")
		Container<T> genericObject=null;
				
				
		File file = new File(fileLocation);
			
	
		if (!file.exists() || !file.getName().toUpperCase().contains(".JSON")) 
		{
			errorMap.put("Incorrect file type.", "Please enter a JSON file.");
		
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
					
		}
				
				
		Scanner sc = null;
				
		try 
		{
					
			sc = new Scanner(file);
			int countLine=0;
			while (sc.hasNextLine()) 
			{
				String readScanner = sc.nextLine();
						
				boolean found=false;		
							
				String[] line = readScanner.split(",");
						
				JSONObject sessionObject = new JSONObject(readScanner);
						
						
				if (sessionObject.getString("Acquisition SW Version")!=null)
				{
					sessions.setAcquisitionSWVersion(sessionObject.getString("Acquisition SW Version"));
				}
						
				if (sessionObject.getString("Animal Date of Birth")!=null)
				{
					sessions.setAnimalDateOfBirth(sessionObject.getString("Animal Date of Birth"));
				}
						
				if (sessionObject.getString("Animal ID")!=null)
				{
					sessions.setAnimalID(sessionObject.getString("Animal ID"));
				}
						
				if (sessionObject.getString("Animal Sex")!=null)
				{
					sessions.setAnimalSex(sessionObject.getString("Animal Sex"));
				}
						
				if (sessionObject.getString("Animal Species")!=null)
				{
					sessions.setAnimalSpecies(sessionObject.getString("Animal Species"));
				}
						
				if (sessionObject.getString("Animal Weight (g)")!=null)
				{
					sessions.setAnimalWeight(sessionObject.getString("Animal Weight (g)"));
				}
						
				if (sessionObject.getString("Experimenter Name")!=null)
				{			
					sessions.setExperimenterName(sessionObject.getString("Experimenter Name"));
				}
						
				if (sessionObject.getDouble("Microscope EX LED Power (mw/mm^2)")!=-1)
				{
					sessions.setMicroscopeEXLEDPower(sessionObject.getDouble("Microscope EX LED Power (mw/mm^2)"));
				}
						
				if (sessionObject.getDouble("Microscope OG LED Power (mw/mm^2)")!=-1)
				{
					sessions.setMicroscopeOGLEDPower(sessionObject.getDouble("Microscope OG LED Power (mw/mm^2)"));
				}
						
						
				if (sessionObject.getInt("Sampling rate (Hz)")!=-1)
				{
					sessions.setSamplingRate(sessionObject.getInt("Sampling rate (Hz)"));
				}
						
				if (sessionObject.getString("Session_ID")!=null)
				{
					sessions.setSessionId(sessionObject.getString("Session_ID"));
				}
						
				if (sessionObject.getString("Recording start time")!=null)
				{
							
					sessions.setRecordingStartTime(sessionObject.getString("Recording start time"));
				}
						
						
				responseSessions = sessionRepo.save(sessions);
				
				domainSession.setAcquisitionSWVersion(responseSessions.getAcquisitionSWVersion());
				
				domainSession.setAnimalDateOfBirth(responseSessions.getAnimalDateOfBirth());
				
				domainSession.setAnimalID(responseSessions.getAnimalID());
				
				domainSession.setAnimalSex(responseSessions.getAnimalSex());
				
				domainSession.setAnimalSpecies(responseSessions.getAnimalSpecies()); 
				
				domainSession.setAnimalWeight(responseSessions.getAnimalWeight());
				
				domainSession.setExperimenterName(responseSessions.getExperimenterName());
						
				domainSession.setMicroscopeEXLEDPower(responseSessions.getMicroscopeEXLEDPower());
				
				domainSession.setMicroscopeOGLEDPower(responseSessions.getMicroscopeOGLEDPower());
				
				domainSession.setSamplingRate(responseSessions.getSamplingRate()); 
				
				domainSession.setSessionId(responseSessions.getSessionId());
				
				domainSession.setRecordingStartTime(responseSessions.getRecordingStartTime()); 
					
				return genericObject = (Container<T>) new Container<Session>(domainSession,"Class Object");
					
			}
						
		}
				
		catch (Exception e1) 
		{
			e1.printStackTrace();
			errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
		} 
		finally 
		{
			System.gc();
			if (sc != null) 
			{
				sc.close();
			}
					
		}
				
			return null;
					
		}
	
		//read cell files and populate session table
		@SuppressWarnings({ "hiding", "unchecked", "unused" })
		@Override
		public <T> Container<T> gpiosCSVImportFromFileLocation(String fileLocation, String sessionId)
		{
			
			
			GPIOs gPIOs = null;
			Sessions sessions =null;
			
			GPIOs responseGPIOs = null;
			
			GPIO domainGPIO = null;
			
			ArrayList<GPIO> domainGPIOList = new ArrayList<GPIO>();
			
			Map<String, Object> errorMap = new HashMap<String, Object>();
			
			Container<T> genericObject=null;
			
			File file = new File(fileLocation);
			
			if (!(sessionId instanceof String) )
			{
				errorMap.put("Incorrect parameter value type.", "Please enter a string parameter value");
				return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			}
			
			if (!file.exists() || !file.getName().toUpperCase().contains(".CSV")) 
			{
				errorMap.put("Incorrect file type.", "Please enter a CSV file.");
				return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
				
			}
			
			
			Scanner sc = null;
			
			try 
			{
			

				sessions = sessionRepo.findBySessionId(sessionId);
				
				
				   
				sc = new Scanner(file);
				int countLine=0;
				while (sc.hasNextLine()) 
				{
					String line = sc.nextLine();
						
					gPIOs = new GPIOs();	
					gPIOs.setChannels(line);
					gPIOs.setSessions(sessions);
					gPIOs.setCellColumnId(countLine);
					responseGPIOs = gPIORepo.save(gPIOs);
					countLine++;
				}
							
			    // note that Scanner suppresses exceptions
				if (sc.ioException() != null) 
				{
					throw sc.ioException();
				}
			 
			
			return genericObject = (Container<T>) new Container<String>("File populated into destination table sucessfully","Class Object");
			
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
				errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
				return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
				
			} 
			finally 
			{
				System.gc();
				if (sc != null) 
				{
					sc.close();
				}
			}
			
				
		}
		
		//read cell files from location and populate session table
		@SuppressWarnings({ "hiding", "unchecked", "unused" })
		@Override
		public <T> Container<T> cellsCSVImportFromFileLocation(String fileLocation, String sessionId)
		{
					
					
			ArrayList<Cells> domainCellList = new ArrayList<Cells>();
					
			ArrayList<String[]> readValueList = new ArrayList<String[]>();
					
			Map<String, Object> errorMap = new HashMap<String, Object>();
					
			Container<T> genericObject=null;
							
			File file = new File(fileLocation);
					
			if (!(sessionId instanceof String) )
			{
				errorMap.put("Incorrect parameter value type.", "Please enter a string parameter value");
				
				return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			}
					
			if (!file.exists() || !file.getName().toUpperCase().contains(".CSV")) 
			{
				errorMap.put("Incorrect file type.", "Please enter a CSV file.");
				
				return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
						
			}
					
			Scanner sc = null;
					
			try 
			{
				
				sessions = sessionRepo.findBySessionId(sessionId);		
								   
				sc = new Scanner(file);
							
				int countLine=0;
							
				while (sc.hasNextLine()) 
				{
								
					String line = sc.nextLine();
								
					boolean found=false;
									
					String[] sp = line.split(",");
													
					for(int j=0; j<sp.length && !found; j++)
					{
											
						if(sp[j]!=null && sp[j]!="")
						{
							cells = new Cells(); 
										
							cells.setFrames(sp[j]);
										
							cells.setSessions(sessions);
										
							cells.setCellId(countLine);
										
							cells.setColumnId(j);
										
							responseCells = cellsRepo.save(cells);
						}
											
						else
						{
										
							found=true;
						}
					}
										
					countLine++;
				}
				// note that Scanner suppresses exceptions
							
				if (sc.ioException() != null) 
				{
					throw sc.ioException();
				}
						 
						
				return genericObject = (Container<T>) new Container<String>("File populated into destination table sucessfully","Class Object");
						
			} 
			
			catch (Exception e1) 
			{
				e1.printStackTrace();
						
				errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
						
				return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
						
			} 
			
			finally 
			{
				System.gc();		
				if (sc != null) 
				{
							
					sc.close();
				}
			}
				
						
		}
	
	//read cell files and populate session table
	@SuppressWarnings({ "hiding", "unchecked", "unused" })
	@Override
	public <T> Container<T> cellsCSVImport(MultipartFile file, String sessionId)
	{
		
		
		ArrayList<Cells> domainCellList = new ArrayList<Cells>();
		ArrayList<String[]> readValueList = new ArrayList<String[]>();
		Map<String, Object> errorMap = new HashMap<String, Object>();
		Container<T> genericObject=null;
	
		
		if (!(sessionId instanceof String) )
		{
			errorMap.put("Incorrect parameter value type.", "Please enter a string parameter value");
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
		}
		
		if (file.isEmpty() || !file.getOriginalFilename().toUpperCase().contains(".CSV")) 
		{
			errorMap.put("Incorrect file type.", "Please enter a CSV file.");
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			
		}
		
		Scanner sc = null;
		
		try 
		{
		

			sessions = sessionRepo.findBySessionId(sessionId);
		
			
					   
				sc = new Scanner(file.getInputStream(), "UTF-8");
				int countLine=0;
				while (sc.hasNextLine()) 
				{
					String line = sc.nextLine();
					
					boolean found=false;
						
					String[] sp = line.split(",");
						
					
						
					for(int j=0; j<sp.length && !found; j++)
					{
								
						if(sp[j]!=null && sp[j]!="")
						{
							cells = new Cells(); 
							
							cells.setFrames(sp[j]);
							
							cells.setSessions(sessions);
							
							cells.setCellId(countLine);
							
							cells.setColumnId(j);
							
							responseCells = cellsRepo.save(cells);
						}
								
						else
						{
							found=true;
						}
					}
							
					countLine++;
				}
					    // note that Scanner suppresses exceptions
				if (sc.ioException() != null) 
				{
					throw sc.ioException();
				}
			 
			
			return genericObject = (Container<T>) new Container<String>("File populated into destination table sucessfully","Class Object");
			
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
			errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			
		} 
		finally 
		{
			System.gc();
			if (sc != null) 
			{
				sc.close();
			}
		}
			
	}
	
	
	//import gpio files and populate gpiotable
	@SuppressWarnings({ "unchecked", "hiding", "unused" })
	@Override
	public <T> Container<T> gPIOCSVImport(MultipartFile file, String sessionId)
	{
		
		
		GPIOs gPIOs = null;
		Sessions sessions =null;
		
		GPIOs responseGPIOs = null;
		
		GPIO domainGPIO = null;
		
		ArrayList<GPIO> domainGPIOList = new ArrayList<GPIO>();
		
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		Container<T> genericObject=null;
		
			
		if (!(sessionId instanceof String) )
		{
			errorMap.put("Incorrect parameter value type.", "Please enter a string parameter value");
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
		}
			
		if (file.isEmpty() || !file.getOriginalFilename().toUpperCase().contains(".CSV")) 
		{
			errorMap.put("Incorrect file type.", "Please enter a CSV file.");
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
				
		}
			
		
		Scanner sc = null;
		
		try 
		{
		

			sessions = sessionRepo.findBySessionId(sessionId);
		
			
					   
				sc = new Scanner(file.getInputStream(), "UTF-8");
				int countLine=0;
				while (sc.hasNextLine()) 
				{
					String line = sc.nextLine();
						
					gPIOs = new GPIOs();	
					gPIOs.setChannels(line);
					gPIOs.setSessions(sessions);
					gPIOs.setCellColumnId(countLine);
					responseGPIOs = gPIORepo.save(gPIOs);
					countLine++;
				}
							
			    // note that Scanner suppresses exceptions
				if (sc.ioException() != null) 
				{
					throw sc.ioException();
				}
			 
			
			return genericObject = (Container<T>) new Container<String>("File populated into destination table sucessfully","Class Object");
			
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
			errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			
		} 
		finally 
		{
			
			if (sc != null) 
			{
				sc.close();
			}
			System.gc();
		}
			
	}
		
	
	// get session details for the first select query question
	@SuppressWarnings({ "unchecked", "hiding", "unused" })
	@Override
	public <T> Container<T> getSessionFullDetails(String experimenterName, String startDate, String endDate)
	{
		
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		Container<T> genericObject=null;
		try 
		{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SS:ZZ");
		String startTimestamp = null;
		String endTimestamp = null;
		
		if((startDate.split("T")).length>1)
		{
			
			startTimestamp = startDate;
		}
		else
		{
			startTimestamp = startDate+"T"+"00:00:00+00:00";
		}
		
		if((endDate.split("T")).length>1)
		{
			endTimestamp = endDate;
		}
		else
		{
			endTimestamp = endDate+"T"+"23:59:00+00:00";
		}
		
		List<String[]> sessionsObject = sessionRepo.findSessionFullDetails(experimenterName,startTimestamp, endTimestamp);
		return genericObject = (Container<T>) new Container<Object>(sessionsObject,"Class Object");
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
			errorMap.put("Insert Error", "Error Thrown -> "+e1.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			
		} 
		
	}
	
	
	//get the bout moment for the third select question
	@SuppressWarnings({ "unchecked", "hiding", "unused" })
	@Override
	public <T> Container<T> getBoutMoment(String sessionId)
	{
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		Container<T> genericObject=null;
		try 
		{
			String[] gPIOsList = gPIORepo.findGPIOsBySessionId(sessionId);
			String[] sessionList = cellsRepo.selectCellsRow(sessionId, 0);
			ArrayList<String[]> boutMovementPositionList = new ArrayList<String[]>();
			String[] boutMovementArray = new String[30];
			double previousValue=-1;
			double currentValue=0.0;
			int foundPosition=0;
			for (int i=0; i<gPIOsList.length;i++)
			{
				currentValue = Double.parseDouble(gPIOsList[i]);
				if(currentValue==1.0 && previousValue==0.0)
				{
					int pos =0;
					
					boutMovementArray = new String[30];
					for(int j=(i-15); j<(i+15);j++)
					{
						
						boutMovementArray[pos] = sessionList[j];
						pos++;
					}
					boutMovementPositionList.add(boutMovementArray);
				}
				
				previousValue =currentValue;
				
				
			}
			
			return genericObject = (Container<T>) new Container<ArrayList<String[]>>(boutMovementPositionList,"Class Object");
		}
		catch(Exception e)
		{
			errorMap.put("Insert Error", "Error Thrown -> "+e.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			
		}
	}
	
	// get first two rows from cells table using sessiod id
	@SuppressWarnings({ "unchecked", "hiding", "unused" })
	@Override
	public <T> Container<T> getFirstTwoCellsNeuralData(String sessionId)
	{
		Map<String, Object> errorMap = new HashMap<String, Object>();
		ArrayList<Object[]> boutMovementPositionList = new ArrayList<Object[]>();
		
		Container<T> genericObject=null;
		try 
		{
			
			String[] gPIOsList = gPIORepo.findGPIOsBySessionId(sessionId);
			for(int i=0; i<2;i++)
			{
				Object[] cellsList = cellsRepo.selectCellsRow(sessionId,i);
				boutMovementPositionList.add(cellsList);
			}
			
			
			ArrayList<String[]> cellsGPODataList = new ArrayList<String[]>();
			
			boutMovementPositionList.add(gPIOsList);
			return genericObject = (Container<T>) new Container<ArrayList<Object[]>>(boutMovementPositionList,"Class Object");
		}
		catch(Exception e)
		{
			errorMap.put("Insert Error", "Error Thrown -> "+e.getCause().getCause().getMessage());
			return genericObject = (Container<T>) new Container<Map<String, Object>>(errorMap,"Error Object");			
			
		}
		
	}
}
