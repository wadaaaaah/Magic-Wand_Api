package com.accenture.magicwand.repository;

import com.accenture.magicwand.entity.MagicWand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicWandRepository extends JpaRepository<MagicWand,Long> {

    MagicWand findByName(String name);
}
