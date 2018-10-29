package org.ktds.pointCollation.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointCollationRepository extends MongoRepository<PointCollationData, String> {

	public List<PointCollationData> findByCompanyId(String companyId);
}
