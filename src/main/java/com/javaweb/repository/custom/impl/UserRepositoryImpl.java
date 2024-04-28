package com.javaweb.repository.custom.impl;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserEntity> findByRole(String roleCode) {
		//JPQL
		String sql = "FROM UserEntity";
		Query query = entityManager.createNativeQuery(sql, UserEntity.class);
		return query.getResultList();
	}

	@Override
	public List<UserEntity> getAllUsers(Pageable pageable) {

		StringBuilder sql = new StringBuilder(buildQueryFilter())
				.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
				.append(" OFFSET ").append(pageable.getOffset());
		System.out.println("Final query: " + sql.toString());

		Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
		return query.getResultList();
	}

	@Override
	public int countTotalItem() {
		String sql = buildQueryFilter();
		Query query = entityManager.createNativeQuery(sql.toString());
		return query.getResultList().size();
	}

	@Override
	public List<UserEntity> getAllBuildingAssignedStaff(Long id) {
		StringBuilder sb =new StringBuilder("SELECT distinct u.* FROM user u");
		sb.append(" join assignmentbuilding ab on u.id=ab.staffid ");
		sb.append(" where ab.buildingid = "+id.toString());
		Query query=entityManager.createNativeQuery(sb.toString(),UserEntity.class);
		return query.getResultList();
	}

	@Override
	public List<UserEntity> getAllCustomerAssignedStaff(Long id) {
		StringBuilder sb =new StringBuilder("SELECT distinct u.* FROM user u");
		sb.append(" join assignmentcustomer ac on u.id=ac.staffid ");
		sb.append(" where ac.customerid = "+id.toString());
		Query query=entityManager.createNativeQuery(sb.toString(),UserEntity.class);
		return query.getResultList();
	}

	private String buildQueryFilter() {
		String sql = "SELECT distinct u.* FROM user u WHERE u.status = 1 ";
		return sql;
	}
}
