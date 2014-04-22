package test;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class DistributedCache {

	public static void main(String[] args) {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        instance.getMap("cache");
	}

}
