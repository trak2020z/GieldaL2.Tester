package pl.senderek.gieldal2.tester.service.internal.impl;

import org.springframework.stereotype.Service;
import pl.senderek.gieldal2.tester.model.GeneratorLog;
import pl.senderek.gieldal2.tester.repository.GeneratorLogRepository;
import pl.senderek.gieldal2.tester.service.internal.GeneratorLogService;

import java.util.List;

@Service
public class GeneratorLogServiceImpl implements GeneratorLogService {
    private final GeneratorLogRepository logRepository;

    public GeneratorLogServiceImpl(GeneratorLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void save(GeneratorLog log) {
        logRepository.save(log);
    }
    public void saveAll(List<GeneratorLog> logList) {
        logRepository.saveAll(logList);
    }

    public List<GeneratorLog> findAll() {
        return logRepository.findAll();
    }
}
