package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

@SpringBootTest

public class TestFournisseurJunit {

	@Autowired
	FournisseurServiceImpl fournisseurService;
	@Autowired
	SecteurActiviteServiceImpl secteurActiviteService;
	@Autowired
	SecteurActiviteRepository 	secteurActiviteRepository;
	//testing Add method
		@Test
		public void testAddFournisseur(){
		List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
		int expected = fournisseurs.size();
		Fournisseur o = new Fournisseur();
		o.setCode("code");
		o.setLibelle("20");
		Fournisseur savedFournisseur= fournisseurService.addFournisseur(o);
		assertEquals(expected+1, fournisseurService.retrieveAllFournisseurs().size());
		assertNotNull(savedFournisseur.getLibelle());
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());}
		

	@Test
	public void testUpdateFournisseur() {
	Fournisseur o = new Fournisseur();
	o.setCode("code");
	o.setLibelle("20");
	Fournisseur savedFournisseur= fournisseurService.addFournisseur(o);
	savedFournisseur.setLibelle("hajer");
	fournisseurService.updateFournisseur(savedFournisseur);
	assertEquals(o.getLibelle(),savedFournisseur.getLibelle());
	fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
	}
		@Test
		public void testRetrieveFournisseurs() {
		Fournisseur o = new Fournisseur();
		o.setCode("code");
		o.setLibelle("20");

		Fournisseur savedFournisseur= fournisseurService.addFournisseur(o);
		Fournisseur getFournisseur= fournisseurService.retrieveFournisseur(savedFournisseur.getIdFournisseur());
		assertNotNull(savedFournisseur.getCode());
		assertNotNull(savedFournisseur.getLibelle());
		assertEquals(savedFournisseur.getIdFournisseur(),getFournisseur.getIdFournisseur());
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		
		
		
		

		}




		//Testing deleteOperateur
		@Test
		public void testDeleteFournisseur() {
			Fournisseur o = new Fournisseur();
			o.setCode("code");
			o.setLibelle("20");
		
			Fournisseur savedOperateur= fournisseurService.addFournisseur(o);
			fournisseurService.deleteFournisseur(savedOperateur.getIdFournisseur());
		assertNotNull(savedOperateur.getIdFournisseur());

		}
		
		
		@Test
		
		void assignSecteurActiviteToFournisseurTest() {
			DetailFournisseur df= new DetailFournisseur();
			Fournisseur f=new Fournisseur( "code",  "libelle1", null, null,
        			null,df);
			int expected = f.getSecteurActivites().size();
			
			SecteurActivite sa = new SecteurActivite();
		
			sa.setCodeSecteurActivite("code");
			sa.setCodeSecteurActivite("libelle");
			sa.setIdSecteurActivite((long) 1);
			secteurActiviteRepository.save(sa);
			
	        fournisseurService.assignSecteurActiviteToFournisseur(sa.getIdSecteurActivite(), f.getIdFournisseur());
	        
			
	        assertEquals(expected+1, f.getSecteurActivites().size());
}
}
