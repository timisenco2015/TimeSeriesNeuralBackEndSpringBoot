package domain;

import java.io.Serializable;



public class Cell implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3416115584632543054L;

	private String frames;
	private String sessionId;
	private long cellId;
	private long columnId;
	
	

	

	
	public long getCellId() {
		return cellId;
	}


	public void setCellId(long cellId) {
		this.cellId = cellId;
	}


	public long getColumId() {
		return columnId;
	}


	public void setColumnId(long columnId) {
		this.columnId = columnId;
	}


	

	
	
	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	


	
	public String getFrames() 
	{
		return frames;
	}
	
	
	
	public void setFrames(String frames) 
	{
		this.frames = frames;
	}
	
	


	

	
	
}
