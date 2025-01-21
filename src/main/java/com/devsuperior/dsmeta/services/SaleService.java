package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.Projection.SaleMinProjection;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
/*
	public SaleMinDTO getSummary() {
		Optional<Sale> result = repository.searchAll();
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
*/
	public Page<SaleSellerDTO> getReport(String minDate, String maxDate, String name, Pageable pageable){

		LocalDate maxdate = (maxDate == null || maxDate.isEmpty())? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
				: LocalDate.parse(maxDate);

		LocalDate mindate = (minDate == null || minDate.isEmpty())? maxdate.minusYears(1L): LocalDate.parse(minDate);

		if (name == null || name.isEmpty()){
			name = "";
		}
		//Page<Sale> result = repository.searchReport(mindate,maxdate,name,pageable);
		//return  result.map(x -> new SaleMinDTO(x));
		return repository.searchReport(mindate,maxdate,name,pageable);

	}

	public List<SaleMinProjection> getSummary(String minDate, String maxDate){

		LocalDate maxdate = (maxDate == null || maxDate.isEmpty())? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
				: LocalDate.parse(maxDate);

		LocalDate mindate = (minDate == null || minDate.isEmpty())? maxdate.minusYears(1L): LocalDate.parse(minDate);

		return repository.searchSummary(mindate, maxdate);

	}
}
