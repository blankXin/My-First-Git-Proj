<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
           <fm:form method="post" modelAttribute="provider">
           		<fm:errors path="proCode"/><br/>
           		供应商编码：<fm:input path="proCode"/><br/>
           		<fm:errors path="proName"/><br/>
           		供应商名称：<fm:input path="proName"/><br/>
           		联   系   人：<fm:input path="proContact"/><br/>
           		<fm:errors path="proPhone"/><br/>
           		联 系 电 话：<fm:input path="proPhone"/><br/>
           		联 系 地 址：<fm:input path="proAddress"/><br/>
           		传         真：<fm:input path="proFax"/><br/>
           		描         述：<fm:input path="proDesc"/><br/>
           		<input type="submit" value="保存" />
           </fm:form>
        </div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/provideradd.js"></script>
