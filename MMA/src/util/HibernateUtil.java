package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;

public class HibernateUtil {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	
	static {
		try {
			if(sessionFactory==null) {
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(standardServiceRegistry!=null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
		
	}
	
	public static SessionFactory getSessionFactory() {	
		return sessionFactory;
		
	}
	
}
