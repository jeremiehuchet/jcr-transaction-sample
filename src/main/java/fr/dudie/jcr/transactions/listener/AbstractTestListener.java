package fr.dudie.jcr.transactions.listener;

import javax.jcr.observation.EventIterator;

import org.apache.jackrabbit.core.observation.SynchronousEventListener;

public abstract class AbstractTestListener implements SynchronousEventListener {

    private boolean enabled = false;

    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public final void onEvent(final EventIterator events) {
        if (enabled) {
            onEventDelegate(events);
        }
    }

    protected abstract void onEventDelegate(final EventIterator events);

}
