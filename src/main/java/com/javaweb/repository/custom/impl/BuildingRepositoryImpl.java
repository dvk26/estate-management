package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.NumberUtil;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Pageable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    EntityManager em;
    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sb= new StringBuilder("Select distinct b.* from building b ");

        //join
        sb.append(join(buildingSearchRequest));
        //basic query
        sb.append(basicQuery(buildingSearchRequest));
        //special query
        sb.append(specialQuery(buildingSearchRequest));
        Query query=em.createNativeQuery(sb.toString(),BuildingEntity.class);
        return query.getResultList();

    }

    @Override
    public List<BuildingEntity> findDevidedAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        StringBuilder sb= new StringBuilder("Select distinct b.* from building b ");

        //join
        sb.append(join(buildingSearchRequest));
        //basic query
        sb.append(basicQuery(buildingSearchRequest));
        //special query
        sb.append(specialQuery(buildingSearchRequest));
        //add Limit and Offset
        sb.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset()).append("\n");
        Query query=em.createNativeQuery(sb.toString(),BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return findAll(buildingSearchRequest).size();
    }

    public String join(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sb =new StringBuilder(" ");
        Long rentAreaFrom= buildingSearchRequest.getRentAreaFrom();
        Long rentAreaTo=buildingSearchRequest.getRentAreaTo();

        if(rentAreaFrom!=null||rentAreaTo!=null) {
            sb.append("Join rentarea ra on b.id= ra.buildingid ");
        }

        if(buildingSearchRequest.getStaffId()!=null) {
            sb.append("Join assignmentbuilding ab on ab.buildingid = b.id ");
        }
        return sb.toString();
    }
    public String basicQuery(BuildingSearchRequest buildingSearchRequest)  {
        StringBuilder sb=new StringBuilder(" where 1 = 1 ");
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            System.out.println(fields.length);
            for(Field field: fields) {
                if(!field.getName().equals("typeCode")&&
                        !field.getName().startsWith("rentPrice")&&
                        !field.getName().startsWith("rentArea")&&
                        !field.getName().equals("staffId")) {
                    field.setAccessible(true);
                    Object value=field.get(buildingSearchRequest);
                    if(value!=null&&value.toString().length()>0) {
                        if(NumberUtil.isNumber(value.toString())) {
                            sb.append(" And b."+ field.getName()+ " = " + value.toString()+ " ");
                        }
                        else {
                            sb.append(" And b."+field.getName()+ " LIKE '%"+value.toString()+"%' ");
                        }
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String specialQuery(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sb=new StringBuilder(" ");
        List<String> typeCodes=buildingSearchRequest.getTypeCode();

        if(typeCodes!=null&&typeCodes.size()>0) {

            if(typeCodes.size()>0) {
                sb.append("And ( ");
                String query=typeCodes.stream().map(s-> " b.type like "+ "'%"+ s + "%' ").collect(Collectors.joining(" or "));
                sb.append(query);
                sb.append(" ) ");
            }
        }
        if(buildingSearchRequest.getStaffId()!=null) {
            sb.append(" And ab.staffid = "+ buildingSearchRequest.getStaffId().toString() +" ");
        }
        Long rentAreaFrom= buildingSearchRequest.getRentAreaFrom();
        Long rentAreaTo=buildingSearchRequest.getRentAreaTo();

        if(rentAreaFrom!=null||rentAreaTo!=null) {
            if(rentAreaFrom!=null) sb.append("And ra.value >= " +rentAreaFrom.toString()+" ");
            if(rentAreaTo!=null) sb.append("And ra.value <= "+rentAreaTo.toString()+" ");
        }
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();

        if(rentPriceFrom!=null||rentPriceTo!=null){
            if(rentPriceFrom!=null) {
                sb.append("And b.rentprice >= "+ buildingSearchRequest.getRentPriceFrom()+" ");
            }
            if(rentPriceTo!=null) {
                sb.append("And b.rentprice <= "+ buildingSearchRequest.getRentPriceTo()+" ");
            }
        }
        return sb.toString();
    }

}
