package com.mazurak;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.mazurak.entity.Course;
import com.mazurak.entity.Student;
import com.mazurak.entity.Teacher;
import com.mazurak.entity.TeacherDetails;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		//saveData(em);

	
		
		
		
		// LIKE
		// List<Teacher> list = em.createQuery("Select t from Teacher t where
		// t.firstName LIKE :teacherName" , Teacher.class)
		// .setParameter("teacherName", "%#%1%").getResultList();
		// list.forEach(System.out::println);
		//
		// List<Course> list = em.createQuery("Select c from Course c where c.id>10 and
		// c.title like :title",Course.class)
		// .setParameter("title", "%8").getResultList();
		// list.forEach(System.out::println);

		
		// IN

		// em.createQuery("Select t from Teacher t where t.id in (:ids)")
		// .setParameter("ids",
		// Arrays.asList(12,32,43654,6,2)).getResultList().forEach(System.out::println);
	
		//MAX
//		BigDecimal bigDecimal = em.createQuery("Select max(c.price) from Course c", BigDecimal.class)
//				.getSingleResult();
//		System.out.println(bigDecimal);
		
		//SUM
//		BigDecimal bigDecimal = em.createQuery("Select sum(c.price) from Course c where c.id between 20 and 40", BigDecimal.class)
//				.getSingleResult();
//		System.out.println(bigDecimal);
//		em.getTransaction().commit();
		
		//BETWEEN
//		BigDecimal bigDecimal = em.createQuery("Select sum(c.price) from Course c where c.id between 20 and 40", BigDecimal.class)
//				.getSingleResult();
//		System.out.println(bigDecimal);
//		em.getTransaction().commit();

		//AVG
//		Double double1 = em.createQuery("Select avg(c.price) from Course c", Double.class)
//				.getSingleResult();
//		System.out.println(double1);
//		Teacher teacher = em.createQuery("Select t from Teacher t where t.id = ?1",Teacher.class)
//				.setParameter(1, 10).getSingleResult();
//		System.out.println(teacher);
//		
		
//		Teacher teacher = em.createQuery("Select t from Teacher t join fetch t.teacherDetails where t.id = ?1",Teacher.class)
//				.setParameter(1, 12).getSingleResult();
//		System.out.println(teacher);
//		System.out.println(teacher.getTeacherDetails());
		
	//JOIN
		
//			Student s = em.createQuery("Select s from Student s left join fetch s.cources where s.id = ?1",Student.class)
//					.setParameter(1, 11).getSingleResult();
//			System.out.println(s);
//			s.getCources().forEach(System.out::println);
		
//		List<Course> list = em.createQuery("Select c from Course c join fetch c.teacher t where t.id = 12 ",Course.class)
//				.getResultList();
//		list.forEach(System.out::println);
		
		
		
		
		
		em.close();
		entityManagerFactory.close();
	}

	static void saveData(EntityManager em) {
		for (int i = 1; i <= 100; i++) {
			Teacher teacher = new Teacher();
			teacher.setFirstName("Marian#" + i);
			teacher.setLastName("Mazurak#" + i);
			teacher.setAge(10 + i);
			teacher.setTeacherDetails(new TeacherDetails("email#-" + i , "hobby#-" + i));
			em.persist(teacher);
		}

		for (int i = 1; i <= 100; i++) {
			Teacher teacher = em.createQuery("Select t from Teacher t where t.id = :teacherId", Teacher.class)
					.setParameter("teacherId", i).getSingleResult();
			Course course = new Course();
			course.setTitle("Title$# " + i);
			course.setDescription("desc$%# " + i);
			course.setPrice(new BigDecimal(100 + i + ".00"));
			course.setTeacher(teacher);
			em.persist(course);
		}
		
		for(int i = 1; i<=500;i++) {
			Student student = new Student();
			student.setFullName("FuelNameStudent#" + i);
			student.setAge(i);
			List<Course> course = em.createQuery("Select c from Course c",Course.class)
					.getResultList();
			if(i%2 == 1) {
				student.setCources(course);
			}
		
			em.persist(student);
		}
		
	}

	
}
