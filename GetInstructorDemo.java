package hibernate.advanced.GetAndDeleteInstructor.Demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.advanced.Entity.Instructor;
import hibernate.advanced.Entity.InstructorDetails;


public class GetInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetails.class)
								.buildSessionFactory();
		// create session
		Session session=factory.getCurrentSession();
		
		try {			
			// start a transaction
			session.beginTransaction();
			
			// get instructorDetail by primary key/id
			int theId=44;
			InstructorDetails instructorDetails=session.get(InstructorDetails.class, theId);
			
			// get the instructor
			System.out.println("instructorDetails : "+instructorDetails);
			System.out.println("instructor : "+instructorDetails.getInstructor());
				
			// commit transaction
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// handle connection leakage issue
			session.close();
			factory.close();
		}
	}
}
