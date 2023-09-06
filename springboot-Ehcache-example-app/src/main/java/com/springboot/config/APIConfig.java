/**
 * 
 */
package com.springboot.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.handler.APIFilter;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * @author junaid.khan
 *
 */
/*extends CachingConfigurerSupport  deprecated*/ 
@Configuration
@EnableCaching
public class APIConfig /*extends CachingConfigurerSupport*/ implements CachingConfigurer{

	@Bean
	public APIFilter aPIFilter() {
		return 	new APIFilter();
	}

	
	//ten second cache
	@Bean 
	public net.sf.ehcache.CacheManager ehCacheManager(){
		 	CacheConfiguration tenSec= new CacheConfiguration();
		 	tenSec.setName("ten-second-cache");
		 	tenSec.setMemoryStoreEvictionPolicy("LRU");
		 	tenSec.setMaxEntriesLocalHeap(1000);
		 	tenSec.setTimeToLiveSeconds(10);
		 	
			CacheConfiguration twentySec= new CacheConfiguration();
			twentySec.setName("twenty-second-cache");
			twentySec.setMemoryStoreEvictionPolicy("LRU");
			twentySec.setMaxEntriesLocalHeap(1000);
			twentySec.setTimeToLiveSeconds(20);
		 	
			
			net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
			config.addCache(tenSec);
			config.addCache(twentySec);
			
			
			return net.sf.ehcache.CacheManager.newInstance(config);
	}
	
	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
	
	
	
}
