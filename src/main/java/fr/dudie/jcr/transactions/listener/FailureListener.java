package fr.dudie.jcr.transactions.listener;

import javax.jcr.observation.EventIterator;

import org.springframework.transaction.annotation.Transactional;

public class FailureListener extends AbstractTestListener {

    @Transactional
    public void onEventDelegate(final EventIterator events) {
        throw new RuntimeException("This listener causes failures");
    }

}
