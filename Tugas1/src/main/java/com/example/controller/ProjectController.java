package com.example.controller;

import java.lang.annotation.Repeatable;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Kecamatan;
import com.example.model.Keluarga;
import com.example.model.Kelurahan;
import com.example.model.Kota;
import com.example.model.Penduduk;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProjectController
{
    @Autowired
    KecamatanService kecamatanDAO;
    @Autowired
    KeluargaService keluargaDAO;
    @Autowired
    KelurahanService kelurahanDAO;
    @Autowired
    KotaService kotaDAO;
    @Autowired
    PendudukService pendudukDAO;

    
    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }
   
    @RequestMapping("/penduduk")
    public String viewPenduduk(Model model,
            @RequestParam(value = "NIK", required = true) String nik)
    {
        //Get data by each id while validating data existance
    	Penduduk penduduk = pendudukDAO.selectPendudukByNIK(nik);
        if(penduduk != null){
        	model.addAttribute(penduduk);
        	Keluarga keluarga = keluargaDAO.selectKeluargaById(penduduk.getId_keluarga());
        	if(keluarga != null){
        		model.addAttribute(keluarga);
        		Kelurahan kelurahan = kelurahanDAO.selectKelurahanById(keluarga.getId_kelurahan());
        		if(kelurahan != null){
        			model.addAttribute(kelurahan);
        			Kecamatan kecamatan = kecamatanDAO.selectKecamatanById(kelurahan.getId_kecamatan());
        			if(kecamatan != null){
        				model.addAttribute(kecamatan);
        				Kota kota = kotaDAO.selectKotaById(kecamatan.getId_kota());
        				if(kota != null){
        					model.addAttribute(kota);
        					String prosesMatikan = "belumberjalan";
        			    	model.addAttribute("prosesMatikan", prosesMatikan);
        					//All data is validated
        					return "viewPenduduk";
        				}
        			}
        		}
        	}
        }
        
        //Data is not found
        return "not-found";
        
    }
    
    @RequestMapping("/keluarga")
    public String viewKeluarga (Model model,
            @RequestParam(value = "nkk", required = true) String nkk){
    	Keluarga keluarga = keluargaDAO.selectKeluargaByNKK(nkk);
    	if(keluarga != null){
    		model.addAttribute("keluarga",keluarga);
    		List<Penduduk> penduduk = pendudukDAO.selectPendudukByIdKeluarga(keluarga.id);
    		Kelurahan kelurahan = kelurahanDAO.selectKelurahanById(keluarga.getId_kelurahan());
    		
    		if(kelurahan != null && penduduk.size()!= 0){
    			model.addAttribute("penduduk",penduduk);
    			model.addAttribute("kelurahan",kelurahan);
    			Kecamatan kecamatan = kecamatanDAO.selectKecamatanById(kelurahan.getId_kecamatan());
    			
    			if(kecamatan != null){
    				model.addAttribute("kecamatan",kecamatan);
    				Kota kota = kotaDAO.selectKotaById(kecamatan.getId_kota());
    				
    				if(kota != null){
    					model.addAttribute("kota",kota);
    					//All data is validated
    					return "viewKeluarga";
    				}
    			}
    		}
    	}
    	return "not-found";
    }
    
    @RequestMapping("/penduduk/tambah")
    public String addPenduduk(Model model){
    	Penduduk penduduk = new Penduduk();
    	model.addAttribute(penduduk);
    	return "form-addPenduduk";
    }
    
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String addPenduduk(Model model, @ModelAttribute Penduduk penduduk){
    	penduduk.setNik(penduduk.generateNIK(pendudukDAO, keluargaDAO ,penduduk));
    	pendudukDAO.addPenduduk(penduduk);
    	model.addAttribute(penduduk);
    	return "success-addPenduduk";
    }
    
    @RequestMapping("/keluarga/tambah")
    public String addKeluarga(Model model){
    	Keluarga keluarga = new Keluarga();
    	List<Kelurahan> listKelurahan = kelurahanDAO.selectAllKelurahan();
    	List<Kecamatan> listKecamatan = kecamatanDAO.selectAllKecamatan();
    	List<Kota> listKota = kotaDAO.selectAllKota();
    	
    	model.addAttribute("keluarga", keluarga);
    	model.addAttribute("listKelurahan", listKelurahan);
    	model.addAttribute("listKecamatan", listKecamatan);
    	model.addAttribute("listKota", listKota);
    	return "form-addKeluarga";
    	
    }
    
    @RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
    public String addPenduduk(Model model, @ModelAttribute Keluarga keluarga){
    	//Mengambil kode kelurahan
    	String kode_kelurahan = kelurahanDAO.selectKelurahanById(keluarga.id_kelurahan).getKode_kelurahan().substring(0, 6);
    	
    	//Menyesuaikan isi attribut keluarga
    	String nomor_kk = keluarga.generateNKK(kode_kelurahan, keluarga, keluargaDAO);
    	keluarga.setNomor_kk(nomor_kk);
    	keluarga.setIs_tidak_berlaku(0);
    	keluargaDAO.insertKeluarga(keluarga);
    	
    	//Memasukkan attribut ke html
    	model.addAttribute("keluarga", keluarga);
    	return "sucess-addKeluarga";
    }
    
    @RequestMapping("/penduduk/ubah/{nik}")
    public String updatePenduduk(Model model,
    		@PathVariable(value = "nik") String nik){
    	Penduduk penduduk = pendudukDAO.selectPendudukByNIK(nik);
    	if(penduduk != null){
    		model.addAttribute("penduduk", penduduk);
        	return "form-updatePenduduk";
    	}else{
    		return "not-found";
    	}
    	
    }
    
    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
    public String updatePenduduk(Model model, 
    		@ModelAttribute Penduduk penduduk,
    		@PathVariable(value = "nik") String nik){
    	model.addAttribute("nik", penduduk.getNik());
    	
    	penduduk.setNik(penduduk.generateNIK(pendudukDAO, keluargaDAO ,penduduk));
    	pendudukDAO.updatePenduduk(penduduk);
    	return "success-ubahPenduduk";
    }
    
    @RequestMapping("/keluarga/ubah/{nkk}")
    public String updateKeluarga(Model model,
    		@PathVariable(value = "nkk") String nkk){
    	Keluarga keluarga= keluargaDAO.selectKeluargaByNKK(nkk);
    	
    	log.info("nkk = {}", nkk);
    	
    	List<Kelurahan> listKelurahan = kelurahanDAO.selectAllKelurahan();
    	List<Kecamatan> listKecamatan = kecamatanDAO.selectAllKecamatan();
    	List<Kota> listKota = kotaDAO.selectAllKota();
    	
    	model.addAttribute("listKelurahan", listKelurahan);
    	model.addAttribute("listKecamatan", listKecamatan);
    	model.addAttribute("listKota", listKota);
    	
    	if(keluarga != null){
    		model.addAttribute("keluarga", keluarga);
        	return "form-updateKeluarga";
    	}else{
    		return "not-found";
    	}
    }
    
    @RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
    public String updateKeluarga(Model model,
    		@ModelAttribute Keluarga keluarga,
    		@PathVariable(value = "nkk") String nkk){
    	model.addAttribute("nkk", nkk);
    	
    	//Mengambil kode kelurahan
    	String kode_kelurahan = kelurahanDAO.selectKelurahanById(keluarga.id_kelurahan).getKode_kelurahan().substring(0, 6);
    	
    	//Menyesuaikan isi attribut keluarga
    	Keluarga oldFam = keluargaDAO.selectKeluargaById(keluarga.getId());
    	if(oldFam.getId_kelurahan() != keluarga.getId_kelurahan()){
    		String nomor_kk = keluarga.generateNKK(kode_kelurahan, keluarga, keluargaDAO);
        	keluarga.setNomor_kk(nomor_kk);
    	}
    	keluargaDAO.updateKeluarga(keluarga);
    	
    	//Menyesuaikan NIK anggota keluarga
    	List<Penduduk> anggotaKeluarga = pendudukDAO.selectPendudukByIdKeluarga(keluarga.getId());
    	for(Penduduk penduduk: anggotaKeluarga){
    		penduduk.setNik(penduduk.generateNIK(pendudukDAO, keluarga.getNomor_kk().substring(0, 6)));
    		pendudukDAO.updatePenduduk(penduduk);
    	}
    	
    	return "success-ubahKeluarga";
    }
    
    @RequestMapping("/penduduk/mati")
    public String matikanPenduduk(Model model, 
    		@RequestParam(value = "nik", required = true) String nik){
    	Penduduk penduduk = pendudukDAO.selectPendudukByNIK(nik);
    	if(penduduk != null){
    		List<Penduduk> anggotaKeluarga = pendudukDAO.selectPendudukByIdKeluarga(penduduk.getId_keluarga());
        	int i = 0;
    		while(true){
	        	 if(anggotaKeluarga.get(i).getIs_wafat() == 1){
	        		 i++;
	        	 }else{
	        		 keluargaDAO.matikanKeluarga(keluargaDAO.selectKeluargaById(penduduk.getId_keluarga()));
	        		 break;
	        	 }
	        	 
        	}
        	model.addAttribute("nik",nik);
        	pendudukDAO.matikanPenduduk(penduduk);
        	return "success-matikanPenduduk";
    	}else{
    		return "not-found";
    	}
    }
    
    @RequestMapping("/penduduk/cari")
    public String cariPenduduk(Model model,
    		@RequestParam(value = "id_kota", required = false) String kt,
			@RequestParam(value = "id_kecamatan", required = false) String kc,
			@RequestParam(value = "id_kelurahan", required = false) String kl){
    	
    	List<Kota> listKota = kotaDAO.selectAllKota();
		model.addAttribute("listKota", listKota);
		model.addAttribute("id_kota", kt);

		List<Kecamatan> listKecamatan = kecamatanDAO.selectKecamatanByIdKota(kt);
		model.addAttribute("listKecamatan", listKecamatan);
		model.addAttribute("id_kecamatan", kc);

		List<Kelurahan> listKelurahan = kelurahanDAO.selectKelurahanByIdKecamatan(kc);
		model.addAttribute("listKelurahan", listKelurahan);
		model.addAttribute("id_kelurahan", kl);
		
		if (kt != null && kc != null && kl != null) {
			List<Penduduk> listPenduduk = pendudukDAO.selectPendudukByIdKelurahan(kl);
		
			Kelurahan kelurahan = kelurahanDAO.selectKelurahanById(Integer.parseInt(kl));
			Kecamatan kecamatan = kecamatanDAO.selectKecamatanById(kelurahan.getId_kecamatan());
			Kota kota = kotaDAO.selectKotaById(kecamatan.getId_kota());
			
			model.addAttribute("kelurahan", kelurahan);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("kota", kota);
			model.addAttribute("penduduk", listPenduduk);
			
			Collections.sort(listPenduduk);
			//Penduduk termuda
			model.addAttribute("pendudukMuda", listPenduduk.get(listPenduduk.size()-1));
			log.info("pendudukMuda {}" + listPenduduk.get(0).getNama());
			
			//Penduduk Tertua
			model.addAttribute("pendudukTua", listPenduduk.get(0));
			log.info("pendudukTua {}" + listPenduduk.get(listPenduduk.size()-1).getNama());
			
			return "viewHasilCari";
		}
		
    	return "cariPenduduk";
    	
    }
}
