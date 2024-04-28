<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/28/2024
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/building-list"/>
<c:url var="buildingListURL" value="/admin/building-list"/>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>



    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        Danh sách tòa nhà
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div>
                <!-- /.solenxuong -->
                <div class ="row">
                    <div class="col-xs-12" style="font-family: 'Times New Roman', Times, serif;">
                        <div class="widget-box ui-sortable-handle">
                            <div class="widget-header">
                                <h5 class="widget-title">Tim kiếm</h5>

                                <div class="widget-toolbar">
                                    <div class="widget-menu">
                                        <ul class="dropdown-menu dropdown-menu-right dropdown-light-blue dropdown-caret dropdown-closer">
                                            <li>
                                                <a data-toggle="tab" href="#dropdown1">Option#1</a>
                                            </li>

                                            <li>
                                                <a data-toggle="tab" href="#dropdown2">Option#2</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <!-- Form tìm kiếm-->
                            <div class="widget-body" >
                                <div class="widget-main">
                                    <form:form  id="listForm" modelAttribute="modelSearch" action="${buildingListURL}" method="GET">
                                        <div class="row">
                                            <div id="formList">
                                                <div class ="form-group">
                                                    <div class = "col-xs-12">
                                                        <div class="col-xs-6">
                                                            <label class ="name">Tên tòa nhà</label>
                                                            <form:input type="text" class="form-control" path="name"/>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label class ="name">Diện tích sàn</label>
                                                            <form:input type="number" class="form-control" path="floorArea"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class = "col-xs-12">
                                                        <div class="col-xs-2">
                                                            <label class ="name">Quận</label>
                                                            <form:select class="form-control" path="district">
                                                                <form:option value="">---Chọn Quận---</form:option>
                                                                <form:options items="${districts}"/>
                                                            </form:select>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <label class ="name">Phường</label>
                                                            <form:input type="text" class="form-control" path="ward"/>

                                                        </div>
                                                        <div class="col-xs-5">
                                                            <label class ="name">Đường</label>
                                                            <form:input type="text" class="form-control" path="street"/>

                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class = "col-xs-12">
                                                        <div class="col-xs-4">
                                                            <label class ="name">Số tầng hầm</label>
                                                            <form:input type="number" class="form-control"  path="numberOfBasement"/>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <label class ="name">Hướng</label>
                                                            <form:input type="text" class="form-control"  path="direction"/>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <label class ="name">Hạng</label>
                                                            <form:input type="level" class="form-control"  path="level"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class = "col-xs-12">
                                                        <div class="col-xs-3">
                                                            <label class ="name">Diện tích từ</label>
                                                            <form:input type="number" class="form-control"  path="rentAreaFrom"/>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <label class ="name">Diện tích đến</label>
                                                            <form:input type="number" class="form-control"  path="rentAreaTo"/>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <label class ="name">Giá thuê từ</label>
                                                            <form:input type="number" class="form-control"  path="rentPriceFrom"/>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <label class ="name">Giá thuê đến</label>
                                                            <form:input type="number" class="form-control"  path="rentPriceTo"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class = "col-xs-12">
                                                        <div class="col-xs-5">
                                                            <label class ="name">Tên quản lí</label>
                                                            <form:input type="text" class="form-control"  path="managerName"/>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <label class ="name">SĐT quản lí</label>
                                                            <form:input type="text" class="form-control"  path="managerPhone"/>
                                                        </div>
                                                        <div class="col-xs-2">

                                                            <div>
                                                                <label class ="name">Nhân viên</label>
                                                                <form:select class="form-control" path="staffId">
                                                                    <form:option value="">---Chọn Nhân Viên---</form:option>
                                                                    <form:options items="${staffList}"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class = "col-xs-12" >
                                                        <div class="col-xs-6">
                                                            <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-xs-12 ">
                                                        <div class="col-sm-6">
                                                            <button type="button" class="btn btn-danger" id="btnSearchBuilding">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"></path>
                                                                </svg>
                                                                Tìm kiếm
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>


                                            </div>
                                        </div>
                                    </form:form>


                                </div>
                            </div>
                            <div class ="pull-right">
                                <security:authorize access="hasRole('MANAGER')">
                                    <a href="/admin/building-edit" class="btn btn-info" title="Thêm tòa nhà">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"></path>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"></path>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"></path>
                                        </svg>
                                    </a>
                                </security:authorize>



                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-danger" title="Xóa tòa nhà" id="btnDeleteBuilding" >
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-delete" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"></path>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"></path>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"></path>
                                        </svg>
                                    </button>
                                </security:authorize>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- Bảng tòa nhà-->
                <div class="row">
                    <div class="col-xs-12">
                        <!--  -->
                        <div class="table-responsive">
                            <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                           requestURI="${formUrl}" partialList="true" sort="external"
                                           size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                                           id="tableList" pagesize="${model.maxPageItems}"
                                           export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                headerClass="center select-cell">
                                    <fieldset>
                                        <input type="checkbox" name="checkList" value="${tableList.id}"
                                               id="checkbox_${tableList.id}" class="check-box-element"/>
                                    </fieldset>
                                </display:column>

                                <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                                <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                                <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                                <display:column headerClass="text-left" property="managerName" title="Tên quản lí"/>
                                <display:column headerClass="text-left" property="managerPhone" title="SĐT quản lí"/>
                                <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn"/>
                                <display:column headerClass="text-left" property="emptyArea" title="Diện tích trống"/>
                                <display:column headerClass="text-left" property="rentArea" title="Diện tích thuê"/>
                                <display:column headerClass="text-left" property="brokerageFee" title="Phí môi giới"/>
                                <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                                <display:column headerClass="col-actions" title="Thao tác">

                                    <security:authorize access="hasRole('MANAGER')">
                                        <a class="btn btn-sm btn-success btn-edit" data-toggle="tooltip"
                                           title="Giao tòa nhà"
                                           onclick="assignmentBuilding(${tableList.id})">
                                            <i class="ace-icon glyphicon glyphicon-list"></i>
                                        </a>
                                    </security:authorize>
                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Sửa tòa nhà"
                                       href='<c:url value="/admin/building-edit-${tableList.id}"/>'>
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>

                                    <a class="btn btn-sm btn-danger btn-edit" data-toggle="tooltip"
                                       title="Sửa tòa nhà"
                                       onclick="deleteBuilding(${tableList.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </a>
                                </display:column>
                            </display:table>
                        </div>
                    </div><!-- /.span -->
                </div>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <div class="modal fade" id ="assignmentBuildingModal" role ="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-striped table-bordered table-hover" id="staffList">
                        <thead>
                        <tr>
                            <th class="center">Chọn</th>
                            <th>Tên nhân viên</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <input type="hidden" id="buildingId" name="Building">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btnAssignmentBuilding">Giao tòa nhà</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
            </div>

        </div>
    </div>

