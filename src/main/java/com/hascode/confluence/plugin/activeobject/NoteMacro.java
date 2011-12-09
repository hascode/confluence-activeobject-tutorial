package com.hascode.confluence.plugin.activeobject;

import java.util.Map;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.Macro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.sal.api.transaction.TransactionCallback;

public class NoteMacro extends BaseMacro implements Macro {

	private final ActiveObjects	ao;

	public NoteMacro(final ActiveObjects ao) {
		this.ao = ao;
	}

	@Override
	public String execute(final Map params, final String body, final RenderContext ctx) throws MacroException {
		final StringBuffer output = new StringBuffer("<b>Notes</b><br/><ul>");
		ao.executeInTransaction(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction() {
				for (final Note note : ao.find(Note.class)) {
					output.append("<li>");
					output.append(note.getCreated());
					output.append(": ");
					output.append(note.getNote());
					output.append("</li>");
				}
				return null;
			}
		});
		output.append("</ul>");
		return output.toString();
	}

	@Override
	public RenderMode getBodyRenderMode() {
		return RenderMode.ALL;
	}

	@Override
	public boolean hasBody() {
		return false;
	}

}
