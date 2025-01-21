package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.Projection.SaleMinProjection;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;


@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
/*
	@GetMapping(value = "/summary")
	public ResponseEntity<SaleMinDTO> getSummary() {
		SaleMinDTO dto = service.getSummary();
		return ResponseEntity.ok(dto);
	}
*/
	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSellerDTO>> getReport(
			@RequestParam(name = "minDate", required = false)	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)String minDate,
			@RequestParam(name = "maxDate", required = false)	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)String maxDate,
			@RequestParam(name = "name", defaultValue = "",required = false) String name, Pageable pageable) {

		return ResponseEntity.ok( service.getReport(minDate,maxDate,name,pageable));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleMinProjection>> getSummary(
			@RequestParam(name = "minDate", required = false)	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)String minDate,
			@RequestParam(name = "maxDate", required = false)	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)String maxDate) {
		List<SaleMinProjection> list  = service.getSummary(minDate,maxDate);
		return ResponseEntity.ok( list );
	}


}
