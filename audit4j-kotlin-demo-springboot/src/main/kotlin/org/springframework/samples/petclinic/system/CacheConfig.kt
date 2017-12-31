package org.springframework.samples.petclinic.system

import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Cache could be disabled in unit test.
 */
@org.springframework.context.annotation.Configuration
@EnableCaching
@Profile("production")
open class CacheConfig {
	
	@Bean
    fun cacheManagerCustomizer(): JCacheManagerCustomizer {
        return JCacheManagerCustomizer {
            it.createCache("vets", createCacheConfiguration())
        }
    }

    private fun createCacheConfiguration(): Configuration<Any, Any> =
            // Create a cache using infinite heap. A real application will want to use an
            // implementation dependent configuration that will better fit your needs
    		MutableConfiguration<Any, Any>().setStatisticsEnabled(true)

}

