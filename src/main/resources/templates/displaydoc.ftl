<#-- @ftlvariable name="document" type="main.java.domain.Document" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Document details</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Document details</h1>

<p>Document Name: ${document.filename}</p>
<img src= ${document.url} style="width:128px;height:128px;"/>

</body>
</html>