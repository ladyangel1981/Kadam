package HibernateUtils;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import Objects.Answer;
import Objects.Competition;
import Objects.Kahoot;
import Objects.Player;
import Objects.Question;
import Objects.QuestionType;
import Objects.Topic;
import Objects.User;
import Objects.UserAnswerHistory;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, String> settings = new HashMap<>();
				settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
				settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/Kadam?serverTimezone=UTC");
				settings.put("hibernate.connection.username", "root");
				settings.put("hibernate.connection.password", "root");
				settings.put("hibernate.show_sql", "true");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

				settings.put("hibernate.hbm2ddl.auto", "update");

				registryBuilder.applySettings(settings);
				registry = registryBuilder.build();
				MetadataSources sources = new MetadataSources(registry);
				sources.addAnnotatedClass(Answer.class);
				sources.addAnnotatedClass(Competition.class);
				sources.addAnnotatedClass(Kahoot.class);
				sources.addAnnotatedClass(Player.class);
				sources.addAnnotatedClass(Question.class);
				sources.addAnnotatedClass(QuestionType.class);
				sources.addAnnotatedClass(Topic.class);
				sources.addAnnotatedClass(User.class);
				sources.addAnnotatedClass(UserAnswerHistory.class);

				sessionFactory = sources.buildMetadata().buildSessionFactory();

			} catch (Exception e) {
				System.out.println("SessionFactory creation failed");
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}