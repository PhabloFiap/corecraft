package br.com.fiap.corecraft.model.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.corecraft.model.Categoria;
import br.com.fiap.corecraft.model.User;
import jakarta.validation.Valid;

public class CategoriaRepository extends Repository {

	public static List<Categoria> findAll() {
		String sql = "SELECT * FROM t_categoria ";
		PreparedStatement ps = null;

		ResultSet rs = null;
		List<Categoria> categorias = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Categoria categoria = new Categoria();
					categoria.setId_cat(rs.getLong("id_categoria"));
					categoria.setNome_categ(rs.getString("nm_categoria"));

					categorias.add(categoria);

				}

			} else {
				System.out.println("Não foram encontrados registros na tabela do banco de dados.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categorias;
	}

	public static Categoria salva(Categoria categoria) {

		String sql = "begin insert into t_categoria (id_categoria, nm_categoria, t_cliente_id_cliente)  values(SEQ_CATEGORIA.nextval,?,?)"
				+ "return id_categoria into ?; end;";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			
			
			cs.setString(1, categoria.getNome_categ());
			cs.setLong(2, categoria.getUser().getId());
//			cs.setLong(2, user.getId());

			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.executeUpdate();
			categoria.setId_cat(Long.valueOf(cs.getInt(3)));

			return categoria;

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

	
	public static boolean delete(Long categoriaId) {

		Categoria categoria = null;
		String sql = "delete from t_categoria where id_categoria = ?";
		PreparedStatement ps = null;
		categoria = findById(categoriaId);

		if (categoria == null) {
			return false;

		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, categoriaId);
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

	public static Categoria findById(Long id) {
		String sql = "select * from t_categoria where id_categoria = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				Categoria categoria = new Categoria();
				
				while(rs.next()) {
					
					categoria.setId_cat(rs.getLong("id_categoria"));
					categoria.setNome_categ(rs.getString("nm_categoria"));
					
		

				}
				return categoria;
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

	public static Categoria atualiza(@Valid Categoria categoria) {
			String sql = "update t_categoria set nm_categoria=? where id_categoria =?";
			CallableStatement cs = null;
			try {
				cs = getConnection().prepareCall(sql);
				cs.setString(1, categoria.getNome_categ());
				cs.setLong(2,categoria.getId_cat());
				
				cs.executeUpdate();
				
				return categoria;
				
				
				
				
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
