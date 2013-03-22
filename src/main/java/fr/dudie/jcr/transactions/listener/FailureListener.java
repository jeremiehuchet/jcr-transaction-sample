package fr.dudie.jcr.transactions.listener;

import javax.jcr.observation.EventIterator;

public class FailureListener extends AbstractTestListener {

    public void onEventDelegate(final EventIterator events) {
        throw new RuntimeException("This listener causes failures");
    }

}
