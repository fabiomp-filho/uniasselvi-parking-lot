package fabio.uniasselvi.com.br.parkinglot.repositories;

import fabio.uniasselvi.com.br.parkinglot.dao.EntityManagerUtil;
import fabio.uniasselvi.com.br.parkinglot.entities.Carro;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Named
@RequestScoped
public class CarroRepository {

    private EntityManager dao;

    private EntityManager getEntityManager() {
        if (dao == null || !dao.isOpen()) {
            dao = EntityManagerUtil.getEntityManager();
        }
        return dao;
    }


    public void criarCarro(Carro carro) {
        EntityTransaction transaction = null;

        try {
            transaction = getEntityManager().getTransaction();
            transaction.begin();

            getEntityManager().persist(carro);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Carro> listarCarros() {
        return getEntityManager().createQuery("SELECT c FROM Carro c ORDER BY id DESC", Carro.class).getResultList();
    }

    @PreDestroy
    public void fecharEntityManager() {
        if (dao != null && dao.isOpen()) {
            dao.close();
        }
    }
}
