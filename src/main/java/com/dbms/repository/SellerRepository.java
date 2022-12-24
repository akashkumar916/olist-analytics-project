package com.dbms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dbms.entity.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, String> {

	List<Seller> findAll();
}
