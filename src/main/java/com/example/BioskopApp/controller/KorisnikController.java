package com.example.BioskopApp.controller;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BioskopApp.dto.PretragaDTO;
import com.example.BioskopApp.dto.PretragaVremenaDTO;
import com.example.BioskopApp.dto.Sortiranje;
import com.example.BioskopApp.dto.SortiranjeDTO;
import com.example.BioskopApp.entity.Bioskop;
import com.example.BioskopApp.entity.Film;
import com.example.BioskopApp.entity.Gledalac;
import com.example.BioskopApp.entity.Karta;
import com.example.BioskopApp.entity.Korisnik;
import com.example.BioskopApp.entity.Menadzer;
import com.example.BioskopApp.entity.OcenjenFilm;
import com.example.BioskopApp.entity.OdgledanFilm;
import com.example.BioskopApp.entity.Raspored;

import com.example.BioskopApp.entity.Sala;
import com.example.BioskopApp.entity.Uloga;
import com.example.BioskopApp.entity.UlogovanKorisnik;
import com.example.BioskopApp.service.BioskopService;
import com.example.BioskopApp.service.FilmService;
import com.example.BioskopApp.service.GledalacService;
import com.example.BioskopApp.service.KartaService;
import com.example.BioskopApp.service.KorisnikService;
import com.example.BioskopApp.service.MenadzerService;
import com.example.BioskopApp.service.OcenjenFilmService;
import com.example.BioskopApp.service.OdgledanFilmService;

import com.example.BioskopApp.service.RasporedService;
import com.example.BioskopApp.service.SalaService;
import com.example.BioskopApp.service.UlogovanKorisnikService;