</div><!-- /.main-container -->
<script>
    function assignmentBuilding(buildingId){
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        loadStaff(buildingId);
    }

    function loadStaff(buildingId){
        $.ajax({
            type :'GET',
            url: "/api/building/"+ buildingId + '/staffs' ,
            //data: JSON.stringify(data),
            contentType:"application/json",
            dataType:'JSON',
            success: function(response){
                var row='';
                $.each(response.data,function(index,item){
                    row+='<tr>';
                    row+='<td class= "text-center"> <input type="checkbox"  value=' + item.staffId + ' id="checkbox_'+ item.staffId + '" class = "check-box-element"'+item.checked+'/> </td>' ;
                    row += '<td class= "text-center">'+ item.fullName +'</td>';
                    row+='</tr>';
                });
                $('#staffList tbody').html(row);
                console.info("Success");
            },
            error:function(respond){
                console.log("failed");
                window.location.href="<c:url value = "/admin/building-list"/>";
                console.log("response");
            }
        });
    };

    $('#btnAssignmentBuilding').click(function(e){
        e.preventDefault();
        var data={};
        data['buildingId']=$('#buildingId').val();
        var staffs=$('#staffList').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs']=staffs;
        assignment(data);
        //console.log("ok");
    });

    function assignment(data){
        $.ajax({
            type :'POST',
            url: "/api/building/assignment" ,
            data: JSON.stringify(data),
            contentType:"application/json",
            dataType:'JSON',
            success: function(response){
                //console.infor("Thành công!!");
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
                console.log("thanhcong");

            },
            error:function(respond){
                //console.infor("Giao không thành công!!");
                window.location.href="<c:url value = "/admin/building-list?message=error"/>";
                console.log("thatbai");
            }
        });
    }
    var active_class = 'active';
    $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
        var th_checked = this.checked;//checkbox inside "TH" table header
        $(this).closest('table').find('tbody > tr').each(function(){
            var row = this;
            if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
            else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
        });
    });

    $('#btnSearchBuilding').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    })
    function deleteBuilding(id){
        var buildingID=[id];
        deleteBuildings(id);
    }
    $('#btnDeleteBuilding').click(function(e){
        e.preventDefault();
        var buildingIds=$('#tableList').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        deleteBuildings(buildingIds);
    })

    function deleteBuildings(data){
        $.ajax({
            type :'DELETE',
            url: "/api/building/"+ data,
            data: JSON.stringify(data),
            contentType:"application/json",
            dataType:'JSON',
            success: function(respond){
                console.log("Success");
                window.location.href="<c:url value = "/admin/building-list?message=success"/>";
                console.log(respond.constructor());
            },
            error:function(respond){
                console.log("Error");
                window.location.href="<c:url value = "/admin/building-list?message=error"/>";
            }
        });
    }

</script>

</body>
</html>
