package io.gitlab.arturbosch.detekt.core.config

import io.gitlab.arturbosch.detekt.api.Config

@Suppress("UNCHECKED_CAST")
data class FailFastConfig(private val originalConfig: Config, private val defaultConfig: Config) :
    Config, ValidatableConfiguration {

    override fun subConfig(key: String) =
        FailFastConfig(originalConfig.subConfig(key), defaultConfig.subConfig(key))

    override fun <T : Any> valueOrDefault(key: String, default: T): T {
        return when (key) {
            Config.ACTIVE_KEY -> originalConfig.valueOrDefault(key, true) as T
            MAX_ISSUES_KEY -> originalConfig.valueOrDefault(key, 0) as T
            else -> originalConfig.valueOrDefault(key, defaultConfig.valueOrDefault(key, default))
        }
    }

    override fun <T : Any> valueOrNull(key: String): T? {
        return when (key) {
            Config.ACTIVE_KEY -> originalConfig.valueOrNull(key) ?: true as? T
            MAX_ISSUES_KEY -> originalConfig.valueOrNull(key) ?: 0 as? T
            else -> originalConfig.valueOrNull(key) ?: defaultConfig.valueOrNull(key)
        }
    }

    override fun validate(baseline: Config, excludePatterns: Set<Regex>) =
        validateConfig(originalConfig, baseline, excludePatterns)
}
