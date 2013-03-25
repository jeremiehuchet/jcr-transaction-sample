package fr.dudie.jcr.transactions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.observation.Event;
import javax.jcr.observation.ObservationManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.dudie.jcr.transactions.listener.AbstractTestListener;
import fr.dudie.jcr.transactions.listener.FailureListener;
import fr.dudie.jcr.transactions.service.RepositoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml", "classpath:/applicationContext-test.xml" })
public class JackrabbitTransactionSampleTest {

    private AbstractTestListener failureListener;

    @Autowired
    private Repository repository;

    @Autowired
    private RepositoryService repoService;

    @Before
    public void registerListeners() throws UnsupportedRepositoryOperationException, RepositoryException {
        final ObservationManager observationManager = repository.login().getWorkspace().getObservationManager();
        if (null == failureListener) {
            failureListener = new FailureListener();
            observationManager.addEventListener(failureListener, Event.NODE_ADDED, "/", true, null, null, false);
        }
    }

    @After
    public void disableListeners() {
        failureListener.setEnabled(false);
    }

    @Test
    public void failureCausesRollback() throws LoginException, RepositoryException {
        failureListener.setEnabled(true);
        repoService.createElement("failure");
        assertNull(repoService.getElement("failure"));
    }

    @Test
    public void noFailureCausesCommit() throws LoginException, RepositoryException {
        failureListener.setEnabled(false);
        repoService.createElement("failure");
        assertNotNull(repoService.getElement("failure"));
    }
}
