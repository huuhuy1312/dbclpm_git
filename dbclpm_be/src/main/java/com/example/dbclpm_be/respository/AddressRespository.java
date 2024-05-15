package com.example.dbclpm_be.respository;

import com.example.dbclpm_be.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRespository  extends JpaRepository<Address,Long> {
    @Query("SELECT a FROM Address a WHERE LOWER(REPLACE(a.detailsAddress, ' ', '')) = LOWER(:formattedDetailsAddress) AND LOWER(a.provinceOrCity) = LOWER(:provinceOrCity) AND LOWER(a.district) = LOWER(:district) AND LOWER(a.wards) = LOWER(:wards)")
    List<Address> findByAllFields(String formattedDetailsAddress, String provinceOrCity, String district, String wards);
}
