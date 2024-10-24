package p12.exercise;

import java.util.HashMap;
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
        this.queues.put(queue, new LinkedList<>());
    }

    @Override
    public boolean isQueueEmpty(final Q queue) {
        controlQType(queue);
        return this.queues.get(queue).isEmpty(); 
    }

    @Override
    public void enqueue(final T elem, final Q queue) {
        controlQType(queue);
        this.queues.get(queue).addLast(elem);
    }

    @Override
    public T dequeue(final Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeue'");
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeueOneFromAllQueues'");
    }

    @Override
    public Set<T> allEnqueuedElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allEnqueuedElements'");
    }

    @Override
    public List<T> dequeueAllFromQueue(final Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeueAllFromQueue'");
    }

    @Override
    public void closeQueueAndReallocate(final Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeQueueAndReallocate'");
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

}
