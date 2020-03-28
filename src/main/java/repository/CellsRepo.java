package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dto.Cells;


public interface CellsRepo extends JpaRepository<Cells, Long> 
{
	
	@Query(value ="select cast(frames as NUMERIC(4,2)) from cellstable where session_id=?1 and cell_id=?2 ", nativeQuery = true)
	String[] selectCellsRow(String sessionId, int cellId);
	
	
}