@Controller
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private BioskopService bioskopService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private GledalacService gledalacService;
	
	@Autowired
	private MenadzerService menadzerService;
	
	@Autowired
	private OcenjenFilmService ocenjenFilmService;
	
	@Autowired
	private OdgledanFilmService odgledanFilmService;
	
	@Autowired
	private RasporedService rasporedService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private UlogovanKorisnikService ulogovanKorisnikService;
	
	@Autowired
	private KartaService kartaService;
	
	@GetMapping("/")
    public String welcome() {
        return "home.html";
    }
	@RequestMapping(value = "/sacuvaj-korisnika", method = { RequestMethod.GET, RequestMethod.POST })
	public String registracijaKorisnika(@ModelAttribute Korisnik korisnik,BindingResult errors,Model model, RedirectAttributes rattrs) {
		
		
		korisnik.setAktivan(true);
		korisnik.setUloga(Uloga.GLEDALAC);
		
		Korisnik k = korisnikService.findByKorisnickoIme(korisnik.getKorisnickoIme());
		if(k!=null) {
			
			return "redirect:/registracija";
		}
		
		if (korisnik == null){
	      
	            rattrs.addFlashAttribute("check","true");  //neuspesna registracija
	            return "redirect:/registracija";
	     }
		 
		korisnikService.registracija(korisnik);
		gledalacService.dodaj(korisnik);

		return "redirect:/kreiran-korisnik";
		
	}
	
	@GetMapping("/registracija")
	public String registracija(Model model){
		model.addAttribute("korisnik", new Korisnik());
		model.addAttribute("check", false);
		return "registracija.html";
	}
	
	@GetMapping("/registruj-menadzera")
	public String registrujMenadzera(Model model) {
		
		UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		
		if(k.getUloga().equals(Uloga.ADMIN)) {
			
			model.addAttribute("korisnik", new Korisnik());
			model.addAttribute("check", false);
			return "registruj-menadzera";
		}
		
		return "error-page";
	}
	
	@RequestMapping(value = "/sacuvaj-menadzera", method = { RequestMethod.GET, RequestMethod.POST })
	public String registracijaMenadzera(@ModelAttribute Korisnik korisnik,BindingResult errors,Model model, RedirectAttributes rattrs) {
		
		korisnik.setAktivan(true);
		korisnik.setUloga(Uloga.MENADZER);
		
		Korisnik k = korisnikService.findByKorisnickoIme(korisnik.getKorisnickoIme());
		if(k!=null) {
			
			return "redirect:/registruj-menadzera";
		}
		
		if (korisnik == null){
	      
	            rattrs.addFlashAttribute("check","true");  //neuspesna registracija
	            return "redirect:/registracija";
	     }
		 
		korisnikService.registracija(korisnik);
		menadzerService.dodaj(korisnik);

		return "redirect:/kreiran-korisnik";
	}
	
	@GetMapping("/ceka-menadzer")
	public String cekaMenadzer() {
		
		return "ceka-menadzer";
	}
	
	public String registration(Korisnik korisnik) {
        korisnikService.registracija(korisnik);

        return "redirect:/login";
    }
	
	 @GetMapping("/kreiran-korisnik")
	 public String kreiranKorisnik() {
		 
		 return "kreiran-korisnik.html";
	 }
	 
	 @RequestMapping(value = "/potvrda", method = { RequestMethod.GET, RequestMethod.POST })
	 public String potvrdaPrijave(@ModelAttribute UlogovanKorisnik ulogovanKorisnik,BindingResult error,Model model) {
		 
		 String korisnickoIme = ulogovanKorisnik.getKorisnickoIme();
		 String lozinka = ulogovanKorisnik.getLozinka();
		 
		 Korisnik korisnik = korisnikService.login(korisnickoIme, lozinka);
		 
		 if(korisnik!=null && korisnik.isAktivan() == true) {
			 
			 UlogovanKorisnik ul = new UlogovanKorisnik(null,korisnickoIme,lozinka);
			 ulogovanKorisnikService.save(ul);
		 } else {
			 
			 return "redirect:/nije-pronadjen-korisnik";
		 }
		 
		 if(korisnik.getUloga().equals(Uloga.GLEDALAC)) {
			 
			 return "redirect:/gledalac-profil";
		 }
		 if(korisnik.getUloga().equals(Uloga.ADMIN)) {
			 
			 return "redirect:/admin-profil";
		 } 
		 if(korisnik.getUloga().equals(Uloga.MENADZER)) {
			 
			 return "redirect:/menadzer-profil";
		 }
		 model.addAttribute("check", true);
	     return "login.html";
	 }
	 
	 @GetMapping("/login")
	 public String prijava(Model model) {
		 
		if(korisnikService.proveraListeKorisnika() == false) {
			 
			 return "redirect:/error-page";
		 }
		 
		 model.addAttribute("ulogovanKorisnik", new UlogovanKorisnik());
		 model.addAttribute("check", false);
		 
		 return "login.html";
		 
	 }
	 
	 @GetMapping("/error-page")
	 public String errorPage() {
		 
		 return "error-page.html";
	 }
	
	 @GetMapping("/gledalac-profil")
	 public String profilGledaoca(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "error-page";
		 } else {
			 
			 Korisnik korisnik = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
			 
			 if(korisnik.getUloga().equals(Uloga.GLEDALAC)) {
				 
				 model.addAttribute("korisnik",korisnik);
				
			 } else {
				 
				 return "error-page";
			 }
			 
		 }
		 
		 return "gledalac-profil";
	 }
	 
	 @GetMapping("/admin-profil") 
	 public String profilAdmina(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 
		if(ul!=null) {
			
			Korisnik korisnik = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
			
			if(korisnik.getUloga().equals(Uloga.ADMIN)) {
				
				model.addAttribute("korisnik",korisnik);
			} else {
				
				return "error-page";
			}
		} else {
			
			return "error-page";
		}
		 
		 return "admin-profil";
	 }
	 
	 @GetMapping("/menadzer-profil")
	 public String profilMenadzera(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 
			if(ul!=null) {
				
				Korisnik korisnik = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
				model.addAttribute("korisnik",korisnik);
				
		
			} else {
				
				return "error-page";
			}
			 
			 return "menadzer-profil";
	 }
	 
	 @GetMapping("/moj-profil")
	 public String prikazProfila(Model model) {
		 
		UlogovanKorisnik ulogovanKorisnik = ulogovanKorisnikService.getTrenutniKorisnik();
		
		if(ulogovanKorisnik == null) {
			
			model.addAttribute("check", true);
			return "redirect:/login";
		} else {
			
			Korisnik korisnik = korisnikService.findByKorisnickoIme(ulogovanKorisnik.getKorisnickoIme());
			
			if(korisnik.getUloga().equals(Uloga.ADMIN)) {
				
				return "redirect:/admin-profil";
			}
			if(korisnik.getUloga().equals(Uloga.GLEDALAC)) {
				
				return "redirect:/gledalac-profil";
			}
			
			if(korisnik.getUloga().equals(Uloga.MENADZER)) {
				
				return "redirect:/menadzer-profil";
			}
		}
		
		model.addAttribute("check", true);
        return "login.html";
		
	 }
	 
	 @GetMapping("/odjava")
	 public String odjavaKorisnika() {
		 
		ulogovanKorisnikService.deleteAll();
		return "home.html";
	 }
	 
	 @GetMapping("/nije-pronadjen-korisnik")
	 public String nijePronadjenKorisnik() {
		 
		 return "nije-pronadjen-korisnik";
	 }
	 
	 @GetMapping("/ponuda-bioskopa")
	 public String ponudaBioskopa(Model model) {
		 
		 List<Bioskop> bioskopi = bioskopService.findAll();
		 model.addAttribute("bioskopi", bioskopi);
		 return "ponuda-bioskopa";
	 }
	 
	 @GetMapping("/dodavanje-bioskopa")
	 public String dodajBioskop(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.ADMIN)) {
			 model.addAttribute("bioskop", new Bioskop());
			 model.addAttribute("check", false);
			 return "dodavanje-bioskopa";
		 }
		 return "ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value = "/sacuvaj-bioskop", method = { RequestMethod.GET, RequestMethod.POST })
	 public String sacuvajBioskop(@ModelAttribute Bioskop bioskop,BindingResult error,Model model) {
		 
		 String naziv = bioskop.getNaziv();
		 String adresa = bioskop.getAdresa();
		 String email = bioskop.getEmail();
		 String brojTelefona = bioskop.getBrojTelefona();
		 Set<Sala> bioskopskeSale = new HashSet<Sala>();
		 Set<Menadzer> menadzeri = new HashSet<Menadzer>();
		 Set<Raspored> rasporedOdrzavanjaFilmova = new HashSet<Raspored>();
		 
		 Bioskop b = new Bioskop(null, naziv, adresa, brojTelefona, email, bioskopskeSale, menadzeri, rasporedOdrzavanjaFilmova);
		
		 if(b==null) {
			 
			 	return "redirect:/dodavanje-bioskopa";
		  }
		 
		  bioskopService.sacuvaj(b);
		 
		  return "redirect:/ponuda-bioskopa";
		 
	 }
	 
	 @RequestMapping(value = "/brisanje-bioskopa/{id}", method = { RequestMethod.GET, RequestMethod.DELETE })
	 public String obrisiBioskop(@PathVariable(name = "id") Long id) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Bioskop bioskop = bioskopService.findOne(id);
		 if(k.getUloga().equals(Uloga.ADMIN)) {
			
			 Set<Sala> sale = bioskop.getBioskopskeSale();
			 List<Sala> s = salaService.findAll();
			 s.removeAll(sale);
			 Set<Raspored> rasporedi = bioskop.getRasporedOdrzavanjaFilmova();
			 List<Raspored> r = rasporedService.findAll();
			 r.removeAll(rasporedi);
			 
		 } else {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value ="/izmeni-bioskop/{id}", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String izmeniBioskop(@PathVariable(name = "id") Long id , Model model) {
		 
		
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.ADMIN)) {
			 Bioskop bioskop = bioskopService.findOne(id);
			 model.addAttribute("bioskop",bioskop);
			 return "sacuvaj";
		 }
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 
	 
	 @GetMapping("/bioskopske-sale/{bioskopId}")
	 public String bioskopskeSale(@PathVariable(name = "bioskopId") Long bioskopId , Model model) {
		 
		 Bioskop bioskop = bioskopService.findOne(bioskopId);
		 model.addAttribute("bioskop", bioskop);
		 model.addAttribute("sale", bioskop.getBioskopskeSale());
		 return "sale";
	 }
	 
	 @GetMapping("/sacuvaj") 
	 public String izmeniBioskop(Model model) {
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.ADMIN)) {
			 model.addAttribute("bioskop", new Bioskop());
			 model.addAttribute("check", false);
			 return "sacuvaj";
		 }
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value = "/izmeni-bioskop/{id}/potvrda-izmene", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sacuvajIzmenu(@PathVariable(name = "id") Long id, @ModelAttribute Bioskop bioskop,BindingResult error,Model model) {
		 
		 Bioskop bStari = bioskopService.findOne(id);
		 bStari.setAdresa(bioskop.getAdresa());
		 bStari.setNaziv(bioskop.getNaziv());
		 bStari.setBrojTelefona(bioskop.getBrojTelefona());
		 bStari.setEmail(bioskop.getEmail());
		 bioskopService.update(bStari);
		 
		 
		 return "redirect:/ponuda-bioskopa";
		
	 }
	 
	 
	 @GetMapping("/filmovi") 
	 public String filmovi(Model model) {
		 
		 List<Film> filmovi = filmService.findAll();
		 model.addAttribute("filmovi", filmovi);
		 return "filmovi.html";
		 
	}
	
	 @GetMapping("/bioskopske-sale/{bioskopId}/dodavanje-sale")
	 public String dodajSale(@PathVariable(name = "bioskopId") Long bioskopId, Model model) {
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.MENADZER)) {
			 
			 Menadzer m = menadzerService.findByKorisnickoIme(ul.getKorisnickoIme());
			 Bioskop bioskop = bioskopService.findOne(bioskopId);
			 boolean found = menadzerService.zaduzenZaBioskop(m, bioskop);
			 if(found == true) {
				 model.addAttribute("sala", new Sala());
				 model.addAttribute("check", false);
				 model.addAttribute("bioskop", bioskop);
				 return "dodavanje-sale";
			 }
			 
		 }
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value = "/bioskopske-sale/{bioskopId}/sacuvaj-salu", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sacuvajNovuSalu(@PathVariable(name = "bioskopId") Long bioskopId, @ModelAttribute Sala sala,BindingResult error,Model model) {
		 
		
		int kapacitet = sala.getKapacitet();
		int oznaka = sala.getOznaka();
	    Bioskop bioskop = bioskopService.findOne(bioskopId);
		Set<Raspored> listaTerminskihProjekcija = new HashSet<Raspored>();
		 
		 Sala s = new Sala(null,kapacitet,oznaka,bioskop,listaTerminskihProjekcija);
		 salaService.sacuvaj(s);
			 
		 Set<Sala> bioskopskeSale = bioskop.getBioskopskeSale();
		 bioskopskeSale.add(s);
		 bioskopService.update(bioskop);
			 
		 
		 return "redirect:/ponuda-bioskopa";
	 }
	 

	 @RequestMapping(value = "/bioskopske-sale/{bioskopId}/obrisi-salu/{salaId}", method = { RequestMethod.GET, RequestMethod.DELETE })
	 public String obrisiSalu(@PathVariable(name = "bioskopId") Long bioskopId , @PathVariable(name = "salaId") Long salaId) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 
		 if(k.getUloga().equals(Uloga.MENADZER)) {
			 
			 Menadzer m = menadzerService.findByKorisnickoIme(ul.getKorisnickoIme());
			 Bioskop bioskop = bioskopService.findOne(bioskopId);
			 Sala sala = salaService.findOne(salaId);
			 boolean t = menadzerService.zaduzenZaBioskop(m, bioskop);
			 if(t == true) {
				 
				 bioskop.getBioskopskeSale().remove(sala);
				 bioskopService.update(bioskop);
				// salaService.delete(sala);
				 /*List<Raspored> rasporedi = rasporedService.findAll();
				 for (Raspored raspored : rasporedi) {
					
					 Set<Sala> sale = raspored.getSale();
					 for (Sala s : sale) {
						
						 if(s.getId() == salaId) {
							 
							 sale.remove(s);
						 }
					}
				}
				 for (Raspored raspored : rasporedi) {
					
					 rasporedService.update(raspored);
				}*/
			}
			 
		 } else {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value = "/bioskopske-sale/{bioskopId}/izmena-sale/{salaId}", method = { RequestMethod.GET, RequestMethod.POST })
	 public String izmeniSalu(@PathVariable(name = "bioskopId") Long bioskopId , @PathVariable(name = "salaId") Long salaId , Model model) {
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.MENADZER)) {
			 Menadzer m = menadzerService.findByKorisnickoIme(ul.getKorisnickoIme());
			 Bioskop bioskop = bioskopService.findOne(bioskopId);
			 boolean t = menadzerService.zaduzenZaBioskop(m, bioskop);
			 if(t == true) {
				 Sala sala = salaService.findOne(salaId);
				 model.addAttribute("bioskop", bioskop);
				 model.addAttribute("sala", sala);
				 return "izmena-sale";
			 }
		 }
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value = "/bioskopske-sale/{bioskopId}/izmena-sale/{salaId}/sacuvaj", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sacuvajIzmenjenuSalu(@PathVariable(name = "bioskopId") Long bioskopId , @PathVariable(name = "salaId") Long salaId , @ModelAttribute Sala sala,BindingResult error,Model model) {
		 
		 Bioskop bioskop = bioskopService.findOne(bioskopId);
		 Sala salaStara = salaService.findOne(salaId); 
		 salaStara.setKapacitet(sala.getKapacitet());
		 salaStara.setOznaka(sala.getOznaka());
		 
		 salaService.update(salaStara);
		 
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @GetMapping("/pogledaj-menadzere")
	 public String pogledajMenadzere(Model model) {
		 
		 List<Menadzer> menadzeri = menadzerService.findAll();
		 model.addAttribute("menadzeri", menadzeri);
		 return "pogledaj-menadzere";
	
	 }
	 
	 @RequestMapping(value = "/ukloni-menadzera/{id}", method = { RequestMethod.GET, RequestMethod.DELETE} )
	 public String obrisiMenadzera(@PathVariable(name =  "id") Long id) {
		 
		 Menadzer menadzer = menadzerService.findOne(id);
		 Set<Bioskop> bioskopi = menadzer.getBioskopi();

		 
		 for (Bioskop bioskop : bioskopi) {
			
			 Set<Menadzer> menadzeri = bioskop.getMenadzeri();
			 for (Menadzer menadzer2 : menadzeri) {
				
				 if(menadzer2.getId() == menadzer.getId()) {
					 
					 menadzeri.remove(menadzer2);
					 bioskopService.update(bioskop);
				 }
			}
		}
	
		menadzerService.delete(menadzer);
		
		 
		 return "redirect:/pogledaj-menadzere";
		 
	 }
	 
	 @GetMapping("/dodeli-bioskop/{menadzerId}")
	 public String dodelaBioskopa(@PathVariable(name = "menadzerId") Long menadzerId, Model model) {
		 
		 Menadzer menadzer = menadzerService.findOne(menadzerId);
		 model.addAttribute("menadzer", menadzer);
		 List<Bioskop> bioskopi = bioskopService.findAll();
		 model.addAttribute("bioskopi", bioskopi);
		 return "dodela-bioskopa";
	 }
	 
	 @GetMapping("/dodeli-bioskop/{menadzerId}/dodaj/{bioskopId}")
	 public String menadzerBioskop(@PathVariable(name = "menadzerId") Long menadzerId, @PathVariable(name = "bioskopId") Long bioskopId) {
		 
		 Menadzer menadzer = menadzerService.findOne(menadzerId);
		 Bioskop bioskop = bioskopService.findOne(bioskopId);
		 Set<Bioskop> bioskopi = menadzer.getBioskopi();
		 for (Bioskop bioskop2 : bioskopi) {
			
			 if(bioskop2.getId() == bioskop.getId()) {
				 
				 return "redirect:/pogledaj-menadzere";
			 }
		 }
		 bioskop.getMenadzeri().add(menadzer);
		 menadzer.getBioskopi().add(bioskop);
		 bioskopService.update(bioskop);
		 menadzerService.update(menadzer);
		 return "redirect:/pogledaj-menadzere";
	 }
	 
	 @GetMapping("/otkaz-bioskopa/{menadzerId}")
	 public String otkaziBioskop(@PathVariable(name = "menadzerId") Long menadzerId, Model model) {
		 
		 Menadzer menadzer = menadzerService.findOne(menadzerId);
		 model.addAttribute("menadzer", menadzer);
		 List<Bioskop> bioskopi = bioskopService.findAll();
		 model.addAttribute("bioskopi", bioskopi);
		 
		 return "otkaz-bioskopa";
	 }
	 
	 @RequestMapping(value = "/otkaz-bioskopa/{menadzerId}/otkazi/{bioskopId}", method = { RequestMethod.GET, RequestMethod.DELETE })
	 public String menadzerOtkazan(@PathVariable(name = "menadzerId") Long menadzerId, @PathVariable(name = "bioskopId") Long bioskopId) {
		 
		 Menadzer menadzer = menadzerService.findOne(menadzerId);
		 Bioskop bioskop = bioskopService.findOne(bioskopId);
		 Set<Bioskop> bioskopi = menadzer.getBioskopi();
		 Set<Menadzer> menadzeri = bioskop.getMenadzeri();
		 if(menadzeri.size() > 1) {
			 
			 menadzeri.remove(menadzer);
			 bioskopi.remove(bioskop);
			 bioskopService.update(bioskop);
			 return "redirect:/pogledaj-menadzere";
		 } 
		 
		 return "redirect:/error-page";
	 }
	 
	 @GetMapping("/odgledani-filmovi")
	 public String odgledaniFilmovi(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac g = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 
		 Set<OdgledanFilm> odgledaniFilmovi = g.getOdgledaniFilmovi();
		 model.addAttribute("odgledaniFilmovi", odgledaniFilmovi);
		 
		 return "odgledani-filmovi";
	 }
	 
	 @GetMapping("/ocenjeni-filmovi")
	 public String ocenjeniFilmovi(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac g = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Set<OcenjenFilm> ocenjeniFilmovi = g.getOcenjeniFilmovi();
		 model.addAttribute("ocenjeniFilmovi", ocenjeniFilmovi);
		 
		 return "ocenjeni-filmovi";
		 
	 }
	 
	 @GetMapping("/rezervisane-karte")
	 public String rezervisaneKarte(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac g = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Set<Raspored> karte = g.getRezervisaneKarte();
		 model.addAttribute("karte", karte);
		 
		 return "rezervisane-karte";
		 
	 }
	 
	 @GetMapping("/neocenjeni-filmovi")
	 public String neocenjeniFilmovi(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac g = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Set<OdgledanFilm> odgledaniFilmovi = new HashSet<OdgledanFilm>();
		 
		 Set<OcenjenFilm> ocenjeni = g.getOcenjeniFilmovi();
		 Set<OdgledanFilm> odgledani = g.getOdgledaniFilmovi();
		 
		 
		 for (OdgledanFilm odgledanFilm : odgledani) {
			
			 for (OcenjenFilm ocenjen2 : ocenjeni) {
				
				boolean found = false;
				if(ocenjen2.getOdgledanFilm() == odgledanFilm) {
					
					found = true;
				}
				if(found == false) {
					
					odgledaniFilmovi.add(odgledanFilm);
				}
			}
		}
		 
		model.addAttribute("odgledaniFilmovi", odgledaniFilmovi);
		 
		 
		 return "neocenjeni-filmovi";
	 }
	 
	 @GetMapping("/moji-bioskopi")
	 public String mojiBioskopi(Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Menadzer menadzer = menadzerService.findByKorisnickoIme(ul.getKorisnickoIme());
		 
		 Set<Bioskop> bioskopi = menadzer.getBioskopi();
		 model.addAttribute("bioskopi", bioskopi);
		 
		 return "moji-bioskopi";
	 }
	 
	 @GetMapping("/oceni-film/{id}")
	 public String oceniFilm(@PathVariable(name="id") Long id, Model model) {
		 
		 OdgledanFilm odgledan = odgledanFilmService.findOne(id);
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac gledalac = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(ocenjenFilmService.findByOdgledanFilm(odgledan) != null) {

			 return "redirect:/filmovi";
		 }
		 model.addAttribute("odgledan", odgledan);
		 model.addAttribute("gledalac", gledalac);
		 model.addAttribute("ocenjenFilm", new OcenjenFilm());
		
		 return "oceni-film";
		
	 }
	 
	 @RequestMapping(value = "/oceni-film/{id}/sacuvaj-ocenu", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sacuvajOcenu(@PathVariable(name="id") Long id, @ModelAttribute OcenjenFilm ocenjenFilm) {
		 
		 OdgledanFilm odgledan = odgledanFilmService.findOne(id);
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac gledalac = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 
		 OcenjenFilm ocenjen = ocenjenFilmService.findByOdgledanFilm(odgledan);
		 if(ocenjen == null) {
			 int ocena = ocenjenFilm.getOcena();
			 OcenjenFilm o = new OcenjenFilm(null,odgledan,ocena,gledalac);
			 ocenjenFilmService.save(o);
			 
		 } 
		 
		 Film film = odgledan.getFilm();
		 double stara = film.getOcena();
		 double nova = (stara + ocenjenFilm.getOcena())/2;
		 film.setOcena(nova);
		 filmService.update(film);
		 
		 
		 gledalac.getOcenjeniFilmovi().add(ocenjen);
		 gledalacService.update(gledalac);
		 return "redirect:/odgledani-filmovi";
	 }
	 
	 @RequestMapping(value = "/otkazi-rezervaciju/{id}", method = { RequestMethod.GET, RequestMethod.DELETE} )
	 public String otkaziRezervaciju(@PathVariable(name = "id") Long id) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac gledalac = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 
		 Raspored raspored = rasporedService.findOne(id);
		 Karta karta = kartaService.findByGledalacIdAndRasporedId(gledalac.getId(), id);
		 int brojKarata = karta.getBrojKarata();
		 Long salaId = karta.getSalaId();
		 Sala sala = salaService.findOne(salaId);
		 int noviKapacitet = brojKarata + sala.getKapacitet();
		 sala.setKapacitet(noviKapacitet);
		 salaService.update(sala);
		 kartaService.delete(karta);
		 gledalac.getRezervisaneKarte().remove(raspored);
		 raspored.getGledaoci().remove(gledalac);
		 gledalacService.update(gledalac);
		 rasporedService.update(raspored);
		
		 return "redirect:/rezervisane-karte";
	 }
	 
	 @GetMapping("/dodaj-film/{id}")
	 public String dodajProjekciju(@PathVariable(name = "id") Long id, Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Film film = filmService.findOne(id);
		 if(k.getUloga().equals(Uloga.MENADZER)) {
			 
			 model.addAttribute("film", film);
			 model.addAttribute("raspored", new Raspored());
			 return "dodaj-film";
		 }
		 return "redirect:/filmovi";
	 }
	 
	 @RequestMapping(value = "/dodaj-film/{id}/sacuvaj", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sacuvajCenuVreme(@PathVariable(name = "id") Long id, Raspored raspored, BindingResult error,Model model) {
		 
		 int cena = raspored.getCena();
		 LocalDateTime datumVreme = raspored.getDatumVreme();
		 Film film = filmService.findOne(id);
		 
		 Raspored r = new Raspored(null,film,cena,datumVreme,null,null,null);
		 rasporedService.save(r);
		 return "redirect:/nedovrseni";
	 }
	 
	 @GetMapping("/nedovrseni")
	 public String nedovrseni(Model model) {
		 
		 List<Raspored> rasporedi = rasporedService.nedovrseni();
		 model.addAttribute("rasporedi", rasporedi);
		 
		 return "nedovrseni";
	 }
	 
	 @GetMapping("/dodaj-bioskop/{rasporedId}")
	 public String dodeliBioskop(@PathVariable(name = "rasporedId") Long rasporedId, Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Menadzer menadzer = menadzerService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Raspored raspored = rasporedService.findOne(rasporedId);
		 model.addAttribute("raspored", raspored);
		 Set<Bioskop> bioskopi = menadzer.getBioskopi();
		 model.addAttribute("bioskopi", bioskopi);
		 return "projekcije-bioskop";
	 }
	 
	 @GetMapping("/dodaj-bioskop/{rasporedId}/projekcije-bioskop/{bioskopId}")
	 public String sacuvajDodeluBioskopa(@PathVariable(name =  "rasporedId") Long rasporedId,@PathVariable(name = "bioskopId") Long bioskopId, Model model) {
		 
		 Raspored raspored = rasporedService.findOne(rasporedId);
		 Bioskop bioskop = bioskopService.findOne(bioskopId);
		 raspored.setBioskop(bioskop);
		 rasporedService.update(raspored);
		 model.addAttribute("raspored", raspored);
		 model.addAttribute("bioskop", bioskop);
		 Set<Sala> sale = bioskop.getBioskopskeSale();
		 model.addAttribute("sale", sale);
		 
		 return "bioskop-sale";
	 }
	 
	 @GetMapping("/dodaj-bioskop/{rasporedId}/projekcije-bioskop/{bioskopId}/bioskop-sale/{salaId}")
	 public String sacuvajDodeljenuSalu(@PathVariable(name =  "rasporedId") Long rasporedId,@PathVariable(name = "bioskopId") Long bioskopId, @PathVariable(name = "salaId") Long salaId , Model model) {
		 
		 Raspored raspored = rasporedService.findOne(rasporedId);
		 Bioskop bioskop = bioskopService.findOne(bioskopId);
		 Sala sala = salaService.findOne(salaId);
		 
		 raspored.getSale().add(sala);
		 sala.getListaTerminskihProjekcija().add(raspored);
		 salaService.update(sala);
		 rasporedService.update(raspored);
		 return "redirect:/filmovi";
	 }
	 
	 @GetMapping("/projekcije")
	 public String projekcijeFilmova(Model model) {
		 
		 List<Raspored> rasporedi = rasporedService.findAll();
		 model.addAttribute("rasporedi", rasporedi);
		 model.addAttribute("pretragaDTO", new PretragaDTO());
		 model.addAttribute("pretragaVremenaDTO", new PretragaVremenaDTO());
		 model.addAttribute("sortiranjeDTO", new SortiranjeDTO());
		 return "projekcije";
	 }
	 
	 @RequestMapping(value = "/pretraziProjekcije", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String pretraziProjekcije(@ModelAttribute PretragaDTO pretragaDTO , BindingResult error, Model model) {
		 
		 List<Raspored> svi = rasporedService.findAll();
		 List<Raspored> rasporedi = new ArrayList<Raspored>();
		 
		 String cena = Integer.toString(pretragaDTO.getCena());
		 String ocena = Double.toString(pretragaDTO.getOcena());

		 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
			 
			 rasporedi.addAll(svi);
		 } else {
			 
			 for (Raspored raspored : svi) {
				
				
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
				 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
						 
					 }
				 }
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getCena() == pretragaDTO.getCena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getOcena() == pretragaDTO.getOcena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getOcena() == pretragaDTO.getOcena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getOcena() == pretragaDTO.getOcena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0 && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getOcena() == pretragaDTO.getOcena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() == 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0 && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 }
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0  && pretragaDTO.getOpis() == "") {
					 
					 if(raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 } 
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() == "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 } 
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() == 0 && pretragaDTO.getOcena() != 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 } 
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() == 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase()) && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena()) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 } 
				 
				 if(pretragaDTO.getNaziv() == "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 } 
				 
				 if(pretragaDTO.getNaziv() != "" && pretragaDTO.getZanr() != "" && pretragaDTO.getCena() != 0 && pretragaDTO.getOcena() != 0 && pretragaDTO.getOpis() != "") {
					 
					 if(raspored.getFilm().getZanr().toUpperCase().contains(pretragaDTO.getZanr().toUpperCase()) && raspored.getCena() == pretragaDTO.getCena() && raspored.getFilm().getOcena() == pretragaDTO.getOcena() && raspored.getFilm().getOpis().toUpperCase().contains(pretragaDTO.getOpis().toUpperCase()) && raspored.getFilm().getNaziv().toUpperCase().contains(pretragaDTO.getNaziv().toUpperCase())) {
						 
						 if(rasporedi.contains(raspored)) {
							 
						 } else {
						 rasporedi.add(raspored);
						 }
					 }
				 } 
				 
				 
				 
			}
	 
		 }
		 
		 model.addAttribute("rasporedi", rasporedi);
		 model.addAttribute("sortiranjeDTO",new SortiranjeDTO());
		 model.addAttribute("pretragaVremenaDTO", new PretragaVremenaDTO());
		 
		 return "projekcije";
	 }
	 
	 @RequestMapping(value = "/sortirajFilmove", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sortiranjeFilmova(@ModelAttribute SortiranjeDTO sortiranjeDTO , BindingResult error, Model model) {
		 
		 List<Raspored> rasporedi = rasporedService.findAll();
		 
		 if(sortiranjeDTO.getSortiranje() == Sortiranje.OPADAJUCE) {
			 
			 for(int i = 0; i < rasporedi.size(); i++)
	            {
	                for(int j = i + 1; j < rasporedi.size(); j++)
	                {
	                    if(rasporedi.get(i).getCena() < rasporedi.get(j).getCena())
	                    {
	                        long id = rasporedi.get(i).getId();
	                        rasporedi.set(i, rasporedi.get(j));
	                        rasporedi.set(j, rasporedService.findOne(id));
	                    }
	                }

	            }
		 } else {
			 
			 for(int i = 0; i < rasporedi.size(); i++)
	            {
	                for(int j = i + 1; j < rasporedi.size(); j++)
	                {
	                    if(rasporedi.get(i).getCena() > rasporedi.get(j).getCena())
	                    {
	                        long id = rasporedi.get(i).getId();
	                        rasporedi.set(i, rasporedi.get(j));
	                        rasporedi.set(j, rasporedService.findOne(id));
	                    }
	                }

	            }
			 
			 
		 }
		 model.addAttribute("rasporedi", rasporedi);
		 model.addAttribute("pretragaVremenaDTO", new PretragaVremenaDTO());
		 model.addAttribute("pretragaDTO", new PretragaDTO());
		 return "projekcije";
	 }
	 
	 @RequestMapping(value = "/pretraziVremeProjekcija", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String pretragaVremenaProjekcija(@ModelAttribute PretragaVremenaDTO pretragaVremenaDTO, Model model) {
		 
		 List<Raspored> rasporedi = rasporedService.findByDatumVreme(pretragaVremenaDTO.getVreme());
		 model.addAttribute("rasporedi", rasporedi);
		 model.addAttribute("sortiranjeDTO", new SortiranjeDTO());
		 model.addAttribute("pretragaDTO", new PretragaDTO());
		 return "projekcije";
	 }
	 
	 @GetMapping("/raspored/{id}")
	 public String rasporedBioskopi(@PathVariable(name = "id") Long id, Model model) {
		 
		 Bioskop bioskop = bioskopService.findOne(id);
		 Set<Raspored> rasporedi = bioskop.getRasporedOdrzavanjaFilmova();
		 
		 model.addAttribute("rasporedi", rasporedi);
		 
		 return "raspored";
	 }
	 
	 @GetMapping("/kupi-kartu/{id}") 
	 public String kupovinaKarte(@PathVariable(name = "id") Long id) {
		 
		 Raspored raspored = rasporedService.findOne(id);
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac g = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 
		 OdgledanFilm of = new OdgledanFilm(null,raspored.getFilm(),g,null);
		 odgledanFilmService.save(of);
		 g.getOdgledaniFilmovi().add(of);
		 g.getRezervisaneKarte().remove(raspored);
		 raspored.getGledaoci().remove(g);
		 gledalacService.update(g);
		 rasporedService.update(raspored);
		 
		 
		 return "redirect:/odgledani-filmovi";
	 }
	 
	 @GetMapping("/prikazi/{id}")
	 public String prikaziProjekciju(@PathVariable(name = "id") Long id, Model model) {
		 
		 Raspored raspored = rasporedService.findOne(id);
		 model.addAttribute("raspored", raspored);
		 
		 return "prikazi";
	 }
	 
	 @GetMapping("/prikazi/{id}/izaberi-salu")
	 public String prikaziSaleProjekcija(@PathVariable(name = "id") Long id,Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/login";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.GLEDALAC)) {
			 Raspored raspored = rasporedService.findOne(id);
			 model.addAttribute("raspored", raspored);
			 Set<Sala> sale = raspored.getSale();
			 model.addAttribute("sale", sale);
			 return "izaberi-salu";
		 } 
		 return "redirect:/projekcije";
		 
	 }
	 
	 @GetMapping("/prikazi/{id}/izaberi-salu/{salaId}")
	 public String odabranaSala(@PathVariable(name = "id") Long id,@PathVariable(name = "salaId") Long salaId, Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac gledalac = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Raspored raspored = rasporedService.findOne(id);
		 Sala sala = salaService.findOne(salaId);
		 model.addAttribute("raspored", raspored);
		 model.addAttribute("sala", sala);
		 model.addAttribute("karta", new Karta());
		 
		 return "rezervisi";
	 }
	 
	 @RequestMapping(value = "/prikazi/{id}/izaberi-salu/{salaId}/rezervisi", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String rezervisi(@PathVariable(name = "id") Long id, @PathVariable(name = "salaId") Long salaId,@ModelAttribute Karta karta,BindingResult error, Model model) {
		 
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 Gledalac gledalac = gledalacService.findByKorisnickoIme(ul.getKorisnickoIme());
		 Raspored raspored = rasporedService.findOne(id);
		 Sala sala = salaService.findOne(salaId);
		 
		 int noviBroj = sala.getKapacitet() - karta.getBrojKarata();
		 if(noviBroj < 0) {
			 
			 return "redirect:/nema-dovoljno-karata";
		 }
		 karta.setGledalacId(gledalac.getId());
		 karta.setRasporedId(raspored.getId());
		 karta.setSalaId(salaId);
		 kartaService.save(karta);
		 gledalac.getRezervisaneKarte().add(raspored);
		 raspored.getGledaoci().add(gledalac);
		 gledalacService.update(gledalac);
		 rasporedService.update(raspored);
		 sala.setKapacitet(noviBroj);
		 salaService.update(sala);
		 return "redirect:/rezervisane-karte";
	 }
	 
	 @GetMapping("/nema-dovoljno-karata")
	 public String nemaKarata() {
		 
		 return "nema-dovoljno-karata";
	 }
	 
	 @GetMapping("/izmeni-projekciju/{id}")
	 public String izmeniProjekciju(@PathVariable(name = "id") Long id, Model model) {
		 
		 Raspored raspored = rasporedService.findOne(id);
		 UlogovanKorisnik ul = ulogovanKorisnikService.getTrenutniKorisnik();
		 if(ul == null) {
			 
			 return "redirect:/ponuda-bioskopa";
		 }
		 Korisnik k = korisnikService.findByKorisnickoIme(ul.getKorisnickoIme());
		 if(k.getUloga().equals(Uloga.MENADZER)) {
			 
			 Menadzer m = menadzerService.findByKorisnickoIme(ul.getKorisnickoIme());
			 boolean t = menadzerService.zaduzenZaBioskop(m, raspored.getBioskop());
			 if(t == true) {
				 
				 model.addAttribute("raspored", raspored);
				 return "izmeni-projekciju";
			 }
		 }
		 
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @RequestMapping(value = "/izmeni-projekciju/{id}/sacuvaj", method = { RequestMethod.GET, RequestMethod.POST} )
	 public String sacuvajIzmenuProjekcije(@PathVariable(name = "id") Long id, @ModelAttribute Raspored raspored, BindingResult error, Model model) {
		 
		 Raspored r = rasporedService.findOne(id);
		 r.setCena(raspored.getCena());
		 r.setDatumVreme(raspored.getDatumVreme());
		 rasporedService.update(r);
		 return "redirect:/ponuda-bioskopa";
	 }
	 
	 @GetMapping("/error")
	 public String error() {
		 
		 return "error-page";
	 }
}
