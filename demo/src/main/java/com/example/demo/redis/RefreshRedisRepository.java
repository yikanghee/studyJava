package com.example.demo.redis;

import org.springframework.data.repository.CrudRepository;

public interface RefreshRedisRepository extends CrudRepository<RefreshRedisToken, String> {
}
