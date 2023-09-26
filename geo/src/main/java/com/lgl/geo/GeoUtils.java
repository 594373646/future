package com.lgl.geo;

import com.lgl.geo.common.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lgl
 * @Date 2023/9/20 1:56
 */
@Configurable
public class GeoUtils {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 司机上线
     * @param key
     * @param location
     */
    public void add(String key,Location location){
        GeoOperations<String, Object> opsForGeo = redisTemplate.opsForGeo();
        opsForGeo.geoAdd(key,new Point(location.getLatitude(), location.getLongitude()),location.getMember());
    }

    /**
     * 批量上线
     * @param key
     * @param locations
     */
    public void add(String key,List<Location> locations){
        redisTemplate.executePipelined( (RedisCallback<Object>) connection-> {
            for (Location location : locations) {
                connection.geoCommands().geoAdd(key.getBytes(),  new RedisGeoCommands.GeoLocation<>(location.getMember().getBytes(),new Point(location.getLatitude(), location.getLongitude())));
            }
            return null;
        });
    }

    /**
     * 司机下线
     * @param key
     * @param location
     */
    public void delete(String key,Location location){
        GeoOperations<String, Object> opsForGeo = redisTemplate.opsForGeo();
        opsForGeo.remove(key,location.getMember());
    }

    /**
     * 获取区域内司机
     *
     * @param key
     * @param longitude 经度
     * @param latitude  维度
     * @param radius    半径
     * @return
     */
    public List<Object> getLocations(String key, double longitude, double latitude, double radius){
        GeoOperations<String, Object> opsForGeo = redisTemplate.opsForGeo();
        // 半径
        Distance distance = new Distance(radius);
        // 范围
        Circle circle = new Circle(new Point(longitude,latitude),distance);
        // 结果设定
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeCoordinates()
                .sortAscending();
        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults = opsForGeo.radius(key, circle, args);
        // 结果
        List<Object> list = new ArrayList<>();
        Objects.requireNonNull(geoResults).forEach(item -> list.add(item.getContent()));
        return list;
    }

}
