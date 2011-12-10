package com.hascode.confluence.plugin.activeobject;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;

public class NoteDeleteServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	private final ActiveObjects	ao;

	public NoteDeleteServlet(final ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		final int noteId = Integer.parseInt(req.getParameter("noteId"));
		ao.executeInTransaction(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction() {
				final Note note = ao.get(Note.class, noteId);
				ao.delete(note);
				return null;
			}
		});
		resp.getWriter().append("New note saved").close();
	}
}
