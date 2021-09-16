package example.framework.spring.transaction;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.annotation.Transactional;

public class ClassFilterDemo {

	public static void main(String[] args) {
		boolean candidateClass = AnnotationUtils.isCandidateClass(ClassFilterDemo.class, Transactional.class);
		System.out.println(candidateClass);
	}

}
