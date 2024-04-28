<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/28/2024
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
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
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content" >
                <div class="page-header">
                    <h1>
                        Dashboard
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div>
                <!-- bang danh sach-->

                <div class="row " style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="buildingEdit" id="form-edit" method="GET" cssClass="form-horizontal">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form" >
                                <div class="form-group">
                                    <label class="col-xs-3"> Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="name"/>
                                    </div>
                                </div>
                                <div class="form-group">

                                    <label  class="col-xs-3"> Quận</label>
                                    <div class="col-xs-2">
                                        <form:select class="form-control" path="district">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${districts}"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="ward"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Đường</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="street"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Kết cấu</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="structure"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <form:input type="number" class="form-control"  path="numberOfBasement"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Diện tích sàn</label>
                                    <div class="col-xs-9">
                                        <form:input type="number" class="form-control"  path="floorArea"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Hướng</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="direction"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Hạng</label>
                                    <div class="col-xs-9">
                                        <form:input type="number" class="form-control"  path="level"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="rentArea"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <form:input type="number" class="form-control"  path="rentPrice"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="rentPriceDescription"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="serviceFee"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí ô tô</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="carFee"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí mô tô</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="motoFee"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="overtimeFee"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="electricityFee"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="deposit"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="payment"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="rentTime"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="decorationTime"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="managerName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">SĐT quản lý</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="managerPhone"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="brokerageFee"/>
                                    </div>
                                </div>
                                <div class="form-group">

                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-9">
                                        <div class="col-xs-6">
                                            <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3"> Ghi chú</label>
                                    <div class="col-xs-9">
                                        <form:input type="text" class="form-control"  path="note"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 no-padding-right"> Hình đại diện</label>
                                    <input class="col-xs-3 no-padding-right " type="file" id="uploadImage" />
                                    <div class="col-sm-9">
                                        <c:if test="${not empty buildingEdit.image}">
                                            <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                            <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                        </c:if>
                                        <c:if test="${empty buildingEdit.image}">
                                            <img src="image/default.png" id="viewImage" width="300px" height="300px">
                                        </c:if>

                                    </div>
                                </div>



                                <div>
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty buildingEdit.id}" >
                                        <button type="button" class="btn btn-info" id="btnAddOrUpdateBuilding">Cập nhập tòa nhà </button>
                                        <button type="button" class="btn btn-info" id="btnCancel">
                                            Hủy thao tác
                                        </button>
                                        </c:if>
                                        <c:if test="${ empty buildingEdit.id}" >
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateBuilding">Thêm mới </button>
                                            <button type="button" class="btn btn-info" id="btnCancel">
                                                Hủy thao tác
                                            </button>
                                        </c:if>
                                    </div>
                                </div>



                                <form:hidden path="id" id="buildingId"/>

                            </form>
                        </div>
                    </form:form>

                </div>



            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

</div><!-- /.main-container -->
<script>

    var imageBase64 = '';
    var imageName = '';
    $('#btnAddOrUpdateBuilding').click(function(){
        var data={};
        var typeCode=[];
        var formData=$('#form-edit').serializeArray();

        console.log("ok");
        $.each(formData,function(i,v){
            if(v.name!='typeCode'){
                data[v.name]=v.value;
            }
            else{
                typeCode.push(v.value);
            }
            if(''!== imageBase64){
                data['imageBase64']=imageBase64;
                data['imageName']=imageName;
            }
            console.log(i);
        })
        data['typeCode']=typeCode;
        //call API
        if(typeCode.length>0) {
            addOrUpdateBuilding(data);
        }
        else {
            if(data['id']!='') window.location.href="/admin/building-edit-"+data['id']+"?requiredTypeCode";
            else window.location.href="/admin/building-edit?requiredTypeCode";

        }
    });

    function addOrUpdateBuilding(data){

        $.ajax({
            type :'POST',
            url: "/api/building",
            data: JSON.stringify(data),
            contentType:"application/json",
            dataType:'JSON',
            success: function(respond){
                console.log("Success");
                window.location.href="/admin/building-list?messsage=success";
            },
            error:function(respond){
                console.log("Error");
            }
        })
    }
    $('#btnCancel').click(function(){
        window.location.href="/admin/building-list";
    })


    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function (e) {
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    })

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }


</script>
</body>

</html>
