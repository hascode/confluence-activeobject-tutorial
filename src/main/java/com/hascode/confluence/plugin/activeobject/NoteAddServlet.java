package com.hascode.confluence.plugin.activeobject;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;

public class NoteAddServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	private final ActiveObjects	ao;

	public NoteAddServlet(final ActiveObjects ao) {
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String noteText = req.getParameter("note");
		ao.executeInTransaction(new TransactionCallback<Note>() {
			@Override
			public Note doInTransaction() {
				final Note note = ao.create(Note.class);
				note.setNote(noteText);
				note.setCreated(new Date());
				note.save();
				return note;
			}
		});
		resp.getWriter().append("New note saved").close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse res)
	// throws ServletException, IOException {
	// final String noteText =
	// ServletActionContext.getRequest().getParameter("note");
	// // ao.executeInTransaction(new TransactionCallback<Note>() {
	// // @Override
	// // public Note doInTransaction() {
	// // final Note note = ao.create(Note.class);
	// // note.setNote(noteText);
	// // note.setCreated(new Date());
	// // note.save();
	// // return note;
	// // }
	// // });
	// res.getWriter().append("New note saved").close();
	// }

}
