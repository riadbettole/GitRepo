<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserGit Edition</title>
</head>
<body>
<header>
    <h1>UserGit Edition</h1>
</header>
<main>
    <form action="updateUserGit" method="post">
        <div>
            <label hidden="hidden" for="id">Name : </label>
            <input hidden="hidden" type="text" id="id" name="id" value="${userGitView.id}">
        </div>
        <div>
            <label for="name">Name : </label>
            <input type="text" id="name" name="name" value="${userGitView.name}">
        </div>
        <div>
            <label for="email">Email : </label>
            <input type="email" id="email" name="email" value="${userGitView.email}">
        </div>
        <div>
            <input type="submit" value="Update">
        </div>
    </form>
</main>
<footer>
    <a href="usersGitList">UserGit List</a>
</footer>
</body>
</html>