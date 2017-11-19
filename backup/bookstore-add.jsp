<%--
  Created by IntelliJ IDEA.
  User: pikachu
  Date: 10/20/17
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Books</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron-narrow.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>


<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="menu.jsp">Home</a></li>
                <li role="presentation"><a href="#">About</a></li>
                <li role="presentation"><a href="#">Contact</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Books</h3>
    </div>


    <div class="jumbotron">

        <form:form modelAttribute="bookProduct">
            <fieldset>
                <form:label path="title">Title:</form:label>
                <form:input path="BookName" cssClass="form-control"/>
                <br />

                <form:label path="title">Pages:</form:label>
                <form:input path="Pages" cssClass="form-control" />
                <br />

                <form:label path="title">Authors:</form:label>
                <form:input path="BookAuthor" cssClass="form-control" />
                <br />

                <form:label path="title">Genres:</form:label>
                <form:input path="BookGenre" cssClass="form-control" />
                <br />

                <form:label path="title">Genres:</form:label>
                <form:input path="BookPublisher"  cssClass="form-control" />
                <br />

                <form:label path="title">Price:</form:label>
                <form:input path="BookPrice" cssClass="form-control" />
                <br />
            </fieldset>

            <input type="submit" value="Save" class="btn btn-lg btn-success" />
        </form:form>
    </div>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
