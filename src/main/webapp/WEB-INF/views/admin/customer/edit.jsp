<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/28/2024
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content" id="main-container">

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Quản lí khách hàng</a>
                    </li>
                    <c:if test="${not empty customerEdit.id}">
                        <li class="active">Sửa khách hàng</li>
                    </c:if>
                    <c:if test="${empty customerEdit.id}">
                        <li class="active">Thêm khách hàng</li>
                    </c:if>

                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content" >
                <div class="page-header">
                    <h1>
                        Quản lí khách hàng
                        <c:if test="${not empty customerEdit.id}">
                            <small>
                                <i class="ace-icon fa fa-angle-double-right"></i>
                                Sửa khách hàng
                            </small>
                        </c:if>
                        <c:if test="${empty customerEdit.id}">
                            <small>
                                <i class="ace-icon fa fa-angle-double-right"></i>
                                Thêm khách hàng
                            </small>
                        </c:if>

                    </h1>
                </div>
                <!-- bang danh sach-->

                <div class="row " style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="customerEdit" id="form-edit" method="GET" cssClass="form-horizontal">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form" >
                                <div class="form-group">
                                    <label class="col-xs-3"> Tên khách hàng</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="fullName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Số điện thoại</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="phone"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Email</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Tên công ty</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="companyName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Tình trạng</label>
                                    <div class="col-xs-2">
                                        <form:select class="form-control" path="status">
                                            <form:option value="">---Chọn Tình Trạng---</form:option>
                                            <form:options items="${customerStatus}"/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"> Nhu cầu</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="demand"/>
                                    </div>
                                </div>


                                <div>
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateCustomer">Sửa khách hàng </button>
                                        </c:if>
                                        <c:if test="${ empty customerEdit.id}">
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateCustomer">Thêm khách hàng </button>
                                        </c:if>

                                        <button type="button" class="btn btn-info" id="btnCancel">
                                            Hủy
                                        </button>
                                    </div>
                                </div>
                                <form:hidden path="id" id="customerId"/>

                            </form>
                        </div>
                    </form:form>

                </div>

            </div><!-- /.page-content -->
            <c:forEach var="item" items="${transactionType}">
                <div class="col-xs-12">
                    <div class="col-sm-12">
                        <h3 class="header smaller lighter blue">${item.value}</h3>
                        <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}',${customerEdit.id})">
                                <i class="orange ace-icon fa fa-location-arrow bigger-130"></i> Add
                        </button>
                    </div>
                    <c:if test="${item.key=='CSKH'}">
                        <div class="col-xs-12">
                            <table id="simple-table" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <c:forEach var="transaction" items="${CSKHTransactions}">
                                    <tbody>
                                    <tr>
                                        <td>${transaction.createdDate}</td>
                                        <td>${transaction.createdBy}</td>
                                        <td>${transaction.modifiedDate}</td>
                                        <td>${transaction.modifiedBy}</td>
                                        <td>${transaction.note}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-xs btn-info" data-toggle="tooltip" title="sửa thông tin giao dịch"
                                                     onclick="updateTransaction(${transaction.id},'${transaction.note}')">
                                                    <i class="ace-icon fa fa-trash-o bigger-120">
                                                    </i>
                                                </button>

                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </c:forEach>

                            </table>
                        </div>
                    </c:if>
                    <c:if test="${item.key=='DDX'}">
                        <div class="col-xs-12">
                            <table id="simple-table1" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <c:forEach var="transaction" items="${DDXTransactions}">
                                    <tbody>
                                    <tr>
                                        <td>${transaction.createdDate}</td>
                                        <td>${transaction.createdBy}</td>
                                        <td>${transaction.modifiedDate}</td>
                                        <td>${transaction.modifiedBy}</td>
                                        <td>${transaction.note}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-xs btn-info" data-toggle="tooltip" title="sửa thông tin giao dịch"
                                                        onclick="updateTransaction(${transaction.id},'${transaction.note}')">
                                                    <i class="ace-icon fa fa-trash-o bigger-120">
                                                    </i>
                                                </button>

                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </c:forEach>

                            </table>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div><!-- /.main-content -->
    <div class="modal fade" id="transactionTypeModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" id="xButton" name="xButton">x</button>
                    <h4 class="modal-title">Nhập giao dịch</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group has-success">
                        <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">
                            Chi tiết giao dịch
                        </label>
                        <div class="col-xs-12 col-sm-9">
                            <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" class="width-100">
                            </span>
                        </div>
                        <input type="hidden" name="customerId" id="customerId" value="">
                        <input type="hidden" name="code" id="code" value="">
                        <input type="hidden" name="id" id="id" value="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelButton" name="cancelButton">Đóng</button>
                </div>
            </div>
        </div>
    </div>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->
<script>


    $('#btnAddOrUpdateCustomer').click(function(){
        var data={};
        var formData=$('#form-edit').serializeArray();

        console.log("ok");
        $.each(formData,function(i,v){
            data[v.name]=v.value;
        })
        data['isActive']=1;

        //call API
        if(data['name']!=''&&data['customerPhone']!='') {
            addOrUpdateCustomer(data);
        }
        else {
            window.location.href="/admin/customer-edit-" + data['id'] +"?requiredphoneandname";
        }
    });

    function addOrUpdateCustomer(data){
        $.ajax({
            type :'POST',
            url: "/api/customer",
            data: JSON.stringify(data),
            contentType:"application/json",
            dataType:'JSON',
            success: function(respond){
                console.log("Success");
                window.location.href="/admin/customer-list?messsage=success";
            },
            error:function(respond){
                console.log("Error");
                window.location.href="/admin/customer-list?messsage=success";
            }
        })
    }
    $('#btnCancel').click(function(){
        window.location.href="/admin/customer-list";
    })

    function transactionType(code,customerId){
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
    }

    $('#btnAddOrUpdateTransaction').click(function (){
        var data={};
        data['note']=$('#transactionDetail').val()
        data['code']=$('#code').val();
        data['customerId']=$('#customerId').val();
        data['id']=$('#id').val();

        addOrUpdateTransaction(data);

    })
    function addOrUpdateTransaction(data){
        $.ajax({
            type :'POST',
            url: "/api/customer/transaction",
            data: JSON.stringify(data),
            contentType:"application/json",
            dataType:'JSON',
            success: function(respond){
                console.log("Success");
                window.location.href="/admin/customer-edit-"+$('#customerId').val()+"?messsage=success";
            },
            error:function(respond){
                console.log("Error");
                window.location.href="/admin/customer-edit-"+$('#customerId').val()+"?messsage=success";
            }
        })
    }
    function updateTransaction(transactionId,note){
        var data={};
        $('#transactionDetail').val(note);
        $('#id').val(transactionId);
        $('#transactionTypeModal').modal();
    }

    $('#transactionTypeModal').on('hidden.bs.modal', function () {
        $('#transactionDetail').val('');
        $('#id').val('');
        console.log("Modal đã đóng");
    });

</script>
</body>

</html>
