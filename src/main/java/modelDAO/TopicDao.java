package modelDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import HibernateUtils.HibernateUtil;
import Objects.Topic;

public class TopicDao {

	public Topic getTopicById(long id) {
		Transaction transaction = null;
		Session session = null;
		Topic topic = new Topic();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			topic = session.get(Topic.class, id);

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

		return topic;
	}

	@SuppressWarnings("unchecked")
	public List<Topic> getAllTopics() {
		Transaction transaction = null;
		Session session = null;
		List<Topic> topics = new ArrayList<Topic>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			topics = session.createQuery("from Topic").list();

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

		return topics;
	}

	public void saveTopic(Topic topic) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.save(topic);

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

	public void updateTopic(Topic topic) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.saveOrUpdate(topic);

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

	public void deleteTopic(Topic topic) {
		Transaction transaction = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			session.delete(topic);

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

	public Topic getByTopicName(String topicName) {
		Transaction transaction = null;
		Session session = null;
		Topic topic = new Topic();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			topic = (Topic) session.createQuery("from Topic where topic = " + topicName);
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
		return topic;

	}

}
