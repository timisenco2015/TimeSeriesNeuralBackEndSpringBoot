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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cellstable",uniqueConstraints=@UniqueConstraint(columnNames= {"session_id","cell_id","column_id"}))
@IdClass(Cells.CellsId.class)
public class Cells implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8733558810263586856L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	
	@Column(name = "cell_id", nullable = false)
	long cellId;
	
	@Column(name = "column_id", nullable = false)
	long columnId;
	

	@Column(name = "frames", nullable = false)
	String frames;
	
	
	
	@Bean
	public Long getId() {
		return id;
	}

	@Bean
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Bean
	public String getFrames() {
		return frames;
	}
	
	
	@Bean
	public void setFrames(String frames) {
		this.frames = frames;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "session_id",referencedColumnName="session_id", nullable = false,insertable = true, updatable = true),
		@JoinColumn(name = "experimenter_name",referencedColumnName="experimenter_name", nullable = false,insertable = true, updatable = true)
	})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Sessions sessions;
	
	@Bean
	public Sessions getSessions() {
		return sessions;
	}

	@Bean
	public void setSessions(Sessions sessions) {
		this.sessions = sessions;
	}
	
	
	
	@Bean
	public long getCellId() {
		return cellId;
	}

	@Bean
	public void setCellId(long cellId) {
		this.cellId = cellId;
	}

	@Bean
	public long getColumnId() {
		return columnId;
	}

	@Bean
	public void setColumnId(long columnId) {
		this.columnId = columnId;
	}

	


	public static class CellsId implements Serializable {
		   
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3830614031559707873L;
		private Long id;
		
	}

	
	
}
