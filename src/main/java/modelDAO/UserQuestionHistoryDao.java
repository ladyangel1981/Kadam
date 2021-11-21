package modelDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateUtils.HibernateUtil;
import Objects.UserAnswerHistory;

public class UserQuestionHistoryDao {

	public UserAnswerHistory getAnswerHistoryById(long id) {
		Transaction transaction = null;
		Session session = null;
		UserAnswerHistory userAnswerHistory = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			userAnswerHistory = session.get(UserAnswerHistory.class, id);

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

		return userAnswerHistory;
	}

	@SuppressWarnings("unchecked")
	public List<UserAnswerHistory> getAllUserAnswerHistory() {
		Transaction transaction = null;
		Session session = null;
		List<UserAnswerHistory> userAnswerHistory = new ArrayList<UserAnswerHistory>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			userAnswerHistory = session.createQuery("from user_answer_history").list();

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

		return userAnswerHistory;
	}

	public void saveAnswerHistory(UserAnswerHistory userAnswerHistory) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.save(userAnswerHistory);

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

	public void updateUserAnswerHistory(UserAnswerHistory userAnswerHistory) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(userAnswerHistory);

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

	public void deleteUserAnswerHistory(UserAnswerHistory userAnswerHistory) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.delete(userAnswerHistory);

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

}
