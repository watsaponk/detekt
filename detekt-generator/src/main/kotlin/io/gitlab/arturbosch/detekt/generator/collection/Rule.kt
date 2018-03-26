package io.gitlab.arturbosch.detekt.generator.collection

/**
 * @author Marvin Ramin
 */
data class Rule(
		val name: String,
		val description: String,
		val nonCompliantCodeExample: String,
		val compliantCodeExample: String,
		val active: Boolean,
		val severity: String,
		val debt: String,
		val configuration: List<Configuration> = listOf()
)
