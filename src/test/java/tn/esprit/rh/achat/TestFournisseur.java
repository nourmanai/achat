package tn.esprit.rh.achat;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

@ExtendWith(MockitoExtension.class)

public class TestFournisseur {
	
	
	
	@Mock private FournisseurRepository fournisseurRepository;
	private FournisseurServiceImpl fournisseurService;
	
	@Mock private DetailFournisseurRepository detailFournisseurRepository;

	@Mock private SecteurActiviteRepository secteurActiviteRepository;
	@BeforeEach
	public void setUp()
    {
        this.fournisseurService
            = new FournisseurServiceImpl(this.fournisseurRepository);
        new SecteurActiviteServiceImpl(this.secteurActiviteRepository);
       
    }
	
	
	@Test
	void isFournisseurExistsById() {
		
		Fournisseur f=new Fournisseur();
		DetailFournisseur df= new DetailFournisseur();
				
		df.setAdresse("adresse");
		df.setEmail("email");
		df.setMatricule("matricule");
		df.setDateDebutCollaboration(null);
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
/*@Test
	 void assignSecteurActiviteToFournisseurTest() {
			DetailFournisseur df= new DetailFournisseur();
		    Fournisseur f=new Fournisseur( "code",  "libelle1", null, null,
        			null,df);
		
			SecteurActivite sa = new SecteurActivite();
		
			sa.setCodeSecteurActivite("code");
			sa.setCodeSecteurActivite("libelle");
			sa.setIdSecteurActivite((long) 1);
			secteurActiviteRepository.save(sa);
					
			fournisseurService.assignSecteurActiviteToFournisseur(sa.getIdSecteurActivite(), f.getIdFournisseur());
			verify(fournisseurService).assignSecteurActiviteToFournisseur(sa.getIdSecteurActivite(), f.getIdFournisseur());		     
			
	 }*/
	/*@Test
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
