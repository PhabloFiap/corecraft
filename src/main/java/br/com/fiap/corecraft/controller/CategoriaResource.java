package br.com.fiap.corecraft.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.corecraft.model.Categoria;
import br.com.fiap.corecraft.model.User;
import br.com.fiap.corecraft.model.repository.CategoriaRepository;
import br.com.fiap.corecraft.model.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/categoria")
public class CategoriaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response findAll() {

		List<Categoria> retorno = CategoriaRepository.findAll();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();

	}

	@POST
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salva(@Valid Categoria categoria) {

		Categoria resp = CategoriaRepository.salva(categoria);

		final URI categoriaUri = UriBuilder.fromResource(CategoriaResource.class).path("/categoria/{id}").build(resp.getId_cat());

		ResponseBuilder response = Response.created(categoriaUri);
		response.entity(resp);
		return response.build();

	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long categoriaId) {

		if (CategoriaRepository.delete(categoriaId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();

		} else {
			System.out.println("nao foi possivel remover" + categoriaId);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}

	}

	@PUT
	@Path("/{id}")
	public Response atualiza(@PathParam("id") Long categoriaId,  @Valid Categoria categoria ) {

		Categoria velho = CategoriaRepository.findById(categoriaId);
		Categoria novo = null;
		if (velho == null || velho.getId_cat() != categoria.getId_cat()) {
			novo = CategoriaRepository.salva(categoria);

			final URI categoriaUri = UriBuilder.fromResource(CategoriaResource.class).path("/categoria/{id}").build(novo.getId_cat());

			ResponseBuilder response = Response.created(categoriaUri);
			response.entity(novo);
			return response.build();

		}

		novo = CategoriaRepository.atualiza(categoria);
		return Response.ok(novo).build();

	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long categoriaId) {
		Categoria categoria = CategoriaRepository.findById(categoriaId);
		if (categoria != null) {
			ResponseBuilder response = Response.ok();
			response.entity(categoria);
			return response.build();

		} else {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}
	}

}
