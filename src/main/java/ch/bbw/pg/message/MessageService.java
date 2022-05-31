package ch.bbw.pg.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Service
public class MessageService {
    private final MessageRepository repository;

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Iterable<Message> getAll() {
        logger.info("DB Transaction: select");
        return repository.findAll();
    }

    public void add(Message message) {
        logger.info("DB Transaction: insert");
        repository.save(message);
    }

    public void update(Message message) {
        logger.info("DB Transaction: update");
        repository.save(message);
    }

    public void deleteById(Long id) {
        logger.info("DB Transaction: delete");
        repository.deleteById(id);
    }
}
