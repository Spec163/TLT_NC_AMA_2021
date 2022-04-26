package nc.unc.ama.logger.controller;

import java.util.stream.Stream;
import nc.unc.ama.complaint_handling_service.dto.LogEntryDTO;
import nc.unc.ama.complaint_handling_service.dto.LogsREST;
import nc.unc.ama.logger.entity.LogEntry;
import nc.unc.ama.logger.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/logs")
public class LogsController implements LogsREST {

    private final LogsService logsService;

    @Autowired
    public LogsController(final LogsService service) {
        this.logsService = service;
    }


    @GetMapping("/")
    @Override
    public Stream<LogEntryDTO> listLastLogs() {
        return this.logsService.findLastLogs()
            .map(src -> new LogEntryDTO(src.getCreated(), src.getService(), src.getMessage()));
    }

    @PutMapping("/")
    @Override
    public ResponseEntity<LogEntryDTO> addLog(final LogEntryDTO input) {
        final LogEntry entry = new LogEntry();
        entry.setCreated(input.getCreated());
        entry.setService(input.getService());
        entry.setMessage(input.getMessage());
        final LogEntry created = this.logsService.addLog(entry);
        return new ResponseEntity<>(
            new LogEntryDTO(created.getCreated(), created.getService(), created.getMessage()),
            HttpStatus.CREATED
        );
    }
}