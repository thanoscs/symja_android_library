package org.matheclipse.core.form.tex.reflection;

import org.matheclipse.core.form.tex.AbstractConverter;
import org.matheclipse.core.interfaces.IAST;

/**
 * Operator function conversions
 */
public class Abs extends AbstractConverter {
	public Abs() {
	}

	/** {@inheritDoc} */
	public boolean convert(final StringBuffer buf, final IAST f, final int precedence) {
		if (f.size() != 2) {
			return false;
		}
		buf.append('|');
		fFactory.convert(buf, f.arg1(), 0);
		buf.append('|');
		return true;
	}
}
