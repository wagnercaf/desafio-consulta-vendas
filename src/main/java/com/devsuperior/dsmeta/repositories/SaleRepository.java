package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.Projection.SaleMinProjection;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true,value  = """
            SELECT tb_seller.name,SUM(tb_sales.amount) as TOTAL FROM tb_seller
            INNER JOIN tb_sales ON tb_seller.ID  = tb_sales.SELLER_ID
            WHERE tb_sales.date BETWEEN :minDate AND :maxDate GROUP BY tb_seller.name
            """ )
    List<SaleMinProjection> searchSummary(
            @Param("minDate")LocalDate minDate,
            @Param("maxDate")LocalDate maxDate
        );

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(obj.id, obj.amount, obj.date, obj.seller.name) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleSellerDTO> searchReport(
            @Param("minDate")LocalDate minDate,
            @Param("maxDate")LocalDate maxDate,
            @Param("name")String name,
            Pageable pageable
    );

}
