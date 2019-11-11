package pl.senderek.gieldal2.tester.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.senderek.gieldal2.tester.model.GeneratorLog;

import java.util.List;

@Repository
public interface GeneratorLogRepository extends CrudRepository<GeneratorLog, Long> {
    List<GeneratorLog> findAll();
}