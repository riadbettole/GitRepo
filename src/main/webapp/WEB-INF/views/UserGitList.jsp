<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>UserGit List</title>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
</head>
<body>
<header>
    <h1>UserGit List</h1>
</header>
<main>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">EMAIL</th>
                <th scope="col">STATE</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${userGitVue}" var="userGit">
            <tr>
                <td>${userGit.id}</td>
                <td>${userGit.name}</td>
                <td>${userGit.email}</td>
                <td>${userGit.userGitState}</td>
                <td>
                    <a
                            class="btn bg-danger"
                            onclick="return confirm('Are you sure you want to delete this UserGit?')"
                            href="deleteUserGit?id=${userGit.id}"
                    >Delete
                    </a>
                    <a class="btn bg-success" href="editUserGit?id=${userGit.id}">
                    Edit
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</main>
<footer>
    <a href="createUserGit">Customer Creation</a>
</footer>
</body>
</html>


