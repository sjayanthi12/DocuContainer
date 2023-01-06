<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="core.domain.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <style>
    body {
    background-color: #d0e4fe;
    background-image: url("/images/medical-development.jpg");
    background-repeat: no-repeat;
    background-position: center top;
}
h1 {
    color: orange;
    text-align: center;
}
h2 {
    color: orange;
    text-align: center;
}
p {
    font-family: "Times New Roman";
    font-size: 20px;
}
.footer {
  position: absolute;
  right: 0;
  bottom: 0;
  left: 0;
  padding: 1rem;
  background-color: #efefef;
  text-align: center;
}

    </style>
</head>
<body>
<nav role="navigation">

<div style="width:800px; margin:0 auto;">
        <h1> Welcome to Aushadi </h1>        
    </div>

    <ul>
    <#if !currentUser??>
        <li><a href="/login">Log in</a></li>
    </#if>
    <#if currentUser??>
        <li>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Log out</button>
            </form>
        </li>
        <li><a href="/user/${currentUser.id}">View myself</a></li>
        <li><a href="/document/upload">Upload Documents</a></li>
        <li><a href="/document/retrieve/">Retrieve Documents</a></li>
        <li><a href="/drug/retrieve/">List all drugs</a></li>
    </#if>
    <#if currentUser?? && currentUser.role == "ADMIN">
        <li><a href="/user/create">Create a new user</a></li>
        <li><a href="/users">View all users</a></li>
    </#if>
    </ul>
</nav>
<div class="footer">Aushadhi’s goal is to provide affordable, timely and holistic healthcare. We believe that providing medicines at a affordable price; opening up multiple channels of communication between patients and healthcare providers; easy access to healthcare records will immensely benefit general population, especially elderly persons.
We want to use technology to make it easier for patients to order and receive medicines. Additionally, smart phones apps will be used to provide interactive healthcare advice via opening on demand communication channels with nurses, doctors and pharmacists. Patients and their families will be able to consult with doctors, access medical records and manage their healthcare expenses from any part of the world..</div>
</body>
</html>