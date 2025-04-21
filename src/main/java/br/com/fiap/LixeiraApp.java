//package br.com.fiap;
//import java.time.LocalDate;
//import br.com.fiap.model.Lixeira;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//public class LixeiraApp {
//    public static void main(String[] args) {
//        Lixeira lixeira = new Lixeira();
//        lixeira.setEndereco("Rua Limoeiro, 12");
//        lixeira.setCapacidade(250.0);
//        lixeira.setLotada(true);
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestaoresiduos");
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        em.persist(lixeira);
//        em.getTransaction().commit();
//        em.close();
//
//    }
//}

