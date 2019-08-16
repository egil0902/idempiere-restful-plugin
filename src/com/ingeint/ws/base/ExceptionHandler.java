package com.ingeint.ws.base;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Trx;

import com.ingeint.ws.presenter.ExceptionMessage;

public class ExceptionHandler implements ExceptionMapper<AdempiereException> {

	@Override
	public Response toResponse(AdempiereException exception) {
		Trx trx = Trx.get(RequestEnv.getCurrentTrxName(), false);
		trx.rollback();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ExceptionMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception.getMessage())).build();
	}

}
