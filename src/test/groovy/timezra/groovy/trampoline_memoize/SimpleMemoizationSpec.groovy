package timezra.groovy.trampoline_memoize

import spock.lang.Specification

class SimpleMemoizationSpec extends Specification {

	int count

	def identity = {
		count++
		it
	}.memoize()

	def "each call should be cached"() {
		when:
		def first = identity 0
		def second = identity 0

		then:
		count == 1
		first == second
		second == 0
	}
}
