package tam.example.SpringDataPro;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@Configuration
@EnableCaching
public class CacheConfiguration {


	/**
	 * Default value of Time To Live of entity entries in Ehcache 2nd level cache, in seconds: {@value}
	 */
	private static final long EHCACHE_DEFAULT_ENTITY_TTL = 120L;
	
	/**
	 * Default value of Time To Live of HTTP responses in Ehcache cache, in seconds: {@value}
	 */
	private static final long EHCACHE_DEFAULT_HTTP_RESPONSES_TTL = 60L;
	
	/**
	 * Name of cache where HTTP connection parameters are kept
	 */
	public static final String HTTP_CNX_PARAMS_CACHE_NAME = "HttpConnectionParams";
	
	public static final String HTTP_CNX_ROLES_FOR_USERS_CACHE_NAME = "HttpConnectionRoles";

	@PersistenceContext
	private EntityManager entityManager;



	/**
	 * Ehcache's cache manager
	 */
	private CacheManager cacheManager;


	@Bean
	public org.springframework.cache.CacheManager cacheManager() {
		cacheManager = net.sf.ehcache.CacheManager.create();
		// See config/application-prod.yml
//		String maxBytesLocalHeap = env.getProperty("cache.ehcache.maxBytesLocalHeap", String.class, "16M");
//		cacheManager.getConfiguration().setMaxBytesLocalHeap("16M");
		
		Long ttl = 60l;

		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		for (EntityType<?> entity : entities) {

			String name = entity.getName();
			if (name == null || entity.getJavaType() != null) {
				name = entity.getJavaType().getName();
			}
			Assert.notNull(name, "entity cannot exist without an identifier");

			Cache cache = cacheManager.getCache(name);
			if (cache != null) {
				cache.getCacheConfiguration().setTimeToLiveSeconds(ttl);
//				Ehcache decoratedCache = InstrumentedEhcache.instrument(metricRegistry, cache);
//				cacheManager.replaceCacheWithDecoratedCache(cache, decoratedCache);
				 cacheManager.addCache(cache);
			}
		}
		
		EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;
	}
}