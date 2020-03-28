package domain;

import java.io.Serializable;
import java.sql.Timestamp;


public class Session implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4171239878042550146L;

	
	private String animalDateOfBirth;
	private String animalID;
	private String animalSex;
	private String animalSpecies;
	private String animalWeight;
	private String experimenterName;
	private double microscopeEXLEDPower;
	private double microscopeOGLEDPower;
	private int samplingRate;
	private String sessionId;
	private String recordingStartTime;
	private String acquisitionSWVersion;
	
	public String getAcquisitionSWVersion() 
	{
		return acquisitionSWVersion;
	}

	public void setAcquisitionSWVersion(String acquisitionSWVersion) 
	{
		this.acquisitionSWVersion = acquisitionSWVersion;
	}


	public String getAnimalDateOfBirth() 
	{
		return animalDateOfBirth;
	}

	public void setAnimalDateOfBirth(String animalDateOfBirth) 
	{
		this.animalDateOfBirth = animalDateOfBirth;
	}

	public String getAnimalID() 
	{
		return animalID;
	}


	public void setAnimalID(String animalID) 
	{
		this.animalID = animalID;
	}


	public String getAnimalSex() 
	{
		return animalSex;
	}

	public void setAnimalSex(String animalSex) 
	{
		this.animalSex = animalSex;
	}


	public String getAnimalSpecies() 
	{
		return animalSpecies;
	}


	public void setAnimalSpecies(String animalSpecies) 
	{
		this.animalSpecies = animalSpecies;
	}


	public String getAnimalWeight() 
	{
		return animalWeight;
	}


	public void setAnimalWeight(String animalWeight) 
	{
		this.animalWeight = animalWeight;
	}


	public String getExperimenterName() 
	{
		return experimenterName;
	}


	public void setExperimenterName(String experimenterName) 
	{
		this.experimenterName = experimenterName;
	}


	public double getMicroscopeEXLEDPower() 
	{
		return microscopeEXLEDPower;
	}


	public void setMicroscopeEXLEDPower(double microscopeEXLEDPower) 
	{
		this.microscopeEXLEDPower = microscopeEXLEDPower;
	}


	public double getMicroscopeOGLEDPower() 
	{
		return microscopeOGLEDPower;
	}


	public void setMicroscopeOGLEDPower(double microscopeOGLEDPower) 
	{
		this.microscopeOGLEDPower = microscopeOGLEDPower;
	}


	public int getSamplingRate() 
	{
		return samplingRate;
	}


	public void setSamplingRate(int samplingRate) 
	{
		this.samplingRate = samplingRate;
	}


	public String getSessionId() 
	{
		return sessionId;
	}


	public void setSessionId(String sessionID) 
	{
		this.sessionId = sessionID;
	}


	public String getRecordingStartTime() 
	{
		return recordingStartTime;
	}


	public void setRecordingStartTime(String recordingStartTime) 
	{
		this.recordingStartTime = recordingStartTime;
	}


	public Session()
	{
		
	}
	
	
}


