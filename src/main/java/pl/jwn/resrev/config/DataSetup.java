package pl.jwn.resrev.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.dao.UserDao;
import pl.jwn.resrev.domain.model.User;

import java.util.concurrent.atomic.AtomicBoolean;


@Component
public class DataSetup {
    private static final Logger logger = LoggerFactory.getLogger(DataSetup.class);
    private AtomicBoolean alreadyRun = new AtomicBoolean(false);
    private AtomicBoolean setAdminAccount = new AtomicBoolean(false);

    private final UserDao userDao;

    public DataSetup(UserDao userDao) {
        this.userDao = userDao;
    }

    @EventListener
    @Transactional
    public void initData(ContextRefreshedEvent event) {
        if (!setAdminAccount.getAndSet(true)) {
            User admin = new User("admin", "admin@localhost.com","admin");
            admin.setAccessLevel(9);
            userDao.add(admin);
        }
    }

    @EventListener
    @Transactional
    public void testData(ContextRefreshedEvent event) {
        if (!alreadyRun.getAndSet(true)) {
            logger.debug("~-~".repeat(20));
            logger.debug("Fresh start");
            logger.debug("~-~".repeat(20));
            logger.debug("Putting test data to sql");
            userDao.add(new User("jwn", "jwn@costam.pl","abc"));
            userDao.add(new User("kww", "kww@costam.pl","def"));
            userDao.add(new User("kvt", "kvt@costam.pl","ghi"));
            logger.debug("3 users added");
            logger.debug("~-~".repeat(20));

        }
    }
}