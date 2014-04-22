package test;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class DistributedExecutorService {
	
    public static void main(String[] args) {
        HazelcastInstance h = Hazelcast.newHazelcastInstance();
        h.getExecutorService("my-distributed-executor");
    }
}
