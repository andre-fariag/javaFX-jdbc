package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory { // classe responsável por fabricar os DAOS.

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection()); // faz com que a implementaçao nao seja explicita no programa
														// principal.
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}

}
