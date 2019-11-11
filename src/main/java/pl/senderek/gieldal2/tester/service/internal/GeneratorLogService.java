package pl.senderek.gieldal2.tester.service.internal;

import pl.senderek.gieldal2.tester.model.GeneratorLog;

import java.util.List;

public interface GeneratorLogService {
    void save(GeneratorLog log);
    void saveAll(List<GeneratorLog> logList);
    List<GeneratorLog> findAll();
}
