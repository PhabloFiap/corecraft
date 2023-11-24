package br.com.fiap.corecraft.model.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.corecraft.model.User;
import jakarta.validation.Valid;

public class UserRepository extends Repository {

	public static List<User> findAll() {
		String sql = "SELECT * FROM T_CLIENTE ";
		PreparedStatement ps = null;

		ResultSet rs = null;
		List<User> users = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("ID_CLIENTE"));
					user.setDt_cadastro(rs.getDate("dt_cadastro").toLocalDate());
					user.setEmail(rs.getString("em_cliente"));
					user.setNome(rs.getString("nm_cliente"));
					user.setSenha(rs.getString("sn_cliente"));

					users.add(user);

				}

			} else {
				System.out.println("Não foram encontrados registros na tabela do banco de dados.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public static User salva(User user) {

		String sql = "begin insert into T_CLIENTE (ID_CLIENTE, dt_cadastro, em_cliente, nm_cliente, sn_cliente ) values(SQ_USER.nextval,?,?,?,?)"
				+ "return ID_CLIENTE into ?; end;";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			
			cs.setDate(1,Date.valueOf(user.getDt_cadastro()));
			cs.setString(2, user.getEmail());
			cs.setString(3, user.getNome());
			cs.setString(4, user.getSenha());

			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.executeUpdate();
			user.setId(Long.valueOf(cs.getInt(5)));

			return user;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erro ao salvar " + e.getMessage());
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return null;
	}

	
	public static boolean delete(Long userId) {

		User user = null;
		String sql = "delete from T_CLIENTE where id_cliente = ?";
		PreparedStatement ps = null;
		user = findById(userId);

		if (user == null) {
			return false;

		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, userId);
			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("erro pra deletar no banco" + e.getMessage());

		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return false;
	}

	public static User findById(Long id) {
		String sql = "select * from T_CLIENTE where id_cliente = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				User user = new User();
				
				while(rs.next()) {
					
					user.setId(rs.getLong("ID_CLIENTE"));
					user.setNome(rs.getString("nm_cliente"));
					user.setEmail(rs.getString("em_cliente"));
					user.setSenha(rs.getString("sn_cliente"));
					user.setDt_cadastro(rs.getDate("dt_cadastro").toLocalDate());
		

				}
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		finally 
				{
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		return null;

	}

	public static User atualiza(@Valid User user) {
			String sql = "update T_CLIENTE set nm_cliente=?, em_cliente=?, sn_cliente=?, dt_cadastro=? where id_cliente =?";
			CallableStatement cs = null;
			try {
				cs = getConnection().prepareCall(sql);
				cs.setString(1, user.getNome());
				cs.setString(2, user.getEmail());
				cs.setString(3, user.getSenha());
				cs.setDate(4, Date.valueOf(user.getDt_cadastro()));
				cs.setLong(5, user.getId());
				
				cs.executeUpdate();
				
				return user;
				
				
				
				
			} catch (SQLException e) {

				System.out.println("Erro ao atualizar o bd" +e.getMessage());
			}
			
			finally {
				if (cs != null)
					try {
						cs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		
		return null;
	}

}
