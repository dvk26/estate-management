package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name= "assignmentcustomer")
public class AssignmentCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="customerid")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="staffid")
    private UserEntity user;
}
