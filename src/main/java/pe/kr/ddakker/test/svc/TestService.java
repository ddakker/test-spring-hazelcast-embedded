package pe.kr.ddakker.test.svc;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.hazelcast.map.IMap;

import pe.kr.ddakker.test.web.TestController;

@Service
public class TestService {
	private final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private CacheManager cacheManager;
	
	public String test() {
		 logger.info("cacheManager: " + cacheManager);
		 Cache cache = cacheManager.getCache("test");
		  
		 logger.info("cache1 a: " + cache.get("a"), String.class); 
		 cache.put("a", "a1");
		 logger.info("cache2 a: " + cache.get("a", String.class));
		 

		return "index";
	}
	
	public String put() {
		 logger.info("cacheManager: " + cacheManager);
		 Cache cache = cacheManager.getCache("test");
		 cache.put("a", "a1");
		 
		 IMap<String, Object> nCache = (IMap) cache.getNativeCache();
		 nCache.put("b", "b1", 10, TimeUnit.SECONDS);
		 nCache.put("c", "c1", 10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);
		  

		return "index";
	}
	
	public String get() {
		 logger.info("cacheManager: " + cacheManager);
		 Cache cache = cacheManager.getCache("test");
		 
		 
		  
		 logger.info("cache1 a: " + cache.get("a", String.class));
		 logger.info("cache1 b: " + cache.get("b", String.class));
		 logger.info("cache1 c: " + cache.get("c", String.class));

		return "index";
	}
}
