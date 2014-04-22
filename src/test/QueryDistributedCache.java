package test;

import java.io.Serializable;
import java.util.Collection;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;

public class QueryDistributedCache {

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Integer,Person> cache = client.getMap("cache");
        cache.put(1, new Person("Joe",10));
        cache.put(2, new Person("Ali",15));
        cache.put(3, new Person("Avi",20));
        
        Collection<Person> persons = cache.values(new SqlPredicate("age < 20"));
        for(Person person:persons){
        	System.out.println(person);
        }
        client.shutdown();
	}
	
	static class Person implements Serializable{
		String name;
		int age;
		Person(String name, int age){
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		@Override
		public String toString(){
			return "name="+name+", age="+age;
		}
	}

}
