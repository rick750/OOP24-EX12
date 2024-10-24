package p12.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{
    final private Map<Q,Set<T>> queuesMap = new HashMap<>();

    @Override
    public Set<Q> availableQueues() {
        return this.queuesMap.keySet();
    }

    @Override
    public void openNewQueue(Q queue) {
        try {
            this.queuesMap.put(queue, new HashSet<T>());
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        return this.queuesMap.get(queue).isEmpty(); 
    }

    @Override
    public void enqueue(T elem, Q queue) {

    }

    @Override
    public T dequeue(Q queue) {
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
    public List<T> dequeueAllFromQueue(Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeueAllFromQueue'");
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeQueueAndReallocate'");
    }

}
