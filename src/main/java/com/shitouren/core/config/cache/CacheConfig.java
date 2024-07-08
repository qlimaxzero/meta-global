package com.shitouren.core.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Cache配置類，用于缓存数据
 *
 * @author snoopy
 */
@Configuration
@EnableCaching
//@Profile("prodmultds")
public class CacheConfig {

  public static final int DEFAULT_MAXSIZE = 50000;
  public static final int DEFAULT_TTL     = 10;

  /**
   * 定義cache名稱、超時時長（秒）、最大容量
   * 每个cache缺省：10秒超时、最多缓存50000条数据，需要修改可以在构造方法的参数中指定。
   */
  public enum Caches {
      getMineInfo(180), // 3分钟缓存
      classification(180*10),
      message(180*100),
      show(5),
      showColl(60),
      showinfo(60),
      collDetails(60*10),
      myorder(5),
      userid(60),
      Ranking(60*30),
      getBanners(60),
      getShareImage(60),
      getValue(60 * 10),
      getHomePageDetail(30), //拥堵分析--拥堵指数年月日 3小时
      getYuanyuzhouList(60 * 60 * 3), //区域拥堵分析3，1小时
    selectCrowdIndexWithTime(60 * 60 * 3), //拥堵指数分析，1小时
    selectDayAndNightSpeedChange(60 * 60), //特征分析---早高峰晚高峰速度对比，1小时
    selectRoadTimeBehaviour(60 * 60 * 1), //特征分析---时间特征，1小时
    selectRoadSpaceBehaviour(60 * 60 * 1), //特征分析---空间特征，1小时
    selectCrowdIndexBehaviour(60 * 60 * 1), //特征分析---拥堵指数特征，1小时
    selectCityJamIndex(180), //实时---城市拥堵指数，1小时
    selectRollingNews(180), //实时---滚动播报，1小时
    selectNowDataByType(180), //实时---滚动播报，1小时
    ;

    Caches(int ttl) {
      this.ttl = ttl;
      this.name = this.name();
    }
    Caches(String name, int ttl) {
      this.name = name;
      this.ttl  = ttl;
    }

    Caches(int ttl, int maxSize) {
      this.ttl     = ttl;
      this.maxSize = maxSize;
    }

    private int    maxSize = DEFAULT_MAXSIZE;    //最大數量
    private int    ttl;        //过期时间（秒）
    private String name;

    public int getMaxSize() {
      return maxSize;
    }

    public int getTtl() {
      return ttl;
    }

    public String getName() {
      return name;
    }
  }

  /**
   * 创建基于Caffeine的Cache Manager
   *
   * @return
   */
  @Bean
  @Primary
  public CacheManager caffeineCacheManager() {
    SimpleCacheManager  cacheManager = new SimpleCacheManager();
    List<CaffeineCache> caches       = Lists.newArrayListWithExpectedSize(8);
    for (Caches c : Caches.values()) {
      caches.add(new CaffeineCache(c.name(),
                                   Caffeine.newBuilder()
                                           .recordStats()
                                           .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                                           .maximumSize(c.getMaxSize())
                                           .build())
                );
    }
    cacheManager.setCaches(caches);
    return cacheManager;
  }

}
