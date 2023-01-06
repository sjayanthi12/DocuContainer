<!DOCTYPE html>
<html>
<head>
     <style>
    body {
    background-color: #d0e4fe;
    background-image: url("/images/Open-Data-in-Healthcare.png");
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
input {
	text-aling: center;
}
    
    
    </style>

</head>
<body>
	<form method="POST" enctype="multipart/form-data" action="/document/save">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <table>
				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
</form>

</body>
</html>

