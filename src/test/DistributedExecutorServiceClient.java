package test;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

public class DistributedExecutorServiceClient {

	public static void main(String[] args) throws InterruptedException {
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IExecutorService ex = client.getExecutorService("my-distributed-executor");
        for(int i=0;i<10;i++){
        	ex.submit(new MessagePrinter("message number " + i));
        }
        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);
        client.shutdown();
        System.out.println("finishing");
	}
	
	static class MessagePrinter implements Runnable, Serializable {
		private static final long serialVersionUID = 1L;
		final String message;
 
        MessagePrinter(String message) {
            this.message = message;
        }
 
        @Override
        public void run() {
            System.out.println(message);
        }
    }

}
