package com.ay.dao;

import com.ay.model.AyRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AyRoleDao {
    AyRole findById(String id);
}
