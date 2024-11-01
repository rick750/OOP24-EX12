package p12.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{
    final private Map<Q, LinkedList<T>> queues = new HashMap<>();

    @Override
    public Set<Q> availableQueues() {
        return this.queues.keySet();
    }

    @Override
    public void openNewQueue(final Q queue) {
        controlQType(queue);
        if (availableQueues().contains(queue)) {
            throw new IllegalArgumentException("Wrong argument");
        }
        this.queues.put(queue, new LinkedList<>());
    }

    @Override
    public boolean isQueueEmpty(final Q queue) {
        controlQType(queue);
        checkQueueAvailability(queue);
        return getQueue(queue).isEmpty(); 
    }

    @Override
    public void enqueue(final T elem, final Q queue) {
        controlQType(queue);
        controlTType(elem);
        checkQueueAvailability(queue);
        getQueue(queue).addLast(elem);
    }

    @Override
    public T dequeue(final Q queue) {
        controlQType(queue);
        checkQueueAvailability(queue);
        T first = null;
        if (!(isQueueEmpty(queue))) {
            first = getQueue(queue).getFirst();
            getQueue(queue).removeFirst();
        }
        return first;
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        final Map<Q, T> dequeuedMap = new HashMap<>();
        for (final var q : this.queues.keySet()) {
            dequeuedMap.put(q, isQueueEmpty(q) ? null : getQueue(q).removeFirst());        
        }
        return dequeuedMap;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        final Set<T> enqueuedSet = new HashSet<>();
        for (Q key : this.queues.keySet()) {
            enqueuedSet.addAll(getQueue(key));
        }
        return enqueuedSet;
    }

    @Override
    public List<T> dequeueAllFromQueue(final Q queue) {
        controlQType(queue);
        checkQueueAvailability(queue);
        final List<T> enqueuedList = new LinkedList<>();
        final Iterator<T> queueIter = getQueue(queue).listIterator();
        while (queueIter.hasNext()) {
            enqueuedList.addLast(queueIter.next());
        }
        getQueue(queue).clear();
        return enqueuedList;
    }

    @Override
    public void closeQueueAndReallocate(final Q queue) {
        controlQType(queue);
        checkQueueAvailability(queue);
        if (availableQueues().size() > 1) {            
            final Iterator<Q> iter = availableQueues().iterator();
            Q selectedQueue = iter.next();
            if (selectedQueue == queue) {
                selectedQueue = iter.next();
            }
            final Iterator<T> queueIter = getQueue(queue).listIterator();
            while (queueIter.hasNext()) {
                enqueue(queueIter.next(), selectedQueue);
            }        
            availableQueues().remove(queue);    
        } else {
            throw new IllegalStateException("No queues available");
        }
    }

    // Controls if the parameter is of type Q and throw an exception in case it isn't
    private void controlQType(final Q q) {
        if (!(q instanceof Q)) {
            throw new IllegalArgumentException("Wrong Argument");
        }
    }

    // Controls if the parameter is of type T and throw an exception in case it isn't
    private void controlTType(final T e) {
        if (!(e instanceof T)) {
            throw new IllegalArgumentException("Wrong Argument");
        }
    }

    // Return the LinkedList<T> (value) of the key 'queue' in this.queues
    private LinkedList<T> getQueue(final Q queue) {
        controlQType(queue);
        return this.queues.get(queue);
    }

    // Controls if the queue is available and throw an exception in case it isn't
    private void checkQueueAvailability(final Q queue) {
        if (!(availableQueues().contains(queue))) {
            throw new IllegalArgumentException("Wrong argument");
        }
    }
}
