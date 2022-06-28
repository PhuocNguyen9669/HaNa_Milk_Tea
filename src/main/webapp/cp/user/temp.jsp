<%--content--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-6">
            <div>
                <form method="post">
                    <div class="mb-3">
                        <label for="userName" class="form-label">User Name</label>
                        <input type="text" class="form-control" id="userName" name="userName">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="text" class="form-control" id="password" name="password">
                    </div>
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Full name</label>
                        <input type="text" class="form-control" id="fullName" name="fullName">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="number" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="mb-3">
                        <label for="age" class="form-label">Age</label>
                        <input type="number" required class="form-control" id="age" name="age">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address">
                    </div>
                    <div class="mb-3">
                        <ul>
                            <label for="role" class="form-label">Role</label>
                            <select name="role" id="role">
                                <option value="ADMIN">Admin</option>
                                <option value="USER">User</option>
                            </select>
                        </ul>
                    </div>
                    <div class="mb-3">
                        <label for="createdAT" class="form-label">CreatedAT</label>
                        <input type="datetime-local" class="form-control" id="createdAT" name="createdAT">
                    </div>
                    <div class="mb-3">
                        <label for="img" class="form-label">Image</label>
                        <input type="text" class="form-control" id="img" name="img">
                    </div>
                    <%--                        <div class="mb-3">--%>
                    <%--                            <label for="cityId" class="form-label">City</label>--%>
                    <%--                            <select class="form-control" name="cityId" id="cityId">--%>
                    <%--                                <c:forEach items="${requestScope['cityList']}" var="item">--%>
                    <%--                                    <option value="${item.getId()}">${item.getName()}</option>--%>
                    <%--                                </c:forEach>--%>
                    <%--                            </select>--%>
                    <%--                        </div>--%>
                    <button type="submit" class="btn btn-outline-primary">Add user</button>
                </form>
            </div>

            <div class="footer">
                <c:if test="${requestScope['success'] == true}">
                    <ul class="success">
                        <li>Thêm mới thành công</li>
                    </ul>
                </c:if>
                <c:if test="${!requestScope['errors'].isEmpty()}">
                    <ul class="error">
                        <c:forEach items="${requestScope['errors']}" var="item">
                            <li>${item}</li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
    </div>
</div>