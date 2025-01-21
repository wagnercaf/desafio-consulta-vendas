package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import java.time.LocalDate;

public class SaleSellerDTO {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String sellerName;

    public SaleSellerDTO(Long id, Double amount, LocalDate date , String sellerName) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.sellerName = sellerName;
    }

    public SaleSellerDTO(Sale entity) {
        id = entity.getId();
        amount = entity.getAmount();
        date = entity.getDate();
        sellerName = entity.getSeller().getName();
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSellerName() {
        return sellerName;
    }
}
