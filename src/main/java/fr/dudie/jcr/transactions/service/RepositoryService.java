package fr.dudie.jcr.transactions.service;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepositoryService {

    @Autowired
    private Repository repository;

    @Transactional
    public void createElement(final String name) throws LoginException, RepositoryException {
        final Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        session.getRootNode().addNode(name);
        session.save();
    }

    @Transactional
    public void createElementAndThrowRuntimeException(final String name) throws LoginException, RepositoryException {
        final Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        session.getRootNode().addNode(name);
        session.save();
        throw new RuntimeException(name);
    }

    public Node getElement(String name) throws LoginException, RepositoryException {
        final Session session = repository.login();
        try {
            return session.getRootNode().getNode(name);
        } catch (PathNotFoundException pnf) {
            return null;
        }
    }

    public void deleteElement(final String name) throws LoginException, RepositoryException {
        final Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        try {
            session.getRootNode().getNode(name).remove();
            session.save();
        } catch (PathNotFoundException pnf) {
        }
    }
}
