package fr.dudie.jcr.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-jcr.xml")
public class JackrabbitJcaRepositoryCreationTest {

    @Autowired
    private Repository repository;

    @Test
    public void canGetWorkspaceName() throws Exception {
        assertEquals("default", repository.login().getWorkspace().getName());
    }

    @Test
    public void canCreateNode() throws Exception {
        final Session s = repository.login(new SimpleCredentials("admin", "admin".toCharArray())).getWorkspace().getSession();
        assertNotNull(s.getRootNode().addNode("some"));
        assertNotNull(s.getRootNode().addNode("some/node"));
        s.save();
    }
}