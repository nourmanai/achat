package tn.esprit.rh.achat;



import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

@ExtendWith(MockitoExtension.class)

class TestFournisseur {
	
	
	
	@Mock private FournisseurRepository fournisseurRepository;
	private FournisseurServiceImpl fournisseurService;
	
	@Mock private DetailFournisseurRepository detailFournisseurRepository;

	@Mock private SecteurActiviteRepository secteurActiviteRepository;
	private SecteurActiviteServiceImpl SecteurActiviteService;
	

	@BeforeEach
	public void setUp()
    {
        this.fournisseurService
            = new FournisseurServiceImpl(this.fournisseurRepository);
        this.SecteurActiviteService = new SecteurActiviteServiceImpl(this.secteurActiviteRepository);
       
    }
	
	
	@Test
	void isFournisseurExistsById() {
		
		Fournisseur f=new Fournisseur();
		DetailFournisseur df= new DetailFournisseur();
				
		df.setAdresse("adresse");
		df.setEmail("email");
		df.setMatricule("matricule");
		df.setDateDebutCollaboration(null);
		//df.setFournisseur(f);
		detailFournisseurRepository.save(df);
	
		f.setCode("code");
		f.setLibelle("libelle");
		f.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
		f.setDetailFournisseur(df);
		fournisseurRepository.save(f);
		
		
		fournisseurService.retrieveFournisseur(f.getIdFournisseur());
		verify(fournisseurRepository).findById(f.getIdFournisseur());
		
	}
	
	@Test
	void TestRetrieveAll() {
		
		
			when(fournisseurRepository.findAll()).thenReturn(Stream.of(
                new Fournisseur( "code",  "libelle1", null, null,
            			null, null),
                new Fournisseur("code2",  "libelle2", null, null,
            			null, null),
                new Fournisseur("code3",  "libelle3", null, null,
            			null, null))
                .collect(Collectors.toList()));
		assertEquals(3,fournisseurService.retrieveAllFournisseurs().size());
		
	}
	
	 @Test
	 void AddFournisseurTest() {
		 
		 Fournisseur f=new Fournisseur();	
		
			f.setCode("code");
			f.setLibelle("libelle");
			f.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
		
			fournisseurService.addFournisseur(f);
			verify(fournisseurRepository).save(f);	
	 }
	 
	 @Test
	 void DeleteFournisseurTest() {
		 Fournisseur f=new Fournisseur();
	 
		 fournisseurService.deleteFournisseur(f.getIdFournisseur());
			verify(fournisseurRepository).deleteById(f.getIdFournisseur());	
		 
	 }
	/* @Test
	 void assignSecteurActiviteToFournisseurTest() {
		    Fournisseur f=new Fournisseur();
			DetailFournisseur df= new DetailFournisseur();
			SecteurActivite sa = new SecteurActivite();*/

		   /* df.setAdresse("adresse");
			df.setEmail("email");
			df.setMatricule("matricule");
			df.setDateDebutCollaboration(null);
			//df.setFournisseur(f);
			detailFournisseurRepository.save(df);*/
			
		/*	sa.setCodeSecteurActivite("code");
			sa.setCodeSecteurActivite("libelle");
			sa.setIdSecteurActivite((long) 1);
			secteurActiviteRepository.save(sa);
			//SecteurActiviteService.addSecteurActivite(sa);
			
		    f.setCode("code");
			f.setLibelle("libelle");
			f.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);

			
			
			
			fournisseurService.assignSecteurActiviteToFournisseur(sa.getIdSecteurActivite(), f.getIdFournisseur());
			verify(fournisseurService).assignSecteurActiviteToFournisseur(sa.getIdSecteurActivite(), f.getIdFournisseur());		     
			
	 }
	 @Test
	 void UpdateFournisseurTest() {
		 
		     DetailFournisseur df= new DetailFournisseur( "email", null , "adresse", " matricule", null);
	    	 detailFournisseurRepository.save(df);
		    Fournisseur f=new Fournisseur("code",  "libelle1", null, null,null, df);
			

			Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(f);
			DetailFournisseur df2=new DetailFournisseur( "email2",null , "adresse", " matricule", null);
			f.setDetailFournisseur(df2);
			Fournisseur exisitingOp= fournisseurService.updateFournisseur(f) ;
			
			assertNotNull(exisitingOp);
			assertEquals("libelle2", f.getLibelle());
		 
		 
	 }*/
	   
}
