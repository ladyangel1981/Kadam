package modelDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateUtils.HibernateUtil;
import Objects.Kahoot;

public class KahootDao {

	public Kahoot getKahootById(long id) {
		Transaction transaction = null;
		Session session = null;

		Kahoot kahoot = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			kahoot = session.get(Kahoot.class, id);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return kahoot;
	}

	@SuppressWarnings("unchecked")
	public List<Kahoot> getAllKahoots() {
		Transaction transaction = null;
		Session session = null;

		List<Kahoot> kahoots = new ArrayList<Kahoot>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			kahoots = session.createQuery("from Kahoot").list();

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return kahoots;
	}

	public void saveKahoot(Kahoot kahoot) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.save(kahoot);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateKahoot(Kahoot kahoot) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(kahoot);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void deleteKahoot(Kahoot kahoot) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.delete(kahoot);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Kahoot getKahootByTitle(String title) {
		Transaction transaction = null;
		Session session = null;
		Kahoot kahoot = new Kahoot();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			kahoot = (Kahoot) session.createQuery("from Kahoot where title = " + title);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return kahoot;
	}

}
