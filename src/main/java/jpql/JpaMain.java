package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUserName("회원 1");
            member1.setAge(10);
            member1.setTeam(teamA);

            Member member2 = new Member();
            member2.setUserName("회원 2");
            member2.setAge(10);
            member2.setTeam(teamB);

            em.persist(member1);
            em.persist(member2);
            em.flush();
            em.clear();

            List<Member> result = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원 1")
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getUserName() + ", " + member.getTeam().getName() );
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();

    }
}
