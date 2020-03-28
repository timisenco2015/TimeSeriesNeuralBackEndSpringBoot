package dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "gpiostable",uniqueConstraints=@UniqueConstraint(columnNames= {"session_id","cell_column_id"}))
@IdClass(GPIOs.GPIOId.class)
public class GPIOs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "channels", nullable = false)
	String channels;
	
	@Column(name = "cell_column_id", nullable = false)
	long cellColumnId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	
	@Bean
	public Long getId() 
	{
		return id;
	}

	@Bean
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	@Bean
	public long getCellColumnId() {
		return cellColumnId;
	}

	@Bean
	public void setCellColumnId(long cellColumnId) {
		this.cellColumnId = cellColumnId;
	}
	
	@Bean
	public String getChannels() 
	{
		return channels;
	}
	
	
	@Bean
	public void setChannels(String channels) 
	{
		this.channels = channels;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "session_id",referencedColumnName="session_id", nullable = false,insertable = true, updatable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Sessions sessions;
	
	@Bean
	public Sessions getSessions() {
		return sessions;
	}

	@Bean
	public void setSessions(Sessions sessions) 
	{
		this.sessions = sessions;
	}


	public static class GPIOId implements Serializable 
	{
		   
		/**
		 * 
		 */
		private static final long serialVersionUID = 6776865310922786272L;
		/**
		 * 
		 */
		private Long id;
		
	}

	
	
}
