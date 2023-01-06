<#-- @ftlvariable name="drugs" type="main.java.domain.Drugs" -->
<!DOCTYPE html>
<html lang="en">
<head>

 <meta charset="utf-8">
      <title>jQuery UI Autocomplete functionality</title>
      <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <!-- Javascript -->
		<script type="text/javascript" src="../../js/drugs.js"></script>
</head>

<body>
<#--
<h1>Drug details</h1>
<label for="text-drug">Name of the drug</label>
<input type="text" id="text-drug" value="Name of the drug"><br>

<label for="form">Form</label>
<input type="text" id="form" value="form"><br>

<label for="dosage">Dosage</label>
<input type="text" id="dosage" value="dosage"><br>

<label for="units">Units</label>
<input type="text" id="units" value="units"><br> -->



<p>
      <label for="text-drug">
        <span>Drug Name: </span>
        <input type="text" id="text-drug" name="drugName" required />
      </label>
    </p>
    
    <p>
      <label for="form">
        <span>Drug Form: </span>
        <input type="text" id="form" name="drugForm" required />
      </label>
    </p>
    
     <p>
      <label for="dosage">
        <span>Dosage: </span>
        <input type="text" id="dosage" name="dosage" required />
      </label>
    </p>
    
      <p>
      <label for="units">
        <span>Units: </span>
        <input type="text" id="units" name="units" required />
      </label>
    </p>

<p>    
    <span>
	  <input type="checkbox" name="monday" value="Monday"> Monday
	  <input type="checkbox" name="tuesday" value="Tuesday"> Tuesday
	  <input type="checkbox" name="wednesday" value="Wednesday"> Wednesday
	  <input type="checkbox" name="thursday" value="Thursday"> Thursday
	  <input type="checkbox" name="friday" value="Friday"> Friday
	  <input type="checkbox" name="saturday" value="Saturday"> Saturday
	  <input type="checkbox" name="sunday" value="Sunday"> Sunday
	  <input type="checkbox" name="allWeek" value="All Week"> All Week
	
</span><br>
</p>

<p>
<span>
	  <input type="checkbox" name="Morning" value="Morning"> Morning
	  <input type="checkbox" name="Afternoon" value="Afternoon"> Afternoon
	  <input type="checkbox" name="Night" value="Night"> Night
</span>	  

</p>
<input type="button" id="add-prescription" value="Add Prescription">
    
</body>
</html>