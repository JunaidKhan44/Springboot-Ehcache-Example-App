/**
 * 
 */
package com.springboot.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.model.Student;

/**
 * @author junaid.khan
 *
 */
@Service
public class APIService {

//	@Cacheable(value="student-cache",key="'StudentCache'+#studentId")
//	public Optional<Student> fetchStudent(int studentId) throws InterruptedException {
//		Thread.sleep(4000);
//		return  Arrays.asList(new Student(1,"Ali","Ch"),
//				 new Student(2,"Fatima","Ijaz"),
//				 new Student(3,"Ali","Awan"),
//				 new Student(4,"Usman","Irfan"))
//		 .stream().filter(t-> t.getStudentId()==studentId).findFirst();
//		 
//	}

	//----variation-----
	
	
	//@Cacheable(value="student-cache",key="'StudentCache'+#studentId",unless)
	//drawback of using it without condition
	//beforeInvocation remove first the cache
//	@CacheEvict(value="student-cache",key="'StudentCache'+#studentId",beforeInvocation = true)
//	@Cacheable(value="student-cache",key="'StudentCache'+#studentId")
//	public Optional<Student> fetchStudent(int studentId) throws InterruptedException {
//		Thread.sleep(4000);
//		return  Arrays.asList(new Student(1,"Ali","Ch"),
//				 new Student(2,"Fatima","Ijaz"),
//				 new Student(3,"Ali","Awan"),
// 				 new Student(4,"Usman","Irfan"))
//		 .stream().filter(t-> t.getStudentId()==studentId).findFirst();
//		 
//	}

//---------------- EhCache ---------------------------	
	
		@CacheEvict(value="twenty-second-cache",key="'StudentInCache'+#studentId",
				condition = "#isCacheable == null && !#isCacheable",beforeInvocation = true)
		@Cacheable(value="twenty-second-cache",key="'StudentInCache'+#studentId",
				condition = "#isCacheable != null && #isCacheable")
		public Optional<Student> fetchStudent(int studentId,boolean isCacheable) throws InterruptedException {
			Thread.sleep(4000);
			return  Arrays.asList(new Student(1,"Ali","Ch"),
					 new Student(2,"Fatima","Ijaz"),
					 new Student(3,"Ali","Awan"),
					 new Student(4,"Usman","Irfan"))
			 .stream().filter(t-> t.getStudentId()==studentId).findFirst();
			 
		}
		
		
//		@Cacheable(value="ten-second-cache",key="'StudentInCache'+#studentId",
//				condition = "#isCacheable != null && #isCacheable")
//		public Optional<Student> fetchStudent(int studentId,boolean isCacheable) throws InterruptedException {
//			Thread.sleep(4000);
//			return  Arrays.asList(new Student(1,"Ali","Ch"),
//					 new Student(2,"Fatima","Ijaz"),
//					 new Student(3,"Ali","Awan"),
//					 new Student(4,"Usman","Irfan"))
//			 .stream().filter(t-> t.getStudentId()==studentId).findFirst();
//			 
//		}
//	
	
}
