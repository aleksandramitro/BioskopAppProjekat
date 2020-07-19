INSERT INTO KORISNIK (korisnicko_ime,lozinka,ime,prezime,broj_telefona,email,datum_rodjenja,uloga,aktivan) VALUES ('aleks','123','Aleksandra','Mitro','062468023','sandra@gmail.com','1999-07-22',0,1);
INSERT INTO KORISNIK (korisnicko_ime,lozinka,ime,prezime,broj_telefona,email,datum_rodjenja,uloga,aktivan) VALUES ('vasilije','sifra','Vasilije','Obradovic','069847832','vasilijeo@gmail.com','1999-05-25',1,1);
INSERT INTO KORISNIK (korisnicko_ime,lozinka,ime,prezime,broj_telefona,email,datum_rodjenja,uloga,aktivan) VALUES ('ana','sifra123','Anastasija','Popovic','063474852','anap@gmail.com','1999-02-19',2,1);

INSERT INTO GLEDALAC(id) VALUES (1);
INSERT INTO MENADZER(id) VALUES (3);

INSERT INTO BIOSKOP (naziv,adresa,broj_telefona,email) VALUES ('Bioskop','Bulevar Mihajla Pupina 7','021410745','bioskop@gmail.com');
INSERT INTO BIOSKOP (naziv,adresa,broj_telefona,email) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 10','021410546','arena@gmail.com');
INSERT INTO BIOSKOP (naziv,adresa,broj_telefona,email) VALUES ('Cinestar','Bulevar Oslobodjenja 67','02143434','cinestar@gmail.com');
INSERT INTO BIOSKOP (naziv,adresa,broj_telefona,email) VALUES ('Cinema','Zmaj Jovina 15','021123123','cinema@gmail.com');


INSERT INTO FILM (naziv,opis,zanr,trajanje,ocena) VALUES ('A quien te llevarias en una isla desierta','Odlazak na pusto ostrvo','komedija',120,4);
INSERT INTO FILM (naziv,opis,zanr,trajanje,ocena) VALUES ('Parazit', 'Prica o razlikama medju stalezima', 'drama', 133, 8.6); 
INSERT INTO FILM (naziv,opis,zanr,trajanje,ocena) VALUES ('Nevidljivi covek', 'Dinamicna prica puna zapleta', 'horor',124,7.8);
INSERT INTO FILM (naziv,opis,zanr,trajanje,ocena) VALUES ('Ricard Dzuel', 'Bombaski napad na OI u Atlanti', 'drama',100,7.6);
INSERT INTO FILM (naziv,opis,zanr,trajanje,ocena) VALUES ('Terminal', 'Covek zarobljen na aerodromu', 'drama',98,6.1);
INSERT INTO FILM (naziv,opis,zanr,trajanje,ocena) VALUES ('Montevideo', 'Prica o Jugoslovenskoj fudbalskoj reprezentaciji', 'drama',140,7.0);

INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (60,1,1);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (80,2,1);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (100,3,1);

INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (30,1,2);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (90,2,2);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (150,3,2);

INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (45,1,3);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (75,2,3);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (105,3,3);

INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (60,1,4);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (50,2,4);
INSERT INTO SALA (kapacitet,oznaka,bioskop_id) VALUES (70,3,4);

INSERT INTO RASPORED (cena,datum_vreme,bioskop_id,film_id) VALUES (150,'2020-7-10 20:30',1,1);
INSERT INTO RASPORED (cena,datum_vreme,bioskop_id,film_id) VALUES (250,'2020-7-10 20:30',2,2);
INSERT INTO RASPORED (cena,datum_vreme,bioskop_id,film_id) VALUES (150,'2020-7-10 20:00',3,3);
INSERT INTO RASPORED (cena,datum_vreme,bioskop_id,film_id) VALUES (350,'2020-7-10 20:00',4,4);
INSERT INTO RASPORED (cena,datum_vreme,bioskop_id,film_id) VALUES (250,'2020-7-11 20:30',1,5);
INSERT INTO RASPORED (cena,datum_vreme,bioskop_id,film_id) VALUES (150,'2020-7-10 20:30',2,6);

INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (1,1);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (2,1);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (3,2);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (2,3);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (1,4);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (2,2);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (3,5);
INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA (sale_id,lista_terminskih_projekcija_id) VALUES (2,6);


INSERT INTO ODGLEDAN_FILM (film_id,gledalac_id) VALUES (1,1);
INSERT INTO ODGLEDAN_FILM (film_id,gledalac_id) VALUES (2,1);
INSERT INTO ODGLEDAN_FILM (film_id,gledalac_id) VALUES (3,1);

INSERT INTO GLEDALAC_REZERVISANE_KARTE (gledaoci_id,rezervisane_karte_id) VALUES (1,1);
INSERT INTO GLEDALAC_REZERVISANE_KARTE (gledaoci_id,rezervisane_karte_id) VALUES (1,2);
INSERT INTO GLEDALAC_REZERVISANE_KARTE (gledaoci_id,rezervisane_karte_id) VALUES (1,3);
INSERT INTO GLEDALAC_REZERVISANE_KARTE (gledaoci_id,rezervisane_karte_id) VALUES (1,4);


INSERT INTO OCENJEN_FILM (ocena,gledalac_id,odgledan_film_id) VALUES (5,1,1);



