package br.com.fiap.corecraft.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.corecraft.model.User;
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

@Path("/user")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response findAll() {

		List<User> retorno = UserRepository.findAll();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();

	}

	@POST
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salva(@Valid User user) {

		User resp = UserRepository.salva(user);

		final URI userUri = UriBuilder.fromResource(UserResource.class).path("/user/{id}").build(resp.getId());

		ResponseBuilder response = Response.created(userUri);
		response.entity(resp);
		return response.build();

	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long userId) {

		if (UserRepository.delete(userId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();

		} else {
			System.out.println("nao foi possivel remover" + userId);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}

	}

	@PUT
	@Path("/{id}")
	public Response atualiza(@PathParam("id") Long userId,  @Valid User user) {

		User velho = UserRepository.findById(userId);
		User novo = null;
		if (velho == null || velho.getId() != user.getId()) {
			novo = UserRepository.salva(user);

			final URI userUri = UriBuilder.fromResource(UserResource.class).path("/user/{id}").build(novo.getId());

			ResponseBuilder response = Response.created(userUri);
			response.entity(novo);
			return response.build();

		}

		novo = UserRepository.atualiza(user);
		return Response.ok(novo).build();

	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long userId) {
		User user = UserRepository.findById(userId);
		if (user != null) {
			ResponseBuilder response = Response.ok();
			response.entity(user);
			return response.build();

		} else {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}
	}

}
