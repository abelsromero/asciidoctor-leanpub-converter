package org.asciidoctor.leanpub.internal

import org.asciidoctor.converters.markdown.core.InlineQuotedTextFormatter

/**
 * @author Schalk W. Cronjé
 */
class QuotedTextConverter extends InlineQuotedTextFormatter {
    static String latexmath(final String text) {
        '{$$}' + text + '{/$$}'
    }
}
