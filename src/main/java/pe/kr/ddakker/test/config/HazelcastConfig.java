package pe.kr.ddakker.test.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.config.DiscoveryConfig;
import com.hazelcast.config.DiscoveryStrategyConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

import static org.springframework.util.Assert.isTrue;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableCaching
@Configuration
public class HazelcastConfig {
	
    public Config config() {
		System.out.println("1111");
		MulticastConfig multicastConfig = new MulticastConfig();
		multicastConfig.setEnabled(true);
		multicastConfig.setMulticastGroup("224.2.2.3");
		multicastConfig.setMulticastPort(54327);
		
		
		JoinConfig joinConfig = new JoinConfig();
		joinConfig.setMulticastConfig(multicastConfig);
		
		NetworkConfig networkConfig = new NetworkConfig();
		networkConfig.setPort(15701);
		networkConfig.setPublicAddress("127.0.0.1");
		networkConfig.setJoin(joinConfig);
		
    	Config config = new Config();
//		config.setClusterName("ddakker-cluster");
//		config.setInstanceName("ddakker-cluste1");
		config.addMapConfig(new MapConfig().setName("test").setTimeToLiveSeconds(20));
		config.setNetworkConfig(networkConfig);
//					.setPublicAddress("127.0.0.1")
//					.setPort(15701)
//					.setPortAutoIncrement(false)
//					.getJoin()
//						.getMulticastConfig()
//							.setEnabled(true)
//							.setMulticastGroup("224.2.2.3")
//							.setMulticastPort(54327)
//							.addTrustedInterface("127.0.0.1");
//					.setPortAutoIncrement(true)
//					.setPortCount(20);
		return config;
	}
	
    @Bean
    public CacheManager cacheManager() {
    	
    	System.out.println("222222222 1");
    	HazelcastCacheManager hazelcastCacheManager = new HazelcastCacheManager(Hazelcast.newHazelcastInstance(config()));
    	System.out.println("222222222 2");
//    	hazelcastCacheManager.setCacheOptions("defaultReadTimeout=2,cache1=10,cache2=20");
//    	System.out.println("222222222 3");
        return hazelcastCacheManager;
    }
    
    
    public static void main(String[] args) {
    	new HazelcastConfig().parseOptions("defaultReadTimeout=2,cache1=10,cache2=20");
	}
    
    private void parseOptions(String options) {
        if (null == options || options.trim().isEmpty()) {
            return;
        }
        for (String option : options.split(",")) {
            parseOption(option);
        }
    }

    private void parseOption(String option) {
        String[] keyValue = option.split("=");
//        isTrue(keyValue.length != 0, "blank key-value pair");
//        isTrue(keyValue.length <= 2, String.format("key-value pair %s with more than one equals sign", option));

        String key = keyValue[0].trim();
        String value = (keyValue.length == 1) ? null : keyValue[1].trim();
        System.out.println(key + "=" +value);

        isTrue(value != null && !value.isEmpty(), String.format("value for %s must not be null or empty", key));

		/*
		 * if ("defaultReadTimeout".equals(key)) { defaultReadTimeout =
		 * Long.parseLong(value); } else { readTimeoutMap.put(key,
		 * Long.parseLong(value)); }
		 */
    }
}
