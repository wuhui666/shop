/**
 * 
 */
package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @description :TODO
 * @author :m1582
 * @date :2018-10-24
 */
public class fdfd {

	/**  
	 * @Description:TODO
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> l1=new ArrayList<>();
		List<Integer> l2=new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l2.add(3);
		l2.add(4);
		l2.addAll(l1);
		for (Integer integer : l2) {
			System.out.println(integer);
		}

	}

}
