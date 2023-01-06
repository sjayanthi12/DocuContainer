  $(function() {
            var jsonData;
            $( "#text-drug" ).autocomplete({
                minLength: 3,
               source: function(request, response) {
                    $.ajax({
                        type: "GET",
                        url: "/drug/retrieve/new?name="+$("#text-drug").val(),
                        beforeSend: function( xhr ) {
                            xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                        },
                        success: function (data) {
                            if (data != null) {
                            var parsedJson = $.parseJSON(data);
                            jsonData = parsedJson;
                            var arr = [];
                            for (var i=0;i<parsedJson.length;++i)
                                {
                                    arr.push(parsedJson[i].drugName);
                                }
                            response(arr);        
                          }
                        },
                        error: function(result) {
                            alert("Error");
                        }
                    });
                    },
					select: function(event,ui) { 
					    this.value=ui.item.value; 
					    $(this).trigger('change'); 
					   
					    for (var i=0;i<jsonData.length;++i)
                                {
                                    if( $( "#text-drug" ).val() == jsonData[i].drugName){
                                    	$( "#form" ).val(jsonData[i].form);     
                                    	$( "#dosage" ).val(jsonData[i].dosage);                                    	                                   	                               	
                                    }
                                }					    
					    return false; 
					}                    
                });
            });
           
