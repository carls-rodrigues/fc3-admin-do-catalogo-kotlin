package com.fullcycle.admin.catalogo.domain

import java.util.Objects

abstract class Entity<ID: Identifier> (
    protected var entityId: ID
){
    init {
        Objects.requireNonNull(entityId, "Id cannot be null")
    }

    fun getId(): ID {
        return entityId
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entity<*>) return false

        if (entityId != other.entityId) return false

        return true
    }

    override fun hashCode(): Int {
        return entityId.hashCode()
    }


}