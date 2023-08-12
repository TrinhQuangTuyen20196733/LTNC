package Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Config.TableMapper;

public class MyRepository<T> implements IMyRepository<T> {
	public static MyRepository<?> INSTANCE;
	  private final Class<T> entityClass;

	private MyRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public MyRepository() {
		this.entityClass = null;
		// TODO Auto-generated constructor stub
	}

	public static synchronized <T> MyRepository<T> getInstance(Class<T> entityClass) {
		if (INSTANCE == null || (entityClass!= INSTANCE.getEntityClass() && INSTANCE != null )) {
			INSTANCE = new MyRepository<>(entityClass);
		}
		return (MyRepository<T>) INSTANCE;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public void insertEntity(T entity) {
		// TODO Auto-generated method stub
		
		Class<?> objectClass = entity.getClass();// Lấy ra kiểu của đối tượng 
		var entityName = objectClass.getSimpleName();
		var tableName = TableMapper.TableMap.get(entityName);
		var fields = objectClass.getDeclaredFields();
		String query = "INSERT INTO " + tableName + "(";
		String valueQuery = " values(";
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			if (fieldName != "id") {
			   Class<?> fieldType = field.getType();
				query = query + fieldName + ",";
				valueQuery = valueQuery + "?,";
			}

		}
		StringBuilder sbQuery = new StringBuilder(query);
		sbQuery.setCharAt(sbQuery.length() - 1, ')');
		StringBuilder sbValue = new StringBuilder(valueQuery);
		sbValue.setCharAt(sbValue.length() - 1, ')');
		query = sbQuery.toString() + sbValue.toString();

		Connection connection = null;
		Savepoint savepoint = null;
		int count = 1;
		// Execute Query
		try {
			connection = DBConnection.Getconnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint("Savepoint1");
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (fieldName != "id") {
					Class<?> fieldType = field.getType(); // Lấy ra kiểu dữ liệu của trường
					if (fieldType == int.class || fieldType == Integer.class) {
						var value = field.getInt(entity);
						pstmt.setInt(count, value);
					} else if (fieldType == long.class || fieldType == Long.class) {
						var value = field.getLong(entity);
						pstmt.setLong(count, value);
					} else if (fieldType == String.class) {
						var value = (String) field.get(entity);
						pstmt.setString(count, value);
					} else if (fieldType == boolean.class || fieldType == Boolean.class) {
						var value = field.getBoolean(objectClass);
						pstmt.setBoolean(count, value);
					}
					else if (fieldType == Date.class) {
			            Date value = (Date) field.get(entity);
			            if (value != null) {
			                // Chuyển đổi java.util.Date thành java.sql.Timestamp để insert vào MySQL
			                java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(value.getTime());
			                pstmt.setTimestamp(count, sqlTimestamp);
			            } else {
			                // Xử lý trường hợp giá trị null (nếu cần)
			                pstmt.setNull(count, java.sql.Types.TIMESTAMP);
			            }
			            
			        }
					else if (fieldType == LocalDateTime.class) {
					    LocalDateTime value = (LocalDateTime) field.get(entity);
					    if (value != null) {
					        // Chuyển đổi từ LocalDateTime sang java.sql.Timestamp
					        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(value);
					        pstmt.setTimestamp(count, sqlTimestamp);
					    } else {
					        // Xử lý trường hợp giá trị null (nếu cần)
					        pstmt.setNull(count, java.sql.Types.TIMESTAMP);
					    }
					}
					count++;
				}

			}
			pstmt.execute();

			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback(savepoint);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (connection != null) {

				// Đóng kết nối
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	@Override
	public void updateEntity(T entity) {
		// TODO Auto-generated method stub
			Class<?> objectClass = entity.getClass();
			var entityName = objectClass.getSimpleName();
			var tableName = TableMapper.TableMap.get(entityName);
			var fields = objectClass.getDeclaredFields();
			int id=0;
			String query = "UPDATE " + tableName + " SET ";
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (fieldName != "id") {
					Class<?> fieldType = field.getType();
					query = query +" "+ fieldName + " =?,";
			} else {
				try {
					id = field.getInt(entity);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
			StringBuilder sbQuery = new StringBuilder(query);
			sbQuery.setCharAt(sbQuery.length() - 1, ' ');
			query = sbQuery.toString()+ " WHERE id= " + id;
			

			Connection connection = null;
			Savepoint savepoint = null;
			int count = 1;
			// Execute Query
			
			try {
				connection = DBConnection.Getconnection();
				PreparedStatement pstmt = connection.prepareStatement(query);
				connection.setAutoCommit(false);
				savepoint = connection.setSavepoint("Savepoint1");
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					if (fieldName != "id") {
						Class<?> fieldType = field.getType();
						if (fieldType == int.class || fieldType == Integer.class) {
							var value = field.getInt(entity);
							pstmt.setInt(count, value);
						} else if (fieldType == long.class || fieldType == Long.class) {
							var value = field.getLong(entity);
							pstmt.setLong(count, value);
						} else if (fieldType == String.class) {
							var value = (String) field.get(entity);
							pstmt.setString(count, value);
						} else if (fieldType == boolean.class || fieldType == Boolean.class) {
							var value = field.getBoolean(objectClass);
							pstmt.setBoolean(count, value);
						}
						else if (fieldType == Date.class) {
				            Date value = (Date) field.get(entity);
				            if (value != null) {
				                java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(value.getTime());
				                pstmt.setTimestamp(count, sqlTimestamp);
				            } else {
				                pstmt.setNull(count, java.sql.Types.TIMESTAMP);
				            }
						}
						else if (fieldType == LocalDateTime.class) {
						    LocalDateTime value = (LocalDateTime) field.get(entity);
						    if (value != null) {
						        // Chuyển đổi từ LocalDateTime sang java.sql.Timestamp
						        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(value);
						        pstmt.setTimestamp(count, sqlTimestamp);
						    } else {
						        // Xử lý trường hợp giá trị null (nếu cần)
						        pstmt.setNull(count, java.sql.Types.TIMESTAMP);
						    }
						}
						count++;
					}

				}
				pstmt.executeUpdate();

				connection.commit();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback(savepoint);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (connection != null) {

					// Đóng kết nối
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			
	}
	@Override
	public void deleteById(int id) {
		var entityName = entityClass.getSimpleName();
		var tableName = TableMapper.TableMap.get(entityName);
		String query = "DELETE FROM " + tableName + " WHERE id = " + id;
		Connection connection = null;
		Savepoint savepoint = null;
		try {
			connection = DBConnection.Getconnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint("Savepoint1");
			pstmt.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback(savepoint);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (connection != null) {

				// Đóng kết nối
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	public T getById(int id) {
		// TODO Auto-generated method stub
		var entityName = entityClass.getSimpleName();
		var tableName = TableMapper.TableMap.get(entityName);
		String query = "SELECT * FROM " + tableName + " WHERE id = " + id;
		Connection connection = null;
		Savepoint savepoint = null;
		try {
			connection = DBConnection.Getconnection();
			Statement stmt = connection.createStatement();

			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint("Savepoint1");
			ResultSet rs = stmt.executeQuery(query);
			if ( rs.next()) {
				var fields = entityClass.getDeclaredFields();
				 Constructor<T> constructor = entityClass.getDeclaredConstructor();
				T result = constructor.newInstance();

				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					Class<?> fieldType = field.getType();

					if (fieldType == int.class || fieldType == Integer.class) {
						var rsValue = rs.getInt(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == long.class || fieldType == Long.class) {
						var rsValue = rs.getLong(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == String.class) {
						var rsValue = rs.getString(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == boolean.class || fieldType == Boolean.class) {
						var rsValue = rs.getBoolean(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
					else if (fieldType == Date.class) {
						var rsValue = rs.getDate(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
					else if (fieldType == LocalDateTime.class) {
						var rsValue = rs.getDate(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
				}
				connection.commit();
				connection.setAutoCommit(true);
				connection.close();
				return result;

			}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback(savepoint);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (connection != null) {

					// Đóng kết nối
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			
			
		return null;
	}

	@Override
	public List<T> getAll() {
		var entityName = entityClass.getSimpleName();
		var tableName = TableMapper.TableMap.get(entityName);
		String query = "SELECT * FROM " + tableName;
		Connection connection = null;
		Savepoint savepoint = null;
		try {
			connection = DBConnection.Getconnection();
			Statement stmt = connection.createStatement();

			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint("Savepoint1");
			ResultSet rs = stmt.executeQuery(query);
			List<T> results = new ArrayList();
			while (rs.next()) {
				var fields = entityClass.getDeclaredFields();
				 Constructor<T> constructor = entityClass.getDeclaredConstructor();
				T result = constructor.newInstance();

				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					Class<?> fieldType = field.getType();

					if (fieldType == int.class || fieldType == Integer.class) {
						var rsValue = rs.getInt(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == long.class || fieldType == Long.class) {
						var rsValue = rs.getLong(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == String.class) {
						var rsValue = rs.getString(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == boolean.class || fieldType == Boolean.class) {
						var rsValue = rs.getBoolean(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
					else if (fieldType == Date.class) {
						var rsValue = rs.getDate(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
					else if (fieldType == LocalDateTime.class) {
						var rsTimestamp = rs.getTimestamp(fieldName);
					    if (rsTimestamp != null) {
					        LocalDateTime localDateTime = rsTimestamp.toLocalDateTime();
					        var rsField = result.getClass().getDeclaredField(fieldName);
					        rsField.setAccessible(true);
					        rsField.set(result, localDateTime);
					    } else {
					        var rsField = result.getClass().getDeclaredField(fieldName);
					        rsField.setAccessible(true);
					        rsField.set(result, null); // Set the field to null if the database value is null
					    }
					}
			}
				
			results.add(result);
				
				}
				connection.commit();
				connection.setAutoCommit(true);
				connection.close();
				return results;

			
			} catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback(savepoint);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (connection != null) {

					// Đóng kết nối
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			
		
		return null;
	}

	@Override
	public List<T> getByQuery(String query) {
		Connection connection = null;
		Savepoint savepoint = null;
		try {
			connection = DBConnection.Getconnection();
			Statement stmt = connection.createStatement();

			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint("Savepoint1");
			ResultSet rs = stmt.executeQuery(query);
			List<T> results = new ArrayList();
			while (rs.next()) {
				var fields = entityClass.getDeclaredFields();
				 Constructor<T> constructor = entityClass.getDeclaredConstructor();
				T result = constructor.newInstance();

				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					Class<?> fieldType = field.getType();

					if (fieldType == int.class || fieldType == Integer.class) {
						var rsValue = rs.getInt(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == long.class || fieldType == Long.class) {
						var rsValue = rs.getLong(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == String.class) {
						var rsValue = rs.getString(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					} else if (fieldType == boolean.class || fieldType == Boolean.class) {
						var rsValue = rs.getBoolean(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
					else if (fieldType == Date.class) {
						var rsValue = rs.getDate(fieldName);
						var rsField = result.getClass().getDeclaredField(fieldName);
						rsField.setAccessible(true);
						rsField.set(result, rsValue);
					}
					else if (fieldType == LocalDateTime.class) {
						var rsTimestamp = rs.getTimestamp(fieldName);
					    if (rsTimestamp != null) {
					        LocalDateTime localDateTime = rsTimestamp.toLocalDateTime();
					        var rsField = result.getClass().getDeclaredField(fieldName);
					        rsField.setAccessible(true);
					        rsField.set(result, localDateTime);
					    } else {
					        var rsField = result.getClass().getDeclaredField(fieldName);
					        rsField.setAccessible(true);
					        rsField.set(result, null); // Set the field to null if the database value is null
					    }
					}
			}
				
			results.add(result);
				
				}
				connection.commit();
				connection.setAutoCommit(true);
				connection.close();
				return results;

			
			} catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback(savepoint);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (connection != null) {

					// Đóng kết nối
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			
		return null;
	}

	@Override
	public T getOneByQuery(String query) {
		// TODO Auto-generated method stub
		
				Connection connection = null;
				Savepoint savepoint = null;
				try {
					connection = DBConnection.Getconnection();
					Statement stmt = connection.createStatement();

					connection.setAutoCommit(false);
					savepoint = connection.setSavepoint("Savepoint1");
					ResultSet rs = stmt.executeQuery(query);
					if ( rs.next()) {
						
						var fields = entityClass.getDeclaredFields();
						 Constructor<T> constructor = entityClass.getDeclaredConstructor();
						T result = constructor.newInstance();

						for (Field field : fields) {
							field.setAccessible(true);
							String fieldName = field.getName();
							Class<?> fieldType = field.getType();

							if (fieldType == int.class || fieldType == Integer.class) {
								var rsValue = rs.getInt(fieldName);
								var rsField = result.getClass().getDeclaredField(fieldName);
								rsField.setAccessible(true);
								rsField.set(result, rsValue);
							} else if (fieldType == long.class || fieldType == Long.class) {
								var rsValue = rs.getLong(fieldName);
								var rsField = result.getClass().getDeclaredField(fieldName);
								rsField.setAccessible(true);
								rsField.set(result, rsValue);
							} else if (fieldType == String.class) {
								var rsValue = rs.getString(fieldName);
								var rsField = result.getClass().getDeclaredField(fieldName);
								rsField.setAccessible(true);
								rsField.set(result, rsValue);
							} else if (fieldType == boolean.class || fieldType == Boolean.class) {
								var rsValue = rs.getBoolean(fieldName);
								var rsField = result.getClass().getDeclaredField(fieldName);
								rsField.setAccessible(true);
								rsField.set(result, rsValue);
							}
							else if (fieldType == LocalDateTime.class) {
								var rsTimestamp = rs.getTimestamp(fieldName);
							    if (rsTimestamp != null) {
							        LocalDateTime localDateTime = rsTimestamp.toLocalDateTime();
							        var rsField = result.getClass().getDeclaredField(fieldName);
							        rsField.setAccessible(true);
							        rsField.set(result, localDateTime);
							    } else {
							        var rsField = result.getClass().getDeclaredField(fieldName);
							        rsField.setAccessible(true);
							        rsField.set(result, null); // Set the field to null if the database value is null
							    }
							}
						}
						connection.commit();
						connection.setAutoCommit(true);
						connection.close();
						return result;

					}
					} catch (Exception e) {
						e.printStackTrace();
						try {
							connection.rollback(savepoint);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} finally {
						if (connection != null) {

							// Đóng kết nối
							try {
								connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}
					
		return null;
	}

	@Override
	public int insertEntityAndReturnId(T entity) {
		// TODO Auto-generated method stub
		
				Class<?> objectClass = entity.getClass();// Lấy ra kiểu của đối tượng 
				var entityName = objectClass.getSimpleName();
				var tableName = TableMapper.TableMap.get(entityName);
				var fields = objectClass.getDeclaredFields();
				String query = "INSERT INTO " + tableName + "(";
				String valueQuery = " values(";
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					if (fieldName != "id") {
					   Class<?> fieldType = field.getType();
						query = query + fieldName + ",";
						valueQuery = valueQuery + "?,";
					}

				}
				StringBuilder sbQuery = new StringBuilder(query);
				sbQuery.setCharAt(sbQuery.length() - 1, ')');
				StringBuilder sbValue = new StringBuilder(valueQuery);
				sbValue.setCharAt(sbValue.length() - 1, ')');
				query = sbQuery.toString() + sbValue.toString();

				Connection connection = null;
				Savepoint savepoint = null;
				int count = 1;
				int Id =0;
				// Execute Query
				try {
					connection = DBConnection.Getconnection();
					PreparedStatement pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
					connection.setAutoCommit(false);
					savepoint = connection.setSavepoint("Savepoint1");
					for (Field field : fields) {
						field.setAccessible(true);
						String fieldName = field.getName();
						if (fieldName != "id") {
							Class<?> fieldType = field.getType(); // Lấy ra kiểu dữ liệu của trường
							if (fieldType == int.class || fieldType == Integer.class) {
								var value = field.getInt(entity);
								pstmt.setInt(count, value);
							} else if (fieldType == long.class || fieldType == Long.class) {
								var value = field.getLong(entity);
								pstmt.setLong(count, value);
							} else if (fieldType == String.class) {
								var value = (String) field.get(entity);
								pstmt.setString(count, value);
							} else if (fieldType == boolean.class || fieldType == Boolean.class) {
								var value = field.getBoolean(objectClass);
								pstmt.setBoolean(count, value);
							}
							else if (fieldType == Date.class) {
					            Date value = (Date) field.get(entity);
					            if (value != null) {
					                // Chuyển đổi java.util.Date thành java.sql.Timestamp để insert vào MySQL
					                java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(value.getTime());
					                pstmt.setTimestamp(count, sqlTimestamp);
					            } else {
					                // Xử lý trường hợp giá trị null (nếu cần)
					                pstmt.setNull(count, java.sql.Types.TIMESTAMP);
					            }
					            
					        }
							else if (fieldType == LocalDateTime.class) {
							    LocalDateTime value = (LocalDateTime) field.get(entity);
							    if (value != null) {
							        // Chuyển đổi từ LocalDateTime sang java.sql.Timestamp
							        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(value);
							        pstmt.setTimestamp(count, sqlTimestamp);
							    } else {
							        // Xử lý trường hợp giá trị null (nếu cần)
							        pstmt.setNull(count, java.sql.Types.TIMESTAMP);
							    }
							}
							count++;
						}

					}
					pstmt.executeUpdate();
					ResultSet generatedKeys = pstmt.getGeneratedKeys();
					if (generatedKeys.next()) {
					    Id = (int)generatedKeys.getLong(1);
					}

					connection.commit();
					connection.setAutoCommit(true);
					return Id;
					
				} catch (Exception e) {
					e.printStackTrace();
					try {
						connection.rollback(savepoint);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
				} finally {
					if (connection != null) {

						// Đóng kết nối
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					
				}
				return Id;
				
				
	}

	
	

}
