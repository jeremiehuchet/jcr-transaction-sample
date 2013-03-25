package fr.dudie.jcr.transactions;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dudie.jcr.transactions.service.RepositoryService;

@Controller
@RequestMapping("repository")
public class RepositoryController {

    private final RepositoryService repository;

    @Autowired
    public RepositoryController(final RepositoryService repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String help() {
        return "createNode?path=xx or getNode?path=xx";
    }

    @RequestMapping
    public String createNode(@RequestParam final String path) throws LoginException, RepositoryException {
        repository.createElement(path);
        return path;
    }

    @RequestMapping
    public String getNode(@RequestParam final String path) throws LoginException, RepositoryException {
        repository.getElement(path);
        return path;
    }
}
