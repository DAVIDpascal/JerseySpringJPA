 <script type="text/javascript">
    function call_rest() {
    
    $('#ajaxError').ajaxError(function(e, xhr, settings, exception) {
            $(this).text('Error in: ' + settings.url + ' - Error: ' +
                exception + " - Response: " + xhr.responseText);
    });

    $(document).ready(function() {
        
        $('#target').submit(function() {
  			alert('Handler for .submit() called.');
 			var id = $('input[@name=ident]').value;
 			var name = $('input[@name=nom]').value;
 			var age = $('input[@name=age]').value;
 			var pers = new Personne(id, name, age);
 			alert(JSON.stringify(pers));
 			$.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "http://localhost:8080/jerseySpringJPA/myresource/person",
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
	}
    
    function Adresse (codePostal, ville) {
        this.codePostal = codePostal;
        this.ville = ville;        
    }
    
    function Peronne (id, name, age) {
        this.id = id;
        this.name = name;
        this.age = age;        
    }
    
 </script>