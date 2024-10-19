package fabio.uniasselvi.com.br.parkinglot.repositories;

import fabio.uniasselvi.com.br.parkinglot.dao.EntityManagerUtil;
import fabio.uniasselvi.com.br.parkinglot.entities.Vaga;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
@RequestScoped
public class VagaRepository {

    private EntityManager dao;

    private EntityManager getEntityManager() {
        if (dao == null || !dao.isOpen()) {
            dao = EntityManagerUtil.getEntityManager();
        }
        return dao;
    }

    public void criarVaga(Vaga vaga) {
        EntityTransaction transaction = null;

        try {
            transaction = getEntityManager().getTransaction();
            transaction.begin();

            getEntityManager().persist(vaga);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void atualizarVaga(Vaga vaga) {
        EntityTransaction transaction = null;

        try {
            transaction = getEntityManager().getTransaction();
            transaction.begin();

            getEntityManager().merge(vaga);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Vaga> listarVagas() {
        return getEntityManager().createQuery("SELECT v FROM Vaga v ORDER BY numero DESC", Vaga.class).getResultList();
    }

    public Vaga getById(Long id) {
        try {
            return getEntityManager().createQuery("SELECT v FROM Vaga v WHERE v.id = :id", Vaga.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean vagaComMesmoNumeroExiste(int numero) {
        EntityManager em = getEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(v) FROM Vaga v WHERE v.numero = :numero", Long.class);
        query.setParameter("numero", numero);
        Long count = query.getSingleResult();

        return count > 0;
    }

    @PreDestroy
    public void fecharEntityManager() {
        if (dao != null && dao.isOpen()) {
            dao.close();
        }
    }

}
