package fr.dudie.jcr.transactions;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.dudie.jcr.transactions.service.RepositoryService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/applicationContext.xml", "classpath:/applicationContext-test.xml" })
public class RepositoryServiceTest {

    @Autowired
    private RepositoryService repoService;

    @Test
    public void canInvokeCreateElement() throws LoginException, RepositoryException {
        repoService.createElement("failure");
    }

    @Test
    public void canInvokeGetElement() throws LoginException, RepositoryException {
        repoService.getElement("failure");
    }
}
