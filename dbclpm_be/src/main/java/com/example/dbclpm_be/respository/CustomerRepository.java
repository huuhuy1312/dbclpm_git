package com.example.dbclpm_be.respository;

import com.example.dbclpm_be.entity.Customer;
import com.example.dbclpm_be.entity.ResidentialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {



    @Query("SELECT c FROM Customer c " +
            "WHERE (:name IS NULL OR c.infoCommon.name LIKE %:name%) " +
            "AND (:baseName IS NULL OR c.base.name = :baseName) " +
            "AND (:residentialName IS NULL OR c.residentialType.name = :residentialName) " +
            "AND c.actived = false")
    List<Customer> findNonActiveCustomers(String name, String baseName, String residentialName);

    @Query("SELECT c FROM Customer c " +
            "WHERE c.actived = true " +
            "AND (:baseName IS NULL OR c.base.name = :baseName) " +
            "AND (:residentialName IS NULL OR c.residentialType.name = :residentialName) " +
            "AND (:ordinal_numbers is null or c.ordinal_numbers LIKE %:ordinal_numbers%) " +
            "AND (:name IS NULL OR c.infoCommon.name LIKE %:name%)")
    List<Customer> findCustomerByOrdinalNumbersAndNameAndBaseNameAndResidentialType(String name, String baseName, String residentialName,String ordinal_numbers);
    @Query("SELECT c From Customer c WHERE (c.infoCommon.phoneNumber = :phoneNumber)")
    List<Customer> findByPhoneNumber(String phoneNumber);
}
