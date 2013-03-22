package fr.dudie.jcr.transactions;

import static org.junit.Assert.assertEquals;

import javax.jcr.Repository;

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
    public void test() throws Exception {
        assertEquals("default", repository.login().getWorkspace().getName());
    }
}