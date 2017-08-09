package org.asciidoctor.leanpub

import org.asciidoctor.converter.LeanpubConverter
import org.asciidoctor.leanpub.internal.LeanpubSpecification
import spock.lang.FailsWith
import spock.lang.Issue


/**
 * @author Schalk W. Cronjé
 */
class LabeledListsSpec extends LeanpubSpecification {

    @Issue(['https://github.com/asciidoctor/asciidoctorj/issues/404',
    'https://github.com/asciidoctor/asciidoctor-leanpub-converter/issues/30'])
    def "Labeled lists should process examples from Asciidoctor user guide"() {
        setup:
        File single = new File(LeanpubSpecification.MANUSCRIPT_DIR, 'chapter_1.txt')
        File multi = new File(LeanpubSpecification.MANUSCRIPT_DIR, 'chapter_2.txt')
        File qanda = new File(LeanpubSpecification.MANUSCRIPT_DIR, 'chapter_3.txt')
        File mixed = new File(LeanpubSpecification.MANUSCRIPT_DIR, 'chapter_4.txt')
        File multilevel = new File(LeanpubSpecification.MANUSCRIPT_DIR, 'chapter_4.txt')

        when:
        generateOutput('labeled-lists.adoc')

        then: "Single line defintions with empty line separations are translated"
        single.text == '''# Single line

first term

: definition of first term

second term

: definition of second term

'''
        and: "Multi-line definitions with empty line separations are translated"
        multi.text == '''# Multi-line

first term

: definition of first term
on two lines

second term

: definition of second term
can be on multiple lines

'''
        and: "Q&A blocks are translated"
        qanda.text == '''# Q &amp; A

> **Q & A block**
> ''' +
            '''
> What is Asciidoctor?
> ''' +
            '''
> : An implementation of the AsciiDoc processor in Ruby.
> ''' +
            '''
> What is the answer to the Ultimate Question?
> ''' +
            '''
> : 42
> ''' + LeanpubConverter.LINESEP


        and: 'Single line definitions would blank lines are translated'
        multilevel.text == '''# Definition list with no blank lines

first term

: definition of first term

second term

: definition of second term

'''
    }

    @FailsWith(org.asciidoctor.internal.AsciidoctorCoreException)
    @Issue('https://github.com/asciidoctor/asciidoctor-leanpub-converter/issues/54')
    def "Labeled lists should process hybrid example from Asciidoctor user guide"() {
        setup:
        File hybrid = new File(LeanpubSpecification.MANUSCRIPT_DIR, 'chapter_1.txt')

        when:
        generateOutput('labeled-hybrid-lists.adoc')

        then:
        hybrid.text == '''# Hybrid
'''
    }
}