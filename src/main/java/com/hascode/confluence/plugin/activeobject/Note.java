package com.hascode.confluence.plugin.activeobject;

import java.util.Date;

import net.java.ao.Entity;
import net.java.ao.Preload;

@Preload
public interface Note extends Entity {
	public abstract String getNote();

	public abstract void setNote(final String note);

	public abstract Date getCreated();

	public abstract void setCreated(final Date date);
}
