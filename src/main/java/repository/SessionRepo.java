package repository;

import java.sql.Timestamp;
import java.util.List;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import dto.Sessions;

public interface SessionRepo extends JpaRepository<Sessions, Long> 
{
	
	
	@Query(value ="select id, animal_date_of_birth,animal_id,animal_sex,animal_species,animal_weight,"
			+ "experimenter_name,microscope_ex_led_power,microscope_og_led_power,sampling_rate,session_id,recording_start_time,acquisition_sw_version from sessionstable "
			+ "where session_id=?1", nativeQuery = true)
	Sessions findBySessionId(String sessionId);
	
	
	@Query(value ="select  s.animal_date_of_birth,s.animal_id,s.animal_sex,s.animal_species,s.animal_weight, s.experimenter_name,s.microscope_ex_led_power,s.microscope_og_led_power,s.sampling_rate,s.session_id,s.recording_start_time,s.acquisition_sw_version, (countTable.count)*.15 from sessionstable s \r\n" + 
			"LEFT JOIN (select session_id, count(distinct c.cell_id) as count from cellstable c where c.experimenter_name=?1 group by session_id) as countTable on s.session_id = countTable.session_id\r\n" + 
			"where s.experimenter_name=?1 and recording_start_time  BETWEEN ?2 AND ?3", nativeQuery = true)
	List<String[]> findSessionFullDetails(String experimenterName,String startTimestamp, String endTimestamp);
	
	
	
	
	
	
}
