package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.NumberUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<CustomerEntity> findActiveAll(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql= new StringBuilder("select c.* from customer c ");

        sql.append(joinQuery(customerSearchRequest));
        sql.append(basicQuery(customerSearchRequest));
        sql.append(specialQuery(customerSearchRequest));

        Query query=em.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }
    public String basicQuery(CustomerSearchRequest customerSearchRequest)  {
        StringBuilder sb=new StringBuilder(" where 1 = 1 and c.is_active = 1 ");

        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            System.out.println(fields.length);
            for(Field field: fields) {
                field.setAccessible(true);
                Object value=field.get(customerSearchRequest);
                if(field.getName().equals("staffId")) continue;
                if(value!=null&&value.toString().length()>0) {
                    if(NumberUtil.isNumber(value.toString())) {
                        sb.append(" And c."+ field.getName()+ " = " + value.toString()+ " ");
                    }
                    else {
                        sb.append(" And c."+field.getName()+ " LIKE '%"+value.toString()+"%' ");
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String joinQuery(CustomerSearchRequest customerSearchRequest){
        if(customerSearchRequest.getStaffId()!=null){
            return " join assignmentcustomer ac on ac.customerid=c.id ";
        }
        return "";
    }

    public String specialQuery(CustomerSearchRequest customerSearchRequest){
        if(customerSearchRequest.getStaffId()!=null){
            return " and ac.staffid = "+customerSearchRequest.getStaffId()+" ";
        }
        return "";
    }
    @Override
    public List<CustomerEntity> findActiveDevidedAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        StringBuilder sql= new StringBuilder("select distinct c.* from customer c");
        sql.append(joinQuery(customerSearchRequest));
        sql.append(basicQuery(customerSearchRequest));
        sql.append(specialQuery(customerSearchRequest));

        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset()).append("\n");
        Query query=em.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }



    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return findActiveAll(customerSearchRequest).size();
    }


}
