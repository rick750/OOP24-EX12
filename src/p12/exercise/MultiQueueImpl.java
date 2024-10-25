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
        notAvailableException(queue);
        return getQueue(queue).isEmpty(); 
    }

    @Override
    public void enqueue(final T elem, final Q queue) {
        controlQType(queue);
        controlTType(elem);
        notAvailableException(queue);
        getQueue(queue).addLast(elem);
    }

    @Override
    public T dequeue(final Q queue) {
        controlQType(queue);
        notAvailableException(queue);
        T first = null;
        if (!(isQueueEmpty(queue))) {
            first = getQueue(queue).getFirst();
            getQueue(queue).removeFirst();
        }
        return first;
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        Map<Q, T> dequeuedMap = new HashMap<>();
        for (var q : this.queues.keySet()) {
            if (!(isQueueEmpty(q))) {
                dequeuedMap.put(q, getQueue(q).getFirst());
                getQueue(q).removeFirst();
            }
        }
        return dequeuedMap;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        Set<T> enqueuedSet = new HashSet<>();
        Iterator<Q> iter = this.queues.keySet().iterator();
        while (iter.hasNext()) {
            enqueuedSet.addAll(getQueue(iter.next()));
        }
        return enqueuedSet;
    }

    @Override
    public List<T> dequeueAllFromQueue(final Q queue) {
        controlQType(queue);
        notAvailableException(queue);
        List<T> enqueuedList = new LinkedList<>();
        for (var elem : getQueue(queue)) {
            enqueuedList.addLast(elem);
        }
        getQueue(queue).clear();
        return enqueuedList;
    }

    @Override
    public void closeQueueAndReallocate(final Q queue) {
        controlQType(queue);
        notAvailableException(queue);
        if (!(availableQueues().isEmpty())) {
            Iterator<Q> iter = availableQueues().iterator();
            for (var elem : dequeueAllFromQueue(queue)) {
                enqueue(elem, iter.next());
            }            
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
    private void notAvailableException(final Q queue) {
        if (!(availableQueues().contains(queue))) {
            throw new IllegalArgumentException("Wrong argument");
        }
    }
}
