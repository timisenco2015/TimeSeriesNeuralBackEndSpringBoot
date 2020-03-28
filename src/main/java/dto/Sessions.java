package dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.UniqueConstraint;
import org.springframework.context.annotation.Bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "sessionstable",uniqueConstraints=@UniqueConstraint(columnNames={"session_id"}))
@IdClass(Sessions.SessionsId.class)
public class Sessions implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6613292184332932247L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "animal_date_of_birth", nullable = false,length=150)
	private String animalDateOfBirth;
	
	@Column(name = "animal_id" , nullable = false,length=60)
	private String animalID;
	
	
	
	@Column(name = "animal_sex", nullable = false,length=50)
	private String animalSex;
	
	@Column(name = "animal_species", nullable = false,length=50)
	private String animalSpecies;
	
	@Column(name = "animal_weight", nullable = false,length=15)
	private String animalWeight;
	
	@Column(name = "experimenter_name", nullable = false,length=30)
	private String experimenterName;
	
	@Column(name = "microscope_ex_led_power", nullable = false,length=30)
	private double microscopeEXLEDPower;
	
	@Column(name = "microscope_og_led_power", nullable = false,length=30)
	private double microscopeOGLEDPower;
	
	@Column(name = "sampling_rate", nullable = false,length=30)
	private int samplingRate;
	
	@Column(name = "session_id", nullable = false,length=30)
	private String sessionId;
		
	@Column(name = "recording_start_time", nullable = false,length=30)
	private String recordingStartTime;
	
	@Column(name = "acquisition_sw_version", nullable = false,length=30)
	private String acquisitionSWVersion;
	
	@OneToMany(mappedBy = "sessions")
	private List<Cells> cellsList = new ArrayList<Cells>();
	

	@Bean
	public String getAcquisitionSWVersion() {
		return acquisitionSWVersion;
	}


	@Bean
	public void setAcquisitionSWVersion(String acquisitionSWVersion) {
		this.acquisitionSWVersion = acquisitionSWVersion;
	}


	@Bean
	public String getAnimalDateOfBirth() {
		return animalDateOfBirth;
	}


	@Bean
	public void setAnimalDateOfBirth(String animalDateOfBirth) {
		this.animalDateOfBirth = animalDateOfBirth;
	}


	@Bean
	public String getAnimalID() {
		return animalID;
	}


	@Bean
	public void setAnimalID(String animalID) {
		this.animalID = animalID;
	}


	@Bean
	public String getAnimalSex() {
		return animalSex;
	}


	@Bean
	public void setAnimalSex(String animalSex) {
		this.animalSex = animalSex;
	}


	@Bean
	public String getAnimalSpecies() {
		return animalSpecies;
	}


	@Bean
	public void setAnimalSpecies(String animalSpecies) {
		this.animalSpecies = animalSpecies;
	}


	@Bean
	public String getAnimalWeight() {
		return animalWeight;
	}


	@Bean
	public void setAnimalWeight(String animalWeight) {
		this.animalWeight = animalWeight;
	}


	@Bean
	public String getExperimenterName() {
		return experimenterName;
	}


	@Bean
	public void setExperimenterName(String experimenterName) {
		this.experimenterName = experimenterName;
	}


	@Bean
	public double getMicroscopeEXLEDPower() {
		return microscopeEXLEDPower;
	}


	@Bean
	public void setMicroscopeEXLEDPower(double microscopeEXLEDPower) {
		this.microscopeEXLEDPower = microscopeEXLEDPower;
	}


	@Bean
	public double getMicroscopeOGLEDPower() {
		return microscopeOGLEDPower;
	}


	@Bean
	public void setMicroscopeOGLEDPower(double microscopeOGLEDPower) {
		this.microscopeOGLEDPower = microscopeOGLEDPower;
	}


	@Bean
	public int getSamplingRate() {
		return samplingRate;
	}


	@Bean
	public void setSamplingRate(int samplingRate) {
		this.samplingRate = samplingRate;
	}


	@Bean
	public String getSessionId() {
		return sessionId;
	}


	@Bean
	public void setSessionId(String sessionID) {
		this.sessionId = sessionID;
	}


	@Bean
	public String getRecordingStartTime() {
		return recordingStartTime;
	}


	@Bean
	public void setRecordingStartTime(String recordingStartTime) {
		this.recordingStartTime = recordingStartTime;
	}


	
	
	@Bean
	public List<Cells> getCellsList() {
		return cellsList;
	}

	@Bean
	public void setCellsList(List<Cells> cellsList) {
		this.cellsList = cellsList;
	}
	
	
	
	
	public Sessions()
	{
		
	}
	
	
	
	public static class SessionsId implements Serializable {
		   
		/**
		 * 
		 */
		private static final long serialVersionUID = 7902100282837613211L;
		/**
		 * 
		 */
		
		private Long id;
	}

	
	
}


