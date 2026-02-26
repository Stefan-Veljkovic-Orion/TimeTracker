package app.timetrack_repository;

import app.timetracker_entity_implemantion.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IActivityRepository extends JpaRepository<Activity,Integer> {
    boolean existsByProjectId(int id);

    @Query("""
        SELECT a
        FROM Activity a
        WHERE a.employee.id = :employeeId
          AND (:from IS NULL OR a.timeOfActivity >= :from)
          AND (:to   IS NULL OR a.timeOfActivity <= :to)
        ORDER BY a.timeOfActivity DESC
    """)
        List<Activity> findByEmployeeId(
                @Param("employeeId") Integer employeeId,
                @Param("from") LocalDateTime from,
                @Param("to") LocalDateTime to
        );

    @Query("""
        SELECT a
        FROM Activity a
        JOIN Employee e ON (a.employee.id = e.id)
        JOIN Project  p ON (a.project.id = p.id)
        where a.timeOfActivity >= :cutoffTime
        ORDER BY a.timeOfActivity DESC
    """)
    List<Activity> findAllInLastFiveSeconds(@Param("cutoffTime") LocalDateTime cutoffTime);

    boolean existsByEmployee_Id(int id);
}
