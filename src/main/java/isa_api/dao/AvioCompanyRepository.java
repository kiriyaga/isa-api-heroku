package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.flight.AvioCompany;

public interface AvioCompanyRepository extends JpaRepository<AvioCompany, Long> {

}
