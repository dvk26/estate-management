package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity{
    private static final long serialVersionUID = -4988455421375043688L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "demand")
    private String demand;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "is_active", nullable = false)
    private Integer isActive;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="assignmentcustomer",
            joinColumns= @JoinColumn(name="customerid", nullable=false),
            inverseJoinColumns=@JoinColumn(name="staffid",nullable=false))
    List<UserEntity> users;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TransactionEntity> transactions;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
