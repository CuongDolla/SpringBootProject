package com.eazybytes.eazyschool.repository;


import java.util.List;


import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;


import com.eazybytes.eazyschool.model.Contact;


// Inside this class, We see the @Repository because this class is mainly handle the method of storing data into database
// @Repository will add a bean of this class to the Spring Context and then it will execute the DB related operations
@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer>{
	
//	private final JdbcTemplate jdbcTemplate;
//	
//	@Autowired
//	public ContactRepository(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//	
//	// Here this type of data will return is int because the function update from jdbcTemplate will "throw the number of row affected"
//	public int saveContactMsg(Contact contact) {
//		String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS," +
//				"CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
//		// This update command actually give us the number of row that store inside the database 
//		return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),contact.getEmail(),
//				                   contact.getSubject(),contact.getMessage(),contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
//	}
//	
//	// This funtion let us get all the message of the Contact class where the status is OPEN 
//	public List<Contact> findMsgsWithStatus(String status){
//		String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
//		return jdbcTemplate.query(sql,new PreparedStatementSetter() {
//			public void setValues(PreparedStatement preparedStatement) throws SQLException{
//				preparedStatement.setString(1, status);
//			}
//		},new ContactRowMapper());
//	}
//	
//	// This function will let the administrator close the message by updating that status's message from OPEN to CLOSE. That way, when the administrator close the message
//	// It will no longer appear on the screen due to the filter but it's still store inside the H2-Database
//	public int updateMsgStatus(int contactId, String status, String updatedBy) {
//		String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
//		return jdbcTemplate.update(sql,new PreparedStatementSetter() {
//			public void setValues(PreparedStatement preparedStatement ) throws SQLException {
//				preparedStatement.setString(1, status);
//				preparedStatement.setString(2, updatedBy);
//				preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//				preparedStatement.setInt(4, contactId);
//			}
//		});
//	}
	
	List<Contact> findByStatus(String status);
	
}
