package com.rjdeleon.tourista.Common;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base class for observable entities in the application
 * @param <LISTENER_CLASS> the class of the listeners
 */
public class BaseObservable<LISTENER_CLASS> {

    // Thread-safe set of listeners
    private Set<LISTENER_CLASS> _listeners = Collections.newSetFromMap(
            new ConcurrentHashMap<LISTENER_CLASS, Boolean>(1)
    );

    public final void registerListener(LISTENER_CLASS listener) {
        _listeners.add(listener);
    }

    public final void unregisterListener(LISTENER_CLASS listener) {
        _listeners.remove(listener);
    }

    /**
     * Get a reference to the unmodifiable set containing all the registered listeners.
     */
    protected final Set<LISTENER_CLASS> getListeners() {
        return Collections.unmodifiableSet(_listeners);
    }

}
