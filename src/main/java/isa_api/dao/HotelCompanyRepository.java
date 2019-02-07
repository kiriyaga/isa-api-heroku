package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.hotel.HotelCompany;

public interface HotelCompanyRepository extends JpaRepository<HotelCompany, Long> {

}
