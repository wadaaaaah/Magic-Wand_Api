package com.accenture.magicwand.repository;

import com.accenture.magicwand.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicWandRepository extends JpaRepository<Demo,Long> {

    Demo findByName(String name);
}
