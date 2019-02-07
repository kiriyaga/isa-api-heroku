package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
