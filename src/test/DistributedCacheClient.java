package test;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class DistributedCacheClient {

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Integer,String> cache = client.getMap("cache");
        
        cache.put(1, "Joe");
        cache.put(2, "Ali");
        cache.put(3, "Avi");
        
        System.out.println("key 1=" + cache.get(1));
        System.out.println("key 2=" + cache.get(2));
        System.out.println("key 3=" + cache.get(3));
        client.shutdown();
	}

}
