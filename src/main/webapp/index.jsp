<html>
<head>
 <title>Appel service REST/Jersy with JQuery</title>
 <script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
 <script src="star-rating/jquery.rating.js"></script>
 <script src="json/json2.js"></script>
 <style type="text/css" media="screen">
  @import "style.css";
  @import "star-rating/jquery.rating.css";
 </style>
</head>
<body>
 <h1>Article</h1>
 <p>Article donnant un vote</p>
 <form id="vote-form">Rating
  <input name="star1" type="radio" class="rating" value="1"/>
  <input name="star1" type="radio" class="rating" value="2"/>
  <input name="star1" type="radio" class="rating" value="3"/>
  <input name="star1" type="radio" class="rating" value="4"/>
  <input name="star1" type="radio" class="rating" value="5"/>
 <div id="author-wrapper">
   <div id="author">101</div>
 </div>
 <div id="ajaxError"></div>

</form>
 <h1>Personne</h1>
 <form id="person-form">
  <table>
  <tr>
   <td>
   <input type="label" value="Identifiant :" />
   </td>
   <td>
   <input type="text" id="ident" value="" />
   </td>
  </tr>
  <tr>
   <td>
    <input type="label" value="Nom :" /> 
   </td>
   <td>
	<input type="text" id="nom" value="" />
   </td>
  </tr>
  <tr>
   <td>
    <input type="label" value="Age :" /> 
   </td>
    <td>
	<input type="text" id="age" value="" />
   </td>
  </tr>
   <tr align="right">
   <td colspan="2">
    <input type="submit" value="Valider" />
   </td>
  </tr>
  </table>
 </form>

 <script type="text/javascript">
     $('#ajaxError').ajaxError(function(e, xhr, settings, exception) {
            $(this).text('Error in: ' + settings.url + ' - Error: ' +
                exception + " - Response: " + xhr.responseText);
    });

    $(document).ready(function() {
        
        $('#target').submit(function() {
  			alert('Handler for .submit() called.');
 			var id = $("#ident").val();
 			var name =$("#nom").val();
 			var age = $("#age").val();
 			var pers = new Personne(id, name, age);
 			alert(JSON.stringify(pers));
 			$.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "http://localhost:8080/jerseySpringJPA/webresources/v2/person",
                    data: JSON.stringify(pers),
                    dataType: "json"
                });
 			
 			return false;
		
		});
        
        var id = $("#author").text(); //This id will be used with the REST back-end.

        $.get("http://localhost:8080/jerseySpringJPA/webresources/v1/" + id, { } ,
            function(author) {
                $("#author").replaceWith("Author: <b>" + author.UserId + " " + author.UserName + "</b><br/>");

                $("#author-wrapper").slideDown("slow");
            },
            "json");

        $(".rating").rating({
            callback: function(value, link){
                adr = new Adresse("94410", "Saint-Maurice");
                
                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "http://localhost:8080/jerseySpringJPA/webresources/v1/adresse",
                    data: JSON.stringify(adr),
                    dataType: "json"
                });
            }
        });
    });
	
    
    function Adresse (codePostal, ville) {
        this.codePostal = codePostal;
        this.ville = ville;        
    }
    
    function Personne (id, name, age) {
        this.id = id;
        this.name = name;
        this.age = age;        
    }
    
 </script>
</body>
</html>