package domain;

import java.io.Serializable;


public class GPIO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 909190489418025650L;

	private String channels;
	
	private long cellColumnId;
	
	private String sessionId;
	
	
	


	public String getSessionId() 
	{
		return sessionId;
	}


	public void setSessionId(String sessionId) 
	{
		this.sessionId = sessionId;
	}


	
	public String getChannels() 
	{
		return channels;
	}
	
	
	public void setChannels(String channels) 
	{
		this.channels = channels;
	}
	
	public long getCellColumnId() 
	{
		return cellColumnId;
	}


	public void setCellColumnId(long cellColumnId) 
	{
		this.cellColumnId = cellColumnId;
	}

	
	
	
}
