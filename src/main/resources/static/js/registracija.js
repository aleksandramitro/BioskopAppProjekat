function registracija() {

	let korisnickoIme = document.getElementById('korisnickoIme').value;
	let lozinka = document.getElementById('lozinka').value;
	let ime  = document.getElementById('ime').value;
	let prezime = document.getElementById('prezime').value;
	let brojTelefona = document.getElementById('brojTelefona').value;
	let datumRodjenja = document.getElementById('datumRodjenja').value;
	let uloga = document.getElementById('uloga').value;
	
	var formData = JSON.stringify({
        "email": email,
        "korisnickoIme": korisnickoIme,
        "lozinka" : lozinka,
        "ime" : ime,
        "prezime" : prezime,
        "brojTelefona" : brojTelefona,
        "datumRodjenja" : datumRodjenja,
        "uloga" : "uloga",
    });
    
    console.log(formData);
    $.ajax({
        url: '/sacuvaj-korisnika',
        dataType: 'json',
        type: 'post',
        contentType: 'application/json',
        data: formData,
        success: function(data){
        	console.log("success");
        	sessionStorage.setItem("id", data["id"]);
            sessionStorage.setItem("uloga",data["uloga"]);
            window.location.replace("/kreiran-korisnik");
        },
        error: function( jqXhr, textStatus, errorThrown ){
            if (jqXhr.status == 409) {
                alert("Greska prilikom obrade podataka!");
                return;
                }
        }
    });

}