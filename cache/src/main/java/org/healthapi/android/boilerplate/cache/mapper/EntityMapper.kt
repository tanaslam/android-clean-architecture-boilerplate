package org.healthapi.android.boilerplate.cache.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <C> the cached model input type
 * @param <C> the remote model input type
 * @param <E> the model return type
 */
interface EntityMapper<C, E> {

    fun mapFromCached(cache: C): E

    fun mapToCached(entity: E): C

}